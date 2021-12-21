job ('Update-Secrets') {
    label('docker')
    properties {
        parameters {
            parameterDefinitions {
                string {
                    name('validationUrl')
                }
            }
        }
    }
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
            }
        }
    }
    steps {
        shell(readFileFromWorkspace('jobs/Update-Secrets/update-secrets.sh'))
    }
}