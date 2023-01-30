#!/bin/bash
set -e
# This provides a small script sample of what it looks like to build the runtime docker container

# if you want to rebuild the jar, or you can comment this out
./mvnw clean package

# builds the docker image
docker build -t spring-boot-example .

# runs the image, it will forward the port so you can see it at http://localhost:8080
docker run -it \
-e ENV=test \
-p 8080:8080 \
spring-boot-example
