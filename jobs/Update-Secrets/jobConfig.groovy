String script = '''\
echo "$validationUrl"
echo 'Running jobs to update everything that uses Azure secrets'
'''

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
        shell(script)
    }
}