def sendNotification(buildStatus) {
    script {
        def themeColor = buildStatus == 'SUCCESS' ? '#00d75f' : (buildStatus == 'FAILURE' ? '#D22B2B' : '#5402fb')
        def buildStatusDisplay = buildStatus.capitalize()
        def WEBHOOK_URL='https://epsystemspk.webhook.office.com/webhookb2/3e656dc9-fbea-4ec7-a886-b80b81f60f7f@fa1b4296-dc5b-4f08-9606-45d98c6fb9de/IncomingWebhook/75f1f30fa78647248a645cf28e1667b2/a4d2d807-342d-4cde-8991-dfda3945ccd4/V2mWHsUoPkJ94Af-S9iIy_x6CymoDv0WxGRLRJc2j12ZM1'
        def AUTHOR = sh(script: "git log -n 1 --pretty=format:'%an %ae'", returnStdout: true).trim()
        def COMMIT_ID = sh(script: "git log -n 1 --pretty=format:'%H'", returnStdout: true).trim()
        def COMMIT_DATE = sh(script: "git log -n 1 --pretty=format:'%ad'", returnStdout: true).trim()
        def SUBJECT = sh(script: "git log -n 1 --pretty=format:'%s'", returnStdout: true).trim()

        def payload = """
        {
          "title": "CICD Pipeline Notification",
          "text": "Deployment Details",
          "themeColor": "${themeColor}",
          "sections": [
            {
              "facts": [
                {
                    "name": "<font color='${themeColor}'>BUILD STATUS:</font>",
                    "value": "<font color='${themeColor}'>${buildStatusDisplay}</font>"
                },
                {
                    "name": "BRANCH:",
                    "value": "${params.TAG}"
                },
                {
                    "name": "AUTHOR:",
                    "value": "${AUTHOR}"
                },
                {
                    "name": "COMMIT_ID:",
                    "value": "${COMMIT_ID}"
                },
                {
                    "name": "SUBJECT:",
                    "value": "${SUBJECT}"
                },
                {
                    "name": "COMMIT_DATE:",
                    "value": "${COMMIT_DATE}"
                },
                {
                  "name": "JOB NAME:",
                  "value": "${JOB_NAME}"
                },
                {
                    "name": "ENVIRONMENT:",
                    "value": "${params.ENVIRONMENTS}"
                }
              ]
            }
          ]
        }
        """
        
        sh """
        curl -X POST -H "Content-Type: application/json" \
        -d '${payload}' \
            ${WEBHOOK_URL} > /dev/null 2>&1
        """
    }
}
