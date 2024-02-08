package br.com.danielwisky.rinhadebackend.usecases;


import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.CLIENT_NOT_FOUND;

import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.domains.Statement;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.rinhadebackend.gateways.output.ClientDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.TransactionDataGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateStatement {

  private final ClientDataGateway clientDataGateway;
  private final TransactionDataGateway transactionDataGateway;

  public Statement execute(final Long clientId) {
    final Client client = clientDataGateway.findById(clientId)
        .orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND));
    final List<Transaction> recentTransactions =
        transactionDataGateway.findTop10ByClientIdOrderByIdDesc(clientId);
    return Statement.builder()
        .client(client)
        .recentTransactions(recentTransactions)
        .build();
  }
}
