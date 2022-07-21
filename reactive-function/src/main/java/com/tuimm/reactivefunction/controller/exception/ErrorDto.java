package com.tuimm.reactivefunction.controller.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "error")
public class ErrorDto {

  @Schema(
    description = "Error type.",
    required = true,
    example = "ErrorType"
  )
  private String type;

  @Schema(
    description = "Error message with more information about the problem.",
    required = true,
    example = "Error message."
  )
  private String message;

}
