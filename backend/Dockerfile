# -----------------------------
# STEP 1: Build the backend
# -----------------------------
FROM maven:3.9-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom.xml and download dependencies (faster rebuilds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the backend code
COPY src ./src

# Build the jar
RUN mvn clean package -DskipTests

# -----------------------------
# STEP 2: Run the app
# -----------------------------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
