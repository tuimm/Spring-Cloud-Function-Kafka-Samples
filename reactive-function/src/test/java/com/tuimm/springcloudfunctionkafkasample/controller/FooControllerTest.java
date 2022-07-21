package com.tuimm.reactivefunction.controller;

import com.tuimm.reactivefunction.controller.model.FooGenerationRequestDto;
import com.tuimm.reactivefunction.event.header.FooEventVersion;
import com.tuimm.reactivefunction.service.FooEventGenerationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FooControllerTest {

  @InjectMocks
  private FooController controller;

  @Mock
  private FooEventGenerationService fooEventGenerationService;

  @Test
  void generateFoo() {
    // Given
    var fooGenerationRequest = FooGenerationRequestDto.builder()
      .eventVersion(FooEventVersion.PAYLOAD_TYPE_V2)
      .quantity(4L)
      .build();

    when(fooEventGenerationService.generate(fooGenerationRequest.getEventVersion(), fooGenerationRequest.getQuantity()))
      .thenReturn(Mono.empty());

    // When
    var publisher = controller.generateFoo(fooGenerationRequest);

    // Then
    StepVerifier.create(publisher).verifyComplete();
  }

}
