## Problem Description
Ever since Spring Framework 6, the `client.name` tag from `http.client.requests` metrics are missing. 

## Steps to Reproduce
1. Create a Spring Boot 3.0 application (which comes with Spring Framework 6)
2. Add `spring-boot-starter-actuator` and `spring-boot-starter-web` dependencies
3. Run a task that periodically send requests to external endpoint using `RestTemplate`
4. Visit `/actuator/metrics/http.client.requests` endpoint and observe that the `client.name` tag is missing

### Sanity Check
1. Create a Spring Boot 2.7.6 application (which comes with Spring Framework 5)
2. Add `spring-boot-starter-actuator` and `spring-boot-starter-web` dependencies
3. Run a task that periodically send requests to external endpoint using `RestTemplate`
4. Visit `/actuator/metrics/http.client.requests` endpoint and observe that the `client.name` tag is present
