def call(Map config){
	echo "Registry: ${config.DOCKER_REGISTRY}"
  sh """
    echo "Deploying image version ${config.IMAGE_VERSION} to ${config.DOCKER_REGISTRY}"
  """
}
