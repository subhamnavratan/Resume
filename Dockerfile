# ---------- STAGE 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom.xml
COPY RESUME/pom.xml .
RUN mvn dependency:go-offline -B

# Copy source
COPY RESUME/src ./src

# Build
RUN mvn clean package -DskipTests


# ---------- STAGE 2: Run ----------
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
