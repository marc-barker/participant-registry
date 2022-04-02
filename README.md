## Participant Registry

### What is this?

This project is a very simple example implementation of a registry storing basic person information (i.e. name, dob, phone number, address). Each person (`Participant`) is given a unique id. CRUD endpoints are exposed for interacting with the data, but no search endpoints are yet implemented. All data storage is transient (lost upon JVM exit).

### Getting started

You can build and run the project using `./gradlew bootRun` if you have Java 17 locally. 

Otherwise, you can build and run a docker image by executing the following command (from the root of the project, replace $PWD on Windows with %CD%):

`docker run --rm -p "8080:8080" -v "$PWD":/home/gradle/project -w /home/gradle/project gradle:jdk17-alpine gradle bootRun`

All endpoints will be exposed on `http://localhost:8080`!

### Endpoints

The following 4 endpoints are currently defined:

1. `createParticipant` - POST request, root path, body payload of `Participant`.
2. `loadParticipant` - GET request, root path with `{id}` path parameter.
3. `updateParticipant` - POST request, root path with `{id}` path parameter, body payload of `Participant`.
4. `deleteParticipant` - DELETE request, root path with `{id}` path parameter.

Example update participant request showing both path parameter and `Participant` body:

`curl --location --request POST 'http://localhost:8080/ed69337b-6b86-4c08-a44f-86391101291d' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Test User",
"dob": "1950-01-01",
"phoneNumber": "+61412345678",
"address": {
"state": "NSW",
"postcode": "2000",
"country": "Australia"
}
}'`

### Architecture

This project uses the following technologies:
1. Gradle for the build system.
2. Docker for containerization.
3. Spring Boot (Java 17, likely backwards compatible) for the web server.
4. JUnit and AssertJ for testing.
5. Java Immutables for custom serialization objects (see `Address` and `Participant`).

