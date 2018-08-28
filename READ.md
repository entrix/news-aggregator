#me.bubbleinvestor 

###Docker
[logo]: https://docs.docker.com/images/docker-docs-logo.svg "Logo"
You should have docker.



###Building 

IDE project
To build project

 `./gradlew idea `

To build and launch app 

launch `start.sh` in root dir

or use manual commands
 in root dir:
 `./gradlew clean build docker`
 
 To run
   `./gradlew dockerComposeUp`
   
[Docker gradle plugin](https://github.com/palantir/gradle-docker) help to work with docker
    
   
###ELK   
 
 Kibana starts on http://localhost:5601/
 
 [ELK in Docker](https://github.com/deviantony/docker-elk) help to work with elk in docker
 
 
 ###Kafka
 [Kafka in Docker](https://github.com/Landoop/fast-data-dev/) info about network interaction through kafka
 
 interact with kafka 
 `docker run --rm -it --net=host news-aggregator_kafka-lenses-dev bash`
 
 `kafka-topics --zookeeper 127.0.0.1:2181 --create --topic test --partitions 3 --replication-factor 1`  