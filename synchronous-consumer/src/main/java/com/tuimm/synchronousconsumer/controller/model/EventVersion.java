package com.tuimm.synchronousconsumer.controller.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventVersion {
  V1("v1"), V2("v2");

  private final String value;

  @JsonValue
  public String toJson() {
    return value;
  }

}
