FROM openjdk:17.0.2-jdk-slim
MAINTAINER FSE
COPY out/artifacts/test_jar/test-0.0.1-SNAPSHOT.jar fse-app.jar
ENTRYPOINT ["java","-jar","/fse-app.jar"]