cd /docker/server-management/truenas-docker
git pull
docker-compose pull
sleep 5
docker-compose up -d --force-recreate