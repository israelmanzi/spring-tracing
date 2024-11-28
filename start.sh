#!/bin/bash

docker compose down

if [ ! -f ./otel-agent.jar ]; then
  wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.10.0/opentelemetry-javaagent.jar -O ./otel-agent.jar
fi

if [ ! -d ./target ] || [ ! -f ./target/spring-boot-docker-postgres.jar ]; then
  mvn clean package
fi

docker compose up --build
