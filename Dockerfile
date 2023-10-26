FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
COPY --from=build /app/target/tech-challenge-0.0.1.jar /app/tech-challenge-0.0.1.jar
EXPOSE 8080
CMD ["java", "-jar", "tech-challenge-0.0.1.jar"]
