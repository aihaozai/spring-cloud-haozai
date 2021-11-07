FROM apache/skywalking-base:8.7.0-es7

ADD target/spring-cloud-fund-1.0-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai

#openjdk:8-jdk-alpine没有zoneinfo
#RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/$TZ /etc/localtime

RUN echo 'plugin.toolkit.log.grpc.reporter.server_host=${SW_GRPC_LOG_SERVER_HOST:127.0.0.1}
          plugin.toolkit.log.grpc.reporter.server_port=${SW_GRPC_LOG_SERVER_PORT:11800}
          plugin.toolkit.log.grpc.reporter.max_message_size=${SW_GRPC_LOG_MAX_MESSAGE_SIZE:10485760}
          plugin.toolkit.log.grpc.reporter.upstream_timeout=${SW_GRPC_LOG_GRPC_UPSTREAM_TIMEOUT:30}' >>/skywalking/agent/config/agent.config

ENV SW_AGENT_NAME="fund"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app.jar"]