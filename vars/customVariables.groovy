//def call() {
//    def myvars = myvars()
//    return myvars
//}

//def ENVIRONMENT = {
//    'dev': 'Development Environment',
//    'qa': 'Quality Assurance Environment',
//    'prod': 'Production Environment'
// def Email="munawarraza068@gmail.com"
//}

def call() {
    def BASE_DIR="/var/lib/jenkins/deployment_files"
    def SCRIPTS_DIR="${BASE_DIR}/micro-services/java-8"
    def INVENTOR_DIR="${BASE_DIR}/inventory"
    def DEPENDENCIES_DIR="${BASE_DIR}/dependencies"
  return [
    BASE_DIR: BASE_DIR,
    SCRIPTS_DIR: SCRIPTS_DIR,
    INVENTOR_DIR: INVENTOR_DIR,
    DEPENDENCIES_DIR: DEPENDENCIES_DIR,
    DOCKERFILE:'micro-service-dockerfile',
    DEPLOYMENT_FILE:'micro-service-deployment.yml',
    START_SCRIPT_FILE:'start.sh',
    ANSIBLE_INVENTORY_FILE:'inventory_file',
    SERVICE_NAME:'oneload-zong-load-service',
    LOGBACK_FILE_PATH:'src/main/resources/',
    LOGBACK_FILE:'logback-spring.xml',
    PORT:'2022',

    DEVOPS_USERS: ['munawar', 'waqas'],
    DEVOPS_DEV_USERS: ['munawar', 'waqas', 'rehan', 'ali', 'sulman', 'maria', 'developer'],
    ENVIRONMENTS: ['dev', 'qa1', 'qa2', 'uat', 'uat1', 'uat2', 'sync'],
    DOCKER_REGISTRY: 'docker.oneloadpk.com',
    IMAGE_VERSION: 'v1.0.0',
    DEPLOY_TIMEOUT: 300,
    SERVICE_IPS: [
	DEV_IP: "10.130.21.22",
	QA1_IP: "10.130.22.22",
	QA2_IP: "10.130.23.22"
	],
    // add more variables as needed
  ]
}
