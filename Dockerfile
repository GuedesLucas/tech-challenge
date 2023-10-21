# Use the official OpenJDK 17 image as a parent image
FROM adoptopenjdk:17-jre

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (assuming it's named "techchallenge.jar") from the build directory into the container
COPY target/techchallenge.jar /app/techchallenge.jar

# Expose the port that the application will run on
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "techchallenge.jar"]
