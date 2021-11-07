FROM apache/skywalking-base:8.7.0-es7

ADD target/spring-cloud-auth-1.0-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

#openjdk:8-jdk-alpine没有zoneinfo
#RUN apk add -U tzdata && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

#ENV SW_AGENT_NAME="auth"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "-Dskywalking.agent.service_name=auth", "-Dskywalking.collector.backend_service=101.35.6.20:11800", "/app.jar"]