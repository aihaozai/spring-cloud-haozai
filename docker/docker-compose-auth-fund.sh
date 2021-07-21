#!/bin/bash
cd /data/spring-cloud-haozai/
project_path=$(cd `dirname $0`; pwd)
git pull
mvn clean package -Dmaven.test.skip=true
cd docker
docker-compose -f docker-compose-auth-fund.yml  up --build -d
docker images
docker rmi $(docker images | grep none | awk '{print $3}')
