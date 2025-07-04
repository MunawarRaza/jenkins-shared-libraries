def function1(){
    sh "echo Hello World ${Username}"
}
def function2(String name, String email){
  sh "echo Hi ${name}, Your email is ${email}"
}
