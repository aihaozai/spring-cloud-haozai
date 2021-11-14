FROM dataanalysis-docker.pkg.coding.net/spring-cloud-haozai/image/openjdk-font:8-jdk-alpine

ADD target/spring-cloud-fund-1.0-SNAPSHOT.jar app.jar

ENV SW_AGENT_NAME="fund"

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-javaagent:/skywalking/agent/skywalking-agent.jar", "-jar", "/app.jar"]