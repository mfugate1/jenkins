job ('Update-Jenkins') {
    label('docker')
    triggers {
        GenericTrigger {
            regexpFilterExpression('')
            regexpFilterText('')
            tokenCredentialId('update-jenkins-token')
        }
    }
    wrappers {
        sshAgent('docker1-ssh')
    }
    steps {
        shell(readFileFromWorkspace('update-jenkins.sh'))
    }
}