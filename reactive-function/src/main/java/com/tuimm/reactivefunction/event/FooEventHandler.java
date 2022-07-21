package com.tuimm.reactivefunction.event;

import com.tuimm.reactivefunction.event.header.EventHeaders;
import com.tuimm.reactivefunction.event.header.FooEventVersion;
import com.tuimm.reactivefunction.domain.model.Foo;
import com.tuimm.reactivefunction.service.FooService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Function;

import static com.tuimm.reactivefunction.event.header.EventHeaders.PAYLOAD_TYPE_HEADER;
import static com.tuimm.reactivefunction.event.header.FooEventVersion.PAYLOAD_TYPE_V2;

@Service
@RequiredArgsConstructor
@Log4j2
public class FooEventHandler {

  private final FooService fooService;

  @Bean
  public Function<Flux<Message<Foo>>, Flux<Message<Foo>>> consumeAndPublishV2() {
    return fooFlux -> fooFlux.filter(message -> isSupportedEventVersion(message, PAYLOAD_TYPE_V2))
      .doOnNext(message -> log.debug("Processing message: {}", message))
      .map(Message::getPayload)
      .flatMap(fooService::save)
      .map(savedFoo -> MessageBuilder.withPayload(savedFoo)
        .setHeader(EventHeaders.PAYLOAD_TYPE_HEADER, PAYLOAD_TYPE_V2.getValue())
        .build());
  }

  private boolean isSupportedEventVersion(Message<Foo> message, FooEventVersion supportedEventVersion) {
    var payloadType = message.getHeaders().get(PAYLOAD_TYPE_HEADER, String.class);
    return supportedEventVersion.getValue().equals(payloadType);
  }
}
