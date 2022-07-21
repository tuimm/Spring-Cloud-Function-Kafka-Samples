package com.tuimm.reactivefunction.controller;

import com.tuimm.reactivefunction.controller.model.FooGenerationRequestDto;
import com.tuimm.reactivefunction.controller.exception.ErrorDto;
import com.tuimm.reactivefunction.service.FooEventGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping(path = FooController.ENDPOINT)
@RequiredArgsConstructor
@Tag(
  name = "Foo"
)
public class FooController {

  protected static final String ENDPOINT = "/foo";

  private final FooEventGenerationService fooEventGenerationService;

  @Operation(
    summary = "Generate and send test messages to the service input topic.",
    description = "Generate and send test messages to the service input topic. They will be automatically processed by the consumer defined in the FooEventHandler class.",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FooGenerationRequestDto.class)))
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "No content"),
    @ApiResponse(responseCode = "400", description = "Bad Request",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class))),
    @ApiResponse(responseCode = "500", description = "Internal Server Error",
      content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ErrorDto.class)))
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(
    value = "/generate"
  )
  public Mono<Void> generateFoo(@RequestBody @Valid FooGenerationRequestDto fooGenerationRequestDto) {
    return fooEventGenerationService.generate(fooGenerationRequestDto.getEventVersion(), fooGenerationRequestDto.getQuantity());
  }

}
