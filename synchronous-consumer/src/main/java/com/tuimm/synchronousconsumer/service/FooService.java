package com.tuimm.synchronousconsumer.service;

import com.tuimm.synchronousconsumer.controller.model.EventVersion;
import com.tuimm.synchronousconsumer.controller.model.FooRequestDTO;
import com.tuimm.synchronousconsumer.event.model.FooEventV1;
import com.tuimm.synchronousconsumer.event.model.FooEventV2;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FooService {
  private final StreamBridge streamBridge;

  public void sendEvent(FooRequestDTO fooRequestDTO) {
    var eventVersion = fooRequestDTO.getVersion();
    if (eventVersion == EventVersion.V1) {
      sendEventV1(fooRequestDTO);
    } else {
      sendEventV2(fooRequestDTO);
    }
  }

  private void sendEventV1(FooRequestDTO fooRequestDTO) {
    FooEventV1 fooEvent = new FooEventV1();
    fooEvent.setId(String.valueOf(UUID.randomUUID()));
    fooEvent.setMessage(fooRequestDTO.getMessage());
    streamBridge.send("fooEventSupplier-out-0", MessageBuilder.withPayload(fooEvent)
      .setHeader("version", EventVersion.V1.toJson())
      .build());

  }

  private void sendEventV2(FooRequestDTO fooRequestDTO) {
    FooEventV2 fooEvent = new FooEventV2();
    fooEvent.setId(String.valueOf(UUID.randomUUID()));
    fooEvent.setMessage(fooRequestDTO.getMessage());
    streamBridge.send("fooEventSupplier-out-0", MessageBuilder.withPayload(fooEvent)
      .setHeader("version", EventVersion.V2.toJson())
      .build());
  }
}
