package com.tuimm.synchronousconsumer.event;

import com.tuimm.synchronousconsumer.event.model.FooEventV1;
import com.tuimm.synchronousconsumer.event.model.FooEventV2;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Log4j2
@Configuration
public class FooEventHandler {

  @Bean
  public Consumer<Message<FooEventV1>> fooEventConsumerV1() {
    return transferRoute -> log.info("************+message v1 received**************+");
  }

  @Bean
  public Consumer<Message<FooEventV2>> fooEventConsumerV2() {
    return transferRoute -> log.info("**************message v2 received**************");
  }
}
