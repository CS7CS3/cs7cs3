FROM maven:3.8-openjdk-17 AS build
ADD . /src
RUN mvn -f /src/pom.xml clean package

FROM openjdk:17

COPY --from=build /src/target/JourneySharing-0.0.1-SNAPSHOT.jar /app/
COPY --from=build /src/tables.sql /app/

CMD  ["java", "-jar","/app/JourneySharing-0.0.1-SNAPSHOT.jar"]