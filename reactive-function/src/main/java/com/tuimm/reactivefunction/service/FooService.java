package com.tuimm.reactivefunction.service;

import com.tuimm.reactivefunction.db.repository.FooRepository;
import com.tuimm.reactivefunction.domain.model.Foo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FooService {

  private final FooRepository repository;

  public Mono<Foo> save(Foo foo) {
    return repository.save(foo);
  }

}
