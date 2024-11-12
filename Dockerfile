FROM openjdk:17-jdk-slim
COPY target/user-registration-api.jar user-registration-api.jar
ENTRYPOINT ["java", "-jar", "user-registration-api.jar"]
