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
                    envVariable('AZURE_CLIENT_ID')
                    name('AZURE-CLIENT-ID')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE_CLIENT_SECRET')
                    name('AZURE-CLIENT-SECRET')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE_SUBSCRIPTION_ID')
                    name('AZURE-SUBSCRIPTION-ID')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('AZURE_TENANT')
                    name('AZURE-TENANT')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('DOCKER1_SSH_PRIVATE_KEY')
                    name('DOCKER1-SSH-PRIVATE-KEY')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('GARMIN_CONNECT_PASSWORD')
                    name('GARMIN-CONNECT-PASSWORD')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('GARMIN_CONNECT_USERNAME')
                    name('GARMIN-CONNECT-USERNAME')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_ADMIN_EMAIL')
                    name('JENKINS-ADMIN-EMAIL')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_IP')
                    name('JENKINS-IP')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_PASSWORD')
                    name('JENKINS-PASSWORD')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_PORT')
                    name('JENKINS-PORT')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_TRUENAS_DOCKER_UPDATE_TOKEN')
                    name('JENKINS-TRUENAS-DOCKER-UPDATE-TOKEN')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('JENKINS_USERNAME')
                    name('JENKINS-USERNAME')
                    secretType('Secret')
                }
            }
        }
    }
    steps {
        shell(readFileFromWorkspace('jobs/Jenkins-Management/Update-Container/update-jenkins.sh'))
    }
}