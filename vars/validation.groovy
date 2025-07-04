def call(Map config){
	echo "Registry: ${config.DOCKER_REGISTRY}"
	echo "Your Job name is ${env.JOB_NAME}"
  sh """
    echo "Deploying image version ${config.IMAGE_VERSION} to ${config.DOCKER_REGISTRY}"
  """
}
