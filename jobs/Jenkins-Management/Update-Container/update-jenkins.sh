cat << EOF > update-jenkins.sh
cd /docker/server-management/truenas-docker
git pull
docker-compose pull
sleep 5
docker-compose up -d --force-recreate
EOF
cat << EOF > jenkins-secrets.properties
AZURE_CLIENT_ID=$AZURE_CLIENT_ID
AZURE_CLIENT_SECRET=$AZURE_CLIENT_SECRET
AZURE_SUBSCRIPTION_ID=$AZURE_SUBSCRIPTION_ID
AZURE_TENANT=$AZURE_TENANT
DOCKER1_SSH_PRIVATE_KEY=$DOCKER1_SSH_PRIVATE_KEY
GARMIN_CONNECT_PASSWORD=$GARMIN_CONNECT_PASSWORD
GARMIN_CONNECT_USERNAME=$GARMIN_CONNECT_USERNAME
JENKINS_ADMIN_EMAIL=$JENKINS_ADMIN_EMAIL
JENKINS_IP=$JENKINS_IP
JENKINS_PASSWORD=$JENKINS_PASSWORD
JENKINS_PORT=$JENKINS_PORT
JENKINS_TRUENAS_DOCKER_UPDATE_TOKEN=$JENKINS_TRUENAS_DOCKER_UPDATE_TOKEN
EOF
chmod +x update-jenkins.sh
scp -o StrictHostKeyChecking=no update-jenkins.sh jenkins@${JENKINS_IP}:~/
scp -o StrictHostKeyChecking=no jenkins-secrets.properties jenkins@${JENKINS_IP}:/docker/jenkins/
ssh -o StrictHostKeyChecking=no jenkins@${JENKINS_IP} 'nohup ./update-jenkins.sh > /dev/null 2>&1 &'