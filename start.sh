#!/bin/sh
echo "This script prepare and run all infrastructure for bubble investing"
sh ./gradlew clean build docker
sh ./gradlew generateDockerCompose
docker-compose up --build "$@"