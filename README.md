# Microservices Basic Comunication Using Apache Kafka

This is a repository that consists in comunication between NodeJS (Consumer) file running and a SpringBoot API (Producer)

It simulates a ecommerce system that has for while logistics and orders Microservice

## Technologies / Tools

* Java 11
* Spring Boot
* Spring Rest
* Spring Kafka
* Apache Kafka
* Zookeeper
* KafDrop
* NodeJS


## Running Apache Kafka

You will need to have docker-compose and Docker installed on your machine to run Kafka
In the root folder ( / ) of the project exists a docker-compose.yml file, where are defined the containers for Zookeeper, Kafka and Kafdrop.

Run the command below to up the containers of Apache Kafka

```
docker-compose up -d 
```

## Runnning Orders Spring Boot

To run order microservice run the following commands:

```
cd orders
mvn.cmd spring-boot:run //On Windows
/.mvn spring-boot:run // On Linux
```

## Run Logistics NodeJS

To run logistics microservice you need to have npm installed on your machine, then run:

```
cd logistics-node-js
npm install
npm start
```

### Test the comunication

At the *orders* microservice we have a endpoint you can send the following post request:

```
curl --location --request POST 'http://localhost:8080/order' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 1,
    "clientName": "Rodrigo",
    "products": [
        50,52,80
    ],
    "addressId": 50
}'
```

You can see the sended message at Kafdrop at http://localhost:19000, and see at the NodeJS application console, the consumer receiving the message and logging order data.


Fell free to make a fork and do your changes.

