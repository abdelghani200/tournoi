FROM openjdk:17
COPY target/docker-java.jar docker-java.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "docker-java.jar"]