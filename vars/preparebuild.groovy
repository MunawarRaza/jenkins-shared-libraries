def call(Map config){
    script {
sh"""
    echo BASE_DIR: '${config.BASE_DIR}'
    echo Dockerfile '${config.DOCKERFILE}'
     
    echo '${config.DEPLOYMENT_FILE}'
    rm -f tags.txt
    rm -f '${config.ANSIBLE_INVENTORY_FILE}'
    echo tag_name: '${params.TAG}' >>tags.txt
    echo job_name: '${JOB_NAME}' >>tags.txt
    echo env_name: '${params.ENVIRONMENTS}' >>tags.txt

    echo ### Setup Inventory file for the deployment ###
     cp -f '${config.SCRIPTS_DIR}'/'${config.DEPLOYMENT_FILE}' .
     echo '[${params.ENVIRONMENTS}]' >> '${config.ANSIBLE_INVENTORY_FILE}'
    if [ ${params.ENVIRONMENTS} = 'sync' ];then
        sed -i '1a ${config.SERVICE_IPS.qa1' '${config.ANSIBLE_INVENTORY_FILE}'
        sed -i '2a ${config.SERVICE_IPS.qa2}' '${config.ANSIBLE_INVENTORY_FILE}'
    else
	sed -i '1a ${config.SERVICE_IPS[params.ENVIRONMENTS]}' '${config.ANSIBLE_INVENTORY_FILE}'
    fi
"""
    }
}
