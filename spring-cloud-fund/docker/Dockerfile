FROM openjdk:8-jdk-alpine

ADD /spring-cloud-fund/target/spring-cloud-fund-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]