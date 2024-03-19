pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
         stage('Project build') {
            steps {
            powershell 'mvn clean install -DskipTests=true'
            }
        }
         stage('Maven Tests') {
            steps {
            powershell 'mvn test -Dconfig=%CONFIG% -Dsuite=%SUITE%'
            }
        }
        stage('Copy logs') {
            steps {
            archiveArtifacts artifacts: 'target/logs/*', followSymlinks: false
            }
        }
    }

    post("Allure results") {
        always {
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}