FROM openjdk:8u141
VOLUME /tmp
ARG JAR_NAME
ADD ${JAR_NAME} app.jar
ENV JAVA_OPTS="-Dspring.profiles.active=docker"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
