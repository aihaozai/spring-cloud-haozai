FROM openjdk:8-jdk-alpine

ENV TZ=Asia/Shanghai

RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/$TZ /etc/localtime

RUN apk add --update ttf-dejavu fontconfig