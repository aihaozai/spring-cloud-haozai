FROM apache/skywalking-base:8.7.0-es7

ADD target/spring-cloud-fund-1.0-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai

#openjdk:8-jdk-alpine没有zoneinfo
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/$TZ /etc/localtime

ENV SW_AGENT_NAME="fund"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app.jar"]