pipelineJob("Jenkins-Build-Agent-Images") {
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/mfugate1/jenkins.git')
                    }
                    branch('main')
                }
                lightweight()
                scriptPath('agent-images/Jenkinsfile')
            }
        }
    }
    properties {
        pipelineTriggers {
            triggers {
                githubPush()
            }
        }
    }
}