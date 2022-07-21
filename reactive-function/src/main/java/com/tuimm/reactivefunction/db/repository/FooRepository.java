package com.tuimm.reactivefunction.db.repository;

import com.tuimm.reactivefunction.domain.model.Foo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface FooRepository extends ReactiveMongoRepository<Foo, UUID> {
}
