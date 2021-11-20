FROM dataanalysis-docker.pkg.coding.net/spring-cloud-haozai/image/openjdk-font-skywalking:8-jdk-alpine

ADD target/spring-cloud-auth-1.0-SNAPSHOT.jar app.jar


ENV LANG="zh_CN.UTF-8"

ENV SW_AGENT_NAME="auth"

ENTRYPOINT ["tini", "java", "-Djava.io.tmpdir=/tmp", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/agent/skywalking-agent.jar", "-jar", "/app.jar"]