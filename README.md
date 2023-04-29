# Drones App

Some informative information about:
* Tools/Tech
* Running the application
* Assumptions

## Tools/Tech

### Spring Boot

1. Starter JPA
2. Starter WEB
3. Starter Test

### Building

1. Maven - wrapper included in the project

### Language

1. Java 11

### Others

1. Lombok - Annotations
2. Hibernate validations - Bean validation implementation
3. h2 - In memory DB

## Running the service locally

To run the service locally, do the following:
1. Execute `./mvnw clean install` to fetch all dependencies. This uses the maven wrapper (no need to install it)
2. Execute `./mvnw spring-boot:run` to get the application running
   1. It is configured to run in port 8090
3. Execute `./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000"` to run in debug mode
4. Once the application is running, a set of Drones, Medications and its relationships are preloaded.
5. Use the Postman collections provided along with the app to interact with the Rest API.

## Assumptions/Clarifications

### Design

1. There is not a limit in the amount of medications available, so they can be loaded to any drone always they have availability
2. Medications are created separately and later loaded into drones
3. Only drones in IDLE or LOADING state are available to receive more load
4. Serial Number is unique for Drones, if a drone with a serial number taken by an existing one registered in DB is received, the creation is rejected
5. Code is unique for Medications, same condition as above
6. The picture for medications is just a link to a source somewhere in the internet, images should not be stored in DB
7. The scheduled task is intended to be executed every day, so that the battery level is audited and stored in the table `battery_log`
   1. Per implementation, it is set to execute every 10 seconds - just to see it working
8. Just some tests are implemented to demonstrate its usage. Not all scenarios are covered
9. Used java.util.Date instead of java 8 new LocalDateTime or LocalDate to avoid extra configuration due to lack of time.

## Clarifications

1. Skipping implementation of the full set of tests due to lack of time.
2. Skipping documentation of method and classes due to lack of time.