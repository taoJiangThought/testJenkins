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
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
