Flights\src\main\resources\application.properties -> here is the configuration file. You need to enter you database here :

Example
spring.datasource.url=jdbc:mysql://localhost:3306/flights
spring.datasource.username=****
spring.datasource.password=****

Endpoints:
-flight/getAllFlightsByPilot   GET
-flight/create                 POST
-flight/getFlight/7            GET
-flight/deleteById/4           DELETE

data field pattern: "yyyy-MM-dd HH:mm:ss" -> "2025-05-19 00:12:59"

AirplaneType{ AIRLINER, CARGO_PLANE, PRIVATE_JET, HEAVY_JET}
