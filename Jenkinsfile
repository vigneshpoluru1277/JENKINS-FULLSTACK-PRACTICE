pipeline {
    agent any

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                dir('FRONTEND') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
       stage('Deploy Frontend to Tomcat') {
    steps {
        bat '''
            if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\reactlearning" (
                rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\reactlearning"
            )
            mkdir "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\reactlearning"
            xcopy /E /I /Y FRONTEND\\dist\\* "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\reactlearning"
        '''
    }
}


        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                dir('BACKEND/LibraryBookManagementSystem') {
                    bat 'mvn clean package'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
            steps {
                bat '''
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\springbootlearning.war" (
                    del /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\springbootlearning.war"
                )
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\springbootlearning" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\springbootlearning"
                )
                copy "BACKEND\\LibraryBookManagementSystem\\target\\*.war" "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\"
                '''
            }
        }

    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
    }
}