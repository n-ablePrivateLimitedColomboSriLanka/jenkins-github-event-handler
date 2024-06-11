/* groovylint-disable CompileStatic, DuplicateStringLiteral, Indentation, NestedBlockDepth */
pipelineJob('GitHubEventHandler') {
  description('labels:meta')
  parameters {
    stringParam('x_github_event', 'none')
    stringParam('github_event_payload', 'none')
  }
  properties {
    parameters {
      pipelineTriggers {
        triggers {
          GenericTrigger {
            genericVariables {
              genericVariable {
                key('github-event-payload')
                value('$')
                expressionType('JSONPath')
              }
            }
            genericHeaderVariables {
              genericHeaderVariable {
                key('x-github-event')
                regexpFilter('')
              }
            }
            causeString('GitHub Event: $x-github-event')
            tokenCredentialId('generic_webhook_token')
          }
        }
      }
    }
  }
  definition {
    cpsScm {
      lightweight(true)
      scm {
        git {
          remote {
            url(github_project_url)
            credentials(GITHUB_APP_CRED_ID)
          }
          branch('*/master')
        }
      }
      scriptPath('Jenkinsfile')
    }
  }
}
