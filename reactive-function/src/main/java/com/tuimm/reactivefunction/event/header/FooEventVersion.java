package com.tuimm.reactivefunction.event.header;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum FooEventVersion {
  PAYLOAD_TYPE_V1("vnd.foo.v1"),
  PAYLOAD_TYPE_V2("vnd.foo.v2");

  @Getter(onMethod_ = @JsonValue)
  private final String value;

  @JsonCreator
  public static FooEventVersion parse(String value) {
    return Arrays.stream(FooEventVersion.values()).filter(e -> e.value.equals(value)).findFirst().orElse(null);
  }
}
