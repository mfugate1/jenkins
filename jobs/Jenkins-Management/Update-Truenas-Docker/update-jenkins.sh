cat << EOF > update-jenkins.sh
cd /docker/server-management/truenas-docker
git pull
docker-compose pull
sleep 5
docker-compose up -d --force-recreate
EOF
cat << EOF > jenkins-secrets.properties
AZ_VAULT_URL=$AZ_VAULT_URL
AZURE_CLIENT_ID=$AZURE_CLIENT_ID
AZURE_CLIENT_SECRET=$AZURE_CLIENT_SECRET
AZURE_SUBSCRIPTION_ID=$AZURE_SUBSCRIPTION_ID
AZURE_TENANT=$AZURE_TENANT
EOF
chmod +x update-jenkins.sh
scp -o StrictHostKeyChecking=no update-jenkins.sh jenkins@${TRUENAS_DOCKER_IP}:~/
scp -o StrictHostKeyChecking=no jenkins-secrets.properties jenkins@${TRUENAS_DOCKER_IP}:/docker/jenkins/
ssh -o StrictHostKeyChecking=no jenkins@${TRUENAS_DOCKER_IP} 'nohup ./update-jenkins.sh > /dev/null 2>&1 &'