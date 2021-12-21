pipeline {
   agent { dockerfile true }
   stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
             steps {
                sh 'docker --version'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
