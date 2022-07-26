FROM openjdk:17
EXPOSE 8090
ADD target/mifort-automation-api.jar mifort-automation-api.jar
ENTRYPOINT ["java", "-jar", "mifort-automation-api.jar"]