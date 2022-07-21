# spring-cloud-function-kafka-samples

This project shows how to implement the kafka client with spring cloud function. 
There are several uses cases that has been covered with separated modules:
1. Yo need to consume from a topic and produce an event as a result of the input event consumed. The implementation is has been done with a reactive function that allow us to just call once the function and consume infinitely:
https://github.com/tuimm/spring-cloud-function-kafka-samples/tree/main/reactive-function
3. You just need to consume from a topic and process the event synchronously. The implementation is has been done with a consumer function:
https://github.com/tuimm/spring-cloud-function-kafka-samples/tree/main/synchronous-consumer
