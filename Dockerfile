FROM openjdk:17.0.2-jdk-slim
MAINTAINER FSE
COPY build/libs/test-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]