pipelineJob ('Check-For-Jenkins-Update') {
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
                scriptPath('jobs/Jenkins-Management/Check-For-Jenkins-Update/Jenkinsfile')
            }
        }
    }
    properties {
        pipelineTriggers {
            triggers {
                cron {
                    spec('H 3 * * *')
                }
            }
        }
    }
}