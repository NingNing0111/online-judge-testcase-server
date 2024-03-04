FROM openjdk:17-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ./target/oj-ludd-testcase-server-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]