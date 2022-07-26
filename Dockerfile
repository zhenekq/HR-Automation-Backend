FROM openjdk:17
EXPOSE 8090
ARG JAR_FILE=target/mifort-automation-api.jar
COPY ${JAR_FILE} mifort.jar
ADD target/mifort-automation-api.jar mifort-automation-api.jar
ENTRYPOINT ["java", "-jar", "/mifort.jar"]