FROM openjdk:17

EXPOSE 8080

ARG JAR_FILE

ADD target/${JAR_FILE} /cs7cs3.jar

ENTRYPOINT ["java", "-jar","/cs7cs3.jar"]