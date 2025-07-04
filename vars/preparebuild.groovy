def call(Map config){
    script {
sh"""
    rm -f tags.txt
    echo tag_name: '${params.TAG}' >>tags.txt
    echo job_name: '${JOB_NAME}' >>tags.txt
    echo env_name: '${params.ENVIRONMENTS}' >>tags.txt
    echo workspace_name: '${WORKSPACE}' >>tags.txt
    echo Docker Registory: '${config.DOCKER_REGISTRY}' >>tags.txt
"""
    }
}
