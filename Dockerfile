FROM openjdk:11.0.5-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} api-estacionamento-digital.jar
EXPOSE 8887
ENTRYPOINT ["java","-jar","/api-estacionamento-digital.jar"]