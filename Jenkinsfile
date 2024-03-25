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

    }

    post("Allure results") {
        always {
         archiveArtifacts artifacts: 'target/logs/*', followSymlinks: false
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}