# ----------------------------
# STAGE 1: Build the App
# ----------------------------
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src

# Build the JAR (skipping tests to be faster)
RUN mvn clean package -DskipTests

# ----------------------------
# STAGE 2: Run the App
# ----------------------------
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the JAR from the build stage (renaming it to app.jar)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]