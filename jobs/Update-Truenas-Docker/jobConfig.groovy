String script = '''\
cat << EOF > update-jenkins.sh
cd /docker/server-management/truenas-docker
git pull
docker-compose pull
sleep 5
docker-compose up -d --force-recreate
rm -f /docker/jenkins/jenkins-secrets.properties
EOF
cat << EOF > jenkins-secrets.properties
AZ_VAULT_URL=$AZ_VAULT_URL
AZURE_CLIENT_ID=$AZURE_CLIENT_ID
AZURE_CLIENT_SECRET=$AZURE_CLIENT_SECRET
AZURE_SUBSCRIPTION_ID=$AZURE_SUBSCRIPTION_ID
AZURE_TENANT=$AZURE_TENANT
DOCKER1_SSH_PRIVATE_KEY=$DOCKER1_SSH_PRIVATE_KEY
GITHUB_SSH_KEY=$GITHUB_SSH_KEY
EOF
chmod +x update-jenkins.sh
scp -o StrictHostKeyChecking=no update-jenkins.sh jenkins@${TRUENAS_DOCKER_IP}:~/
scp -o StrictHostKeyChecking=no jenkins-secrets.properties jenkins@${TRUENAS_DOCKER_IP}:/docker/jenkins/
ssh -o StrictHostKeyChecking=no jenkins@${TRUENAS_DOCKER_IP} 'nohup ./update-jenkins.sh > /dev/null 2>&1 &'
'''

job ('Update-Truenas-Docker') {
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
                    envVariable('DOCKER1_SSH_PRIVATE_KEY')
                    name('DOCKER1-SSH-PRIVATE-KEY')
                    secretType('Secret')
                }
                azureKeyVaultSecret {
                    envVariable('GITHUB_SSH_KEY')
                    name('GITHUB-SSH-KEY')
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
        shell(script)
    }
}