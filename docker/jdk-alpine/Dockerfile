FROM openjdk:8-jdk-alpine

ENV TZ=Asia/Shanghai

RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/$TZ /etc/localtime

RUN apk add --update ttf-dejavu fontconfig

#cd docker/jdk-alpine
#docker build -t dataanalysis-docker.pkg.coding.net/spring-cloud-haozai/image/openjdk-font:8-jdk-alpine .