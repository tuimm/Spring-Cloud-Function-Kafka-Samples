package com.tuimm.synchronousconsumer.controller;

import com.tuimm.synchronousconsumer.controller.model.FooRequestDTO;
import com.tuimm.synchronousconsumer.service.FooService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "foo")
@Tag(
  name = "foo"
)
@RequiredArgsConstructor
public class FooController {
  private final FooService fooService;


  @Operation(
    tags = "foo"
  )
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "202", description = "Accepted")
    }
  )
  @PostMapping(
    path = "",
    consumes = MediaType.APPLICATION_JSON_VALUE
  )
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void startStream(@Valid @RequestBody
  FooRequestDTO fooRequestDTO) {

    fooService.sendEvent(fooRequestDTO);

  }
}

