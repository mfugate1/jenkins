job ('Jenkins-Update-Container') {
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
        shell(readFileFromWorkspace('jobs/Jenkins-Management/Update-Container/update-jenkins.sh'))
    }
}