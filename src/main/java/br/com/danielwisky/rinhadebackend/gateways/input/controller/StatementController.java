package br.com.danielwisky.rinhadebackend.gateways.input.controller;

import static org.springframework.http.HttpStatus.OK;

import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response.StatementResponse;
import br.com.danielwisky.rinhadebackend.usecases.GenerateStatement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes/{clientId}/extrato")
@RequiredArgsConstructor
public class StatementController {

  private final GenerateStatement generateStatement;

  @GetMapping
  @ResponseStatus(OK)
  public StatementResponse getStatement(@PathVariable final Long clientId) {
    return new StatementResponse(generateStatement.execute(clientId));
  }
}
