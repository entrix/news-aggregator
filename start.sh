#!/bin/sh
echo "This script prepare and run all infrastructure for bubble investing"
sh ./gradlew clean build dockerBuildImage
docker-compose up -d --build "$@"