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
          
          stage("Test") {
             steps {
                sh "./mvnw test"
             }
          }
          
          stage('Docker Image Build') {
              steps {
                   echo "Build Number: {env.BUILD_NUMBER}"   
             }
          }
     }
}
