FROM openjdk:8-jdk-alpine

ADD target/spring-cloud-tx-manage-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]