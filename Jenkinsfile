node {
	    def mvnHome = tool 'maven-3.6.3'

	    def dockerImage

	    def dockerImageTag = "ewallet${env.BUILD_NUMBER}"

	    stage('Clone Repo') { // for display purposes
	      // Get some code from a GitHub repository
	      git 'https://github.com/profemzy/EWallet.git'
	      mvnHome = tool 'maven-3.6.3'
	    }

	    stage('Build Project') {
	      // build project via maven
	      sh "'${mvnHome}/bin/mvn' clean install"
	    }

	    stage('Build Docker Image') {
	      // build docker image
	      dockerImage = docker.build("ewallet:${env.BUILD_NUMBER}")
	    }

	    stage('Deploy Docker Image'){

	      echo "Docker Image Tag Name: ${dockerImageTag}"

		  sh "docker run --name ewallet -d -p 9000:9000 ewallet:${env.BUILD_NUMBER}"

		  // docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
	      //    dockerImage.push("${env.BUILD_NUMBER}")
	      //      dockerImage.push("latest")
	      //  }

	    }
}
