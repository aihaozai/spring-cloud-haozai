FROM dataanalysis-docker.pkg.coding.net/spring-cloud-haozai/image/openjdk-font-skywalking:8-jdk-alpine

ADD target/spring-cloud-weixin-1.0-SNAPSHOT.jar app.jar


ENV LANG="zh_CN.UTF-8"

ENV SW_AGENT_NAME="weixin"

ENTRYPOINT ["tini", "java", "-Djava.io.tmpdir=/tmp", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/agent/skywalking-agent.jar", "-jar", "/app.jar"]