def call(Map config) {
    // Define user-related variables here
    def devOpsUsers = ['munawar', 'waqas'] // Users allowed for all environments
    def devOpsDevUsers = ['munawar', 'waqas', 'rehan', 'ali', 'sulman', 'maria', 'developer'] // Additional users for dev
    def developmentEnvs = ['dev', 'qa1', 'qa2', 'uat', 'uat1', 'uat2', 'sync']

    // Set custom display name
    // currentBuild.displayName = "ENV-${params.ENVIRONMENTS} , Branch - ${params.TAG}"

    // Get the triggering user's name
    def cause = currentBuild.getBuildCauses('hudson.model.Cause$UserIdCause')
    def userName = cause?.userId?.toString()?.replaceAll(/\[|\]/, "") ?: "Unknown"
    echo "Triggered by user: ${userName}"

    // Check if the selected environment is a development environment
    def isDevOrQaEnv = developmentEnvs.contains(params.ENVIRONMENTS)

    // Validate user permissions
    if (params.ENVIRONMENTS == 'dev' && ${config.DEVOPS_DEV_USERS}.contains(userName)) {
        echo "User ${userName} is allowed to deploy to the Dev environment."
    } else if (isDevOrQaEnv && devOpsUsers.contains(userName)) {
        echo "User ${userName} is allowed to deploy to the ${params.ENVIRONMENTS} environment."
    } else {
        error("User ${userName} is not authorized to deploy to the ${params.ENVIRONMENTS} environment.")
    }
}
