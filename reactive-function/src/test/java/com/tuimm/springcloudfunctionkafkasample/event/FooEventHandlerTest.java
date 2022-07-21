package com.tuimm.reactivefunction.event;

import com.tuimm.reactivefunction.event.header.EventHeaders;
import com.tuimm.reactivefunction.event.header.FooEventVersion;
import com.tuimm.reactivefunction.domain.model.Foo;
import com.tuimm.reactivefunction.service.FooService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.integration.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static com.tuimm.reactivefunction.event.header.EventHeaders.PAYLOAD_TYPE_HEADER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FooEventHandlerTest {

  @InjectMocks
  private FooEventHandler fooEventHandler;

  @Mock
  private FooService fooService;

  @Test
  void consumeAndPublishV2() {
    // Given
    var foo = Foo.builder()
      .id(UUID.randomUUID())
      .name("name")
      .description("description")
      .build();

    var inputEventMessage = MessageBuilder
      .withPayload(foo)
      .setHeader(EventHeaders.PAYLOAD_TYPE_HEADER, FooEventVersion.PAYLOAD_TYPE_V2.getValue())
      .build();

    when(fooService.save(foo)).thenReturn(Mono.just(foo));

    // When
    var publisher = fooEventHandler.consumeAndPublishV2().apply(Flux.just(inputEventMessage));

    // Then
    StepVerifier.create(publisher).assertNext(message -> {
      assertEquals(inputEventMessage.getPayload(), message.getPayload());
      assertEquals(inputEventMessage.getHeaders().get(EventHeaders.PAYLOAD_TYPE_HEADER), message.getHeaders().get(PAYLOAD_TYPE_HEADER));
    }).verifyComplete();
  }

  @Test
  void consumeAndPublishV1_discardedV1Event() {
    // Given
    var foo = Foo.builder()
      .id(UUID.randomUUID())
      .name("name")
      .description("description")
      .build();

    var eventMessage = MessageBuilder
      .withPayload(foo)
      .setHeader(EventHeaders.PAYLOAD_TYPE_HEADER, FooEventVersion.PAYLOAD_TYPE_V1.getValue())
      .build();

    // When
    var publisher = fooEventHandler.consumeAndPublishV2().apply(Flux.just(eventMessage));

    // Then
    StepVerifier.create(publisher).verifyComplete();
  }
}
