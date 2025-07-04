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
"""
    }
}
