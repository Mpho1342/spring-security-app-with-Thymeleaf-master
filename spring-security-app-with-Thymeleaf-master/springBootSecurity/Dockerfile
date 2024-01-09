# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY target/springBootSecurity.jar /app/springBootSecurity.jar

# Expose the port that your Spring Boot application listens on (default is 8080)
EXPOSE 8080

# Define environment variables (if needed)
# ENV VARIABLE_NAME=value

# Command to run your Spring Boot application
CMD ["java", "-jar", "springBootSecurity.jar"]

