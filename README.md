# kafka-springboot-poc
Proof of concept of Kafka using Springboot

This project use a kafka server from wurstmeister/kafka image (https://hub.docker.com/r/wurstmeister/kafka).
Use for docker compose the file **docker-compose-kafka-server.yml** included in this project.
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

This SpringBoot application will create a topic named "newCustomerCreated" with 3 partitions and 3 replicas.
Create three instances of this application with docker-compose to observe how
each instance consume from each Kafka partition 
```
docker-compose scale kafka-poc=3
```
