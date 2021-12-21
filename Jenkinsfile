pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               sh  'gradle build -x test'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh  'gradle test'  
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                 sh  'docker-compose up' 
            }
        }
    }
}
