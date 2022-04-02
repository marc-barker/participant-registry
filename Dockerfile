# Smallest base container with JDK17
FROM openjdk:17-alpine
WORKDIR /participant-registry

COPY . ./

# Run the server
ENTRYPOINT ["./gradlew","bootRun"]
