FROM  openjdk:11.0.15-slim
VOLUME /main
ARG JAR_FILE=/build/libs/*.jar
COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
