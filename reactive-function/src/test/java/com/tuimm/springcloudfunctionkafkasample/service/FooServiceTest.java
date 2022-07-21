package com.tuimm.reactivefunction.service;

import com.tuimm.reactivefunction.db.repository.FooRepository;
import com.tuimm.reactivefunction.domain.model.Foo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FooServiceTest {

  @InjectMocks
  private FooService service;

  @Mock
  private FooRepository repository;

  @Test
  void save() {
    // Given
    var foo = Foo.builder()
      .id(UUID.randomUUID())
      .name("name")
      .description("description")
      .build();

    when(repository.save(foo)).thenReturn(Mono.just(foo));

    // When
    var publisher = service.save(foo);

    // Then
    StepVerifier.create(publisher).expectNext(foo).verifyComplete();
  }

}
