#!/bin/bash

docker compose down

if [ ! -f ./otel-agent.jar ]; then
  wget https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.10.0/opentelemetry-javaagent.jar -O ./otel-agent.jar
fi

docker compose up --build
