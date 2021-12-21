job ('Jenkins-Update-Truenas-Docker') {
    label('docker')
    triggers {
        GenericTrigger {
            regexpFilterExpression('')
            regexpFilterText('')
            tokenCredentialId('JENKINS-TRUENAS-DOCKER-UPDATE-TOKEN')
        }
    }
    wrappers {
        sshAgent('docker1-ssh')
        withAzureKeyvault {
            azureKeyVaultSecrets {
                azureKeyVaultSecret {
                    envVariable('AZ_VAULT_URL')
                    name('AZ-VAULT-URL')
                    secretType('Secret')
                }
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
                    envVariable('TRUENAS_DOCKER_IP')
                    name('TRUENAS-DOCKER-IP')
                    secretType('Secret')
                }
            }
        }
    }
    steps {
        shell(readFileFromWorkspace('jobs/Jenkins-Management/Update-Truenas-Docker/update-jenkins.sh'))
    }
}