/* groovylint-disable CompileStatic, DuplicateStringLiteral */
pipeline {
    agent any
    options {
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '-1', artifactNumToKeepStr: '2'))
    }
    parameters {
        string(name: 'x-github-event')
        string(name: 'github-event-payload')
    }

    stages {
        stage('Parse Event Payload') {
            steps {
                script {
                    eventPayload = readJSON text: params['github-event-payload']
                    prettyJson = writeJSON json: eventPayload, pretty: 2
                    print prettyJson
                }
            }
        }
        stage('Process Event') {
            steps {
                echo "Processing event: ${params['x-github-event']}"
            }
        }
    }
}
