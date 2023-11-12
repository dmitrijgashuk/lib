FROM maven:3.6-openjdk-17

COPY src src
COPY pom.xml pom.xml

RUN mvn package

FROM openjdk:17

WORKDIR /app

COPY --from=build target/lib-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./appliction.jar"]