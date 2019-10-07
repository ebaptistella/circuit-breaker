# circuit-breaker
Implements Circuit Breaker (SpringBoot+SpringCloud => Feign + Hystrix)

# Compile and Build

`mvn clean install`

This will create a jar file inside target folder, and it can be executed with:

`java -jar -Dspring.profiles.active=production -Dloader.path=../config/cb-custom-production.properties circuit-breaker-0.0.1-SNAPSHOT.jar` 


# API Doc
Documentation for API with Swagger access this:

`http://127.0.0.1:8080/circuit-breaker/swagger-ui.html`

# Security
For security I used Basic Authorization, credentials is:

*Username: user-1*

*Password: user-1*
