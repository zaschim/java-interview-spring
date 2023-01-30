FROM eclipse-temurin:18.0.2.1_1-jre-alpine

RUN mkdir -p /app
WORKDIR /app

COPY target/libs ./libs
COPY target/sps-java-shared-spring-boot-example.jar .

EXPOSE 8080

ENTRYPOINT java -jar /app/sps-java-shared-spring-boot-example.jar
