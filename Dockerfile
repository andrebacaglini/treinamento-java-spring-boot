FROM openjdk:8-jdk-alpine
COPY ./target/treinamento-0.0.1-SNAPSHOT.jar /usr/src/myapp/app.jar
WORKDIR /usr/src/myapp
CMD ["java", "-jar", "app.jar"]