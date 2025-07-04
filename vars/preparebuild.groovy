def call(Map config){
    script {
sh"""
    echo BASE_DIR: '${config.BASE_DIR}'
    echo Dockerfile '${config.DOCKERFILE}'
     
    echo '${config.DEPLOYMENT_FILE}'
    rm -f tags.txt
    echo tag_name: '${params.TAG}' >>tags.txt
    echo job_name: '${JOB_NAME}' >>tags.txt
    echo env_name: '${params.ENVIRONMENTS}' >>tags.txt
    echo workspace_name: '${WORKSPACE}' >>tags.txt
    echo Docker Registory: '${config.DOCKER_REGISTRY}' >>tags.txt
    cp -r '${config.SCRIPTS_DIR}'/'${config.DEPLOYMENT_FILE}' .

    echo ### Setup Inventory file for the deployment ###
    cp -r '${INVENTOR_DIR}'/'${ANSIBLE_INVENTORY_FILE}' .
   
    sed -i 's/target/${params.ENVIRONMENTS}/g' '${ANSIBLE_INVENTORY_FILE}'
    
    sed -i 's/ip/${config.SERVICE_IPS[params.ENVIRONMENTS]}/g' '${ANSIBLE_INVENTORY_FILE}'
   
  #  if [ '${params.ENVIRONMENTS}' = 'dev' ]; then
  #     sed -i 's/ip/${DEV_IP}/g' '${ANSIBLE_INVENTORY_FILE}'
  #  elif [ '${params.ENVIRONMENTS}' = 'qa1' ]; then
  #     sed -i 's/ip/${QA1_IP}/g' '${ANSIBLE_INVENTORY_FILE}'
  #  elif [ '${params.ENVIRONMENTS}' = 'qa2' ]; then
  #     sed -i 's/ip/${QA2_IP}/g' '${ANSIBLE_INVENTORY_FILE}'
  #  elif [ '${params.ENVIRONMENTS}' = 'sync' ]; then
  #     cp -r '${INVENTOR_DIR}'/sync/'${ANSIBLE_INVENTORY_FILE}' .
  #     sed -i 's/ip1/${QA1_IP}/g' '${ANSIBLE_INVENTORY_FILE}'
  #     sed -i 's/ip2/${QA2_IP}/g' '${ANSIBLE_INVENTORY_FILE}'
  #  else
  #  echo 'You have not selected any relevant environment'
  #  fi

"""
    }
}
