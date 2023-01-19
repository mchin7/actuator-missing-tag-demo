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

## Root Cause
In Spring Framework 6, `client.name` is considered as a high cardinality tag and therefore is only sent in _traces_ and not in _metrics_.
See https://github.com/spring-projects/spring-framework/issues/29839

The issue has a milestone tag of 6.0.5.

### Workaround
1. Implement a class, overriding `DefaultClientRequestObservationConvention#getLowCardinalityKeyValues` method to resolve `client.name` by calling `clientName(context)` method.
2. Register the class as a bean in the Spring context. Spring boot will pick it up during AutoConfiguration
3. Verify that the `client.name` tag is present in `/actuator/metrics/http.client.requests` endpoint