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
  return [
    ENVIRONMENTS: ['qa', 'staging', 'prod'],
    DOCKER_REGISTRY: 'docker.oneloadpk.com',
    IMAGE_VERSION: 'v1.0.0',
    DEPLOY_TIMEOUT: 300,
    // add more variables as needed
  ]
}
