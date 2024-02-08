package br.com.danielwisky.rinhadebackend.gateways.input.controller;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.request.TransactionRequest;
import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response.TransactionResponse;
import br.com.danielwisky.rinhadebackend.usecases.CreditOrDebitTransaction;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clientes/{clientId}/transacoes")
@RequiredArgsConstructor
public class TransactionController {

  private final CreditOrDebitTransaction creditOrDebitTransaction;

  @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public TransactionResponse processTransaction(
      @PathVariable final Long clientId,
      @RequestBody @Valid final TransactionRequest transactionRequest) {
    log.debug("process transaction request: {} with client-id: {}", transactionRequest, clientId);
    return new TransactionResponse(
        creditOrDebitTransaction.execute(clientId, transactionRequest.toDomain()));
  }
}
