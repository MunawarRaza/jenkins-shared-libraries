def call(Map config) {
    // Define user-related variables here
    
    currentBuild.displayName = "ENV-${params.ENVIRONMENTS} , Branch - ${params.TAG}"

    // Get the triggering user's name
    def cause = currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')
    def userName = cause?.userId?.toString()?.replaceAll(/\[|\]/, "") ?: "Unknown"
    echo "Triggered by user: ${userName}"

    // Check if the selected environment is a development environment
    def isDevOrQaEnv = config.ENVIRONMENTS.contains(params.ENVIRONMENTS)

    // Validate user permissions
    if (params.ENVIRONMENTS == 'dev' && config.DEVOPS_DEV_USERS.contains(userName)) {
        echo "User ${userName} is allowed to deploy to the Dev environment."
    } else if (isDevOrQaEnv && config.DEVOPS_USERS.contains(userName)) {
        echo "User ${userName} is allowed to deploy to the ${params.ENVIRONMENTS} environment."
    } else {
        error("User ${userName} is not authorized to deploy to the ${params.ENVIRONMENTS} environment.")
    }
}
