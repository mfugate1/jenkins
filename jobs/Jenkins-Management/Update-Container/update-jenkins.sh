cat << EOF > update-jenkins.sh
cd /docker/server-management/truenas-docker
git pull
docker-compose pull
sleep 5
docker-compose up -d --force-recreate
EOF
chmod +x update-jenkins.sh
scp -o StrictHostKeyChecking=no update-jenkins.sh jenkins@${JENKINS_IP}:~/
ssh -o StrictHostKeyChecking=no jenkins@${JENKINS_IP} 'nohup ./update-jenkins.sh > /dev/null 2>&1 &'