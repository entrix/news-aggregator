#!/bin/sh
../gradlew clean build docker
../gradlew generateDockerCompose
../docker-compose up --build "$@"