package com.tuimm.reactivefunction.service;

import com.tuimm.reactivefunction.event.header.EventHeaders;
import com.tuimm.reactivefunction.event.header.FooEventVersion;
import com.tuimm.reactivefunction.domain.model.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FooEventGenerationService {

  private static final String BINDING_NAME = "generateFooEvent-out-0";

  private final StreamBridge streamBridge;

  public Mono<Void> generate(FooEventVersion eventVersion, Long quantity) {
    return Mono.just(new Foo())
      .map(foo -> generateMessage(foo, eventVersion))
      .doOnNext(message -> streamBridge.send(BINDING_NAME, message))
      .repeat(quantity - 1)
      .doOnNext(fooMessage -> log.debug("Event generated: {}", fooMessage))
      .then();
  }

  private Message<Foo> generateMessage(Foo foo, FooEventVersion eventVersion) {
    foo.setId(UUID.randomUUID());
    foo.setName("Name " + foo.getId());
    foo.setDescription("Description " + foo.getId());

    return MessageBuilder.withPayload(foo)
      .setHeader(EventHeaders.PAYLOAD_TYPE_HEADER, eventVersion.getValue())
      .build();
  }

}
