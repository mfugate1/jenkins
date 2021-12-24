String jenkinsfile = '''\
if (params.updatedSecrets) {

    // Can't use join directly on the result from readJSON, but collect
    // converts it to a normal list that we can use join on
    String secrets = readJSON(text: params.updatedSecrets).collect{it}.join(',')

    build (
        job: 'Update-Home-Assistant-Config/main',
        wait: false,
        parameters: [
            string(name: 'updatedSecrets', value: secrets)
        ]
    )
} else {
    echo 'No secrets to update'
}
'''


pipelineJob ('Update-Secrets') {
    definition {
        cps {
            script(jenkinsfile)
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