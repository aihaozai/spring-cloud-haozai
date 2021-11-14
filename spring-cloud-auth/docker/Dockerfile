FROM dataanalysis-docker.pkg.coding.net/spring-cloud-haozai/image/openjdk-font-skywalking:8-jdk-alpine

ADD target/spring-cloud-auth-1.0-SNAPSHOT.jar app.jar

ENV SW_AGENT_NAME="auth"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/agent/skywalking-agent.jar", "-jar", "/app.jar"]