FROM debian:bullseye-slim
FROM openjdk:11


WORKDIR /TDD-Uppgift-GruppPhilipA




COPY build.gradle .
COPY settings.gradle .


COPY gradle/ ./gradle/
COPY gradlew .


COPY src/ src/

RUN ./gradlew build


CMD ["java", "-jar", "build/libs/TDD-Uppgift-GruppPhilipA-1.0-SNAPSHOT.jar"]
