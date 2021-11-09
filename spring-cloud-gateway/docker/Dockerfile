FROM apache/skywalking-base:8.7.0-es7

ADD target/spring-cloud-gateway-1.0-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai

#openjdk:8-jdk-alpine没有zoneinfo
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/$TZ /etc/localtime

#gateway插件
RUN cp /skywalking/agent/optional-plugins/apm-mybatis-3.x-plugin-8.7.0.jar /skywalking/agent/plugins
RUN cp /skywalking/agent/optional-plugins/apm-spring-webflux-5.x-plugin-8.7.0.jar /skywalking/agent/plugins

ENV SW_AGENT_NAME="gateway"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app.jar"]