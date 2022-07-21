package com.tuimm.reactivefunction.controller.model;

import com.tuimm.reactivefunction.event.header.FooEventVersion;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(name = "foo-generation-request")
public class FooGenerationRequestDto {

  @Schema(
    description = "Message event version. Only messages with version 'vnd.foo.v2' will be processed."
      + " Messages will version 'vnd.foo.v1' will be discarded by the consumer.",
    example = "vnd.foo.v2"
  )
  @NotNull
  private FooEventVersion eventVersion;

  @Schema(
    description = "Number of messages that will be generated and sent to the topic.",
    example = "1"
  )
  @NotNull
  @Min(1)
  private Long quantity;

}
