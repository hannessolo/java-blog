FROM openjdk:8-jre-alpine

COPY target/Blog-jar-with-dependencies.jar /app.jar

CMD ["/usr/bin/java", "-jar", "/app.jar"]
EXPOSE 4567