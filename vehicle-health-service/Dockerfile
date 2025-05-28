# Use a minimal base JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar file (assumes you built it with Maven or Gradle)
COPY target/vehicle-health-service-0.0.1-SNAPSHOT.jar app.jar


# Expose application port
EXPOSE 8081

# Entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
