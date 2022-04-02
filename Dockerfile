# Smallest base container with JDK17
FROM openjdk:17-alpine
WORKDIR /participant-registry

# Build the project (so tests pass) and produce an executable jar file
COPY . ./
CMD ["./gradlew", "build"]

# Run the server with the custom docker configuration (uses docker hostname resolution)
ENTRYPOINT ["java","-jar","build/libs/participant-registry-0.0.1.jar"]
