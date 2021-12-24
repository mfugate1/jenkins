pipelineJob ('Update-Secrets') {
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
                scriptPath('jobs/Update-Secrets/Jenkinsfile')
            }
        }
    }
    properties {
        parameters {
            parameterDefinitions {
                string {
                    name('validationUrl')
                }
                string {
                    name('updatedSecrets')
                }
            }
        }
        pipelineTriggers {
            triggers {
                GenericTrigger {
                    regexpFilterExpression('')
                    regexpFilterText('')
                    tokenCredentialId('AZURE-KEY-EVENT-TOKEN')
                    genericVariables {
                        genericVariable {
                            key('validationUrl')
                            value('$.[0].data.validationUrl')
                            expressionType('JSONPath')
                        }
                        genericVariable {
                            key('updatedSecrets')
                            value('$.*.data.ObjectName')
                            expressionType('JSONPath')
                        }
                    }
                }
            }
        }
    }
}