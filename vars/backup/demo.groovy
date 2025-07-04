def function1(){
    sh "echo Hello World!"
}
def function2(String name, String email){
  sh "echo Hi ${name}!, Your email is ${email}"
}
def function3(Map config){
  sh "echo This is your IP in demo file ${config.SERVICE_IPS.DEV_IP}"
}
