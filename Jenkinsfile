pipeline {
     agent any
     stages {
          stage("Checkout") {
               steps {
                    git url: 'https://github.com/profemzy/EWallet.git'
               }
          }
          
          stage("Compile") {
             steps {
                  sh "chmod +x ./mvnw"
                  sh "./mvnw package"
             }
          }
          
          stage("Unit test") {
             steps {
                sh "./mvnw test"
             }
          }
     }
}
