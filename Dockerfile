# Multi-stage Dockerfile: build with Maven, run with a small JRE image
FROM maven:3.9.4-eclipse-temurin-17 AS builder
WORKDIR /workspace

# Copy sources and build the application (skip tests for faster builds)
COPY pom.xml ./
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the built jar from the builder stage
ARG JAR_FILE=target/*.jar
COPY --from=builder /workspace/${JAR_FILE} /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
