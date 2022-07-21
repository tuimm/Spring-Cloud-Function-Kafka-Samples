# Spring Cloud Function Kafka Sample

Sample service that implements a reactive consumer with Spring Cloud Functions. The message processing by the consumer includes the following steps:

1. Consumes the message from a kafka input topic (foo.input.event).
2. Checks the version of the message indicated in the header 'payload-type'. Messages from 2 different versions coexist in the input topic ('vnd.foo.v1' and 'vnd.foo.v2'), but only
   messages from version 'vnd.foo.v2' are processed (the others are discarded).
3. Saves the message payload in a MongoDB database.
4. Publishes another message in a different kafka topic (foo.output.event) with the previously saved entity.

Additionally, an endpoint is available for generating and sending test messages to the input topic. Swagger is available at: http://localhost:8080/swagger-ui.html.

## Requirements

- **Java 17**
- **Maven**
- **Lombok**
- A running instance of **MongoDB**. Configure it in the application.yml file.
- A running instance of **Kafka** and **Zookeeper**. Configure the broker in the application.yml file.
