# kafka-springboot-poc
Proof of concept of Kafka using Springboot

This project use a kafka server using wurstmeister/kafka image (https://hub.docker.com/r/wurstmeister/kafka)
Use for docker compose the file **docker-compose-kafka-server.yml**
```
docker-compose -f docker-compose-kafka-server.yml up -d
```


Package SpringBoot app
```
mvn clean package
```
Build docker image
```
docker build -t kafka-poc .
```
Run container
```
docker run -p 8080:8080 --network kafka-poc-network kafka-poc
```
