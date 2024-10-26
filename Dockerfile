FROM openjdk:22
COPY target/poop-bot-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]