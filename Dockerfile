FROM openjdk:8-jdk-alpine
LABEL maintainer=mccantuta@yahoo.com
VOLUME /tmp
RUN apk add --no-cache bash
EXPOSE 8080
ADD target/kafka-poc-0.0.1-SNAPSHOT.jar kafka-poc.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/kafka-poc.jar"]
