FROM openjdk:23-jdk-slim

WORKDIR /app

COPY target/conferencias-metaphorce-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
