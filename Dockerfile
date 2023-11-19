FROM maven:3.9-amazoncorretto-17 as build

COPY src src
COPY pom.xml pom.xml

RUN mvn -DskipTests=true  package

FROM amazoncorretto:17.0.9-al2023-headless

WORKDIR ./app

COPY --from=build target/lib-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java", "-jar", "./application.jar"]

ENV DATABASE_URL=jdbc:postgresql://postgres_db:5432/postgres