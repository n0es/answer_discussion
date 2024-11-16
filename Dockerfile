# Build stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY .env .

# Build the application
RUN mvn clean compile package
RUN cp target/answer_discussion-1.0-SNAPSHOT.jar target/answer_discussion.jar


# Runtime stage
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/answer_discussion.jar .

# Run the application
ENTRYPOINT ["java", "-jar", "answer_discussion.jar"]