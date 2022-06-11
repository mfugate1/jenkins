String jobScript = 'addSSHCredentials("JENKINS-SSH-KEY", "jenkins-ssh", "jenkins", "Jenkins SSH Key")'

pipelineJob("Add-SSH-Credentials") {
    definition {
        cps {
            script(jobScript)
            sandbox(true)
        }
    }
    properties {
        pipelineTriggers {
            triggers {
                hudsonStartupTrigger {
                    label("built-in")
                    nodeParameterName("")
                    quietPeriod("0")
                    runOnChoice("Run on initial connection")
                }
            }
        }
    }
}