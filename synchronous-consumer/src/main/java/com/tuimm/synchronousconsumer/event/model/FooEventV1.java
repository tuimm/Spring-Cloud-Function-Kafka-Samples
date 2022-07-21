package com.tuimm.synchronousconsumer.event.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class FooEventV1 {
  @JsonProperty
  private String id;
  @JsonProperty
  private String message;
}
