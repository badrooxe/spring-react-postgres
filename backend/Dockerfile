# ----------------------------
# STEP 1: Use lightweight JDK to run the app
# ----------------------------
FROM eclipse-temurin:21-jre-alpine

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file into the container
# Replace demo-0.0.1-SNAPSHOT.jar with your actual jar file name
COPY demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port (should match your Spring Boot server.port, usually 8080)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
