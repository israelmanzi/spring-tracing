FROM openjdk:17

WORKDIR /app

ADD target/spring-boot-docker-postgres.jar spring-boot-docker-postgres.jar

ENTRYPOINT ["java", "-javaagent:/otel-agent.jar", "-Dotel.service.name=web-app", "-jar", "spring-boot-docker-postgres.jar"]
