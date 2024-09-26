FROM openjdk:17
WORKDIR /app
ARG APP_JAR=target/*.jar
COPY ${APP_JAR} app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080