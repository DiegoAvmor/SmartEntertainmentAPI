pipeline {
    agent any

    environment {
        CONTAINER_NAME="smartapi_container"
    }    

    stages {
        stage('Build') {
            steps {
                //Se realiza la instalación de las dependencias
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                //Paramos el contenedor y lo eliminamos
                sh 'docker stop ${CONTAINER_NAME} || true && docker rm ${CONTAINER_NAME} || true'
                //Generamos la nueva imagen
                sh 'docker build -t smartapi:v${BUILD_NUMBER} .'
                //Creamos el contenedor con la imagen y la iniciamos
                sh 'docker container create --name=${CONTAINER_NAME} -p 8084:8084 smartapi:v${BUILD_NUMBER}'
                sh 'docker container start ${CONTAINER_NAME}'
            }
        }
    }
}