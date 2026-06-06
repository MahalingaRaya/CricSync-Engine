# ==========================================
# Stage 1: Build the Application
# ==========================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml first to cache dependencies
COPY pom.xml .
# Copy the actual source code
COPY src ./src

# Build the application, skipping tests to speed up deployment
RUN mvn clean package -DskipTests

# ==========================================
# Stage 2: Run the Application
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar file using your wildcard approach
COPY --from=build /app/target/*.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
