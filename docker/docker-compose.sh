#!/bin/bash
cd /data/spring-cloud-haozai/
project_path=$(cd `dirname $0`; pwd)
echo $project_path
git pull
mvn clean package -Dmaven.test.skip=true
docker-compose -f docker-compose.yml  up --build -d
docker rmi $(docker images | grep none | awk '{print $3}')
