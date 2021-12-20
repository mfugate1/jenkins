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
        withAzureKeyvault {
            azureKeyVaultSecrets {
                azureKeyVaultSecret {
                    envVariable('AZURE-CLIENT-ID')
                    name('AZURE_CLIENT_ID')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE-CLIENT-SECRET')
                    name('AZURE_CLIENT_SECRET')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE-SUBSCRIPTION-ID')
                    name('AZURE_SUBSCRIPTION_ID')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE-TENANT')
                    name('AZURE_TENANT')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('DOCKER1-SSH-PRIVATE-KEY')
                    name('DOCKER1_SSH_PRIVATE_KEY')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('GARMIN-CONNECT-PASSWORD')
                    name('GARMIN_CONNECT_PASSWORD')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('GARMIN-CONNECT-USERNAME')
                    name('GARMIN_CONNECT_USERNAME')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS-ADMIN-EMAIL')
                    name('JENKINS_ADMIN_EMAIL')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS-IP')
                    name('JENKINS_IP')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS-PASSWORD')
                    name('JENKINS_PASSWORD')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS-PORT')
                    name('JENKINS_PORT')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS-TRUENAS-DOCKER-UPDATE-TOKEN')
                    name('JENKINS_TRUENAS_DOCKER_UPDATE_TOKEN')
                    secretType('Secret')
                }
            }
        }
    }
    steps {
        shell(readFileFromWorkspace('jobs/Jenkins-Management/Update-Container/update-jenkins.sh'))
    }
}