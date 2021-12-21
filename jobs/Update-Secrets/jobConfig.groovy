job ('Update-Secrets') {
    label('docker')
    triggers {
        GenericTrigger {
            regexpFilterExpression('')
            regexpFilterText('')
            tokenCredentialId('AZURE-KEY-EVENT-TOKEN')
        }
    }
    steps {
        shell(readFileFromWorkspace('jobs/Update-Secrets/update-secrets.sh'))
    }
}