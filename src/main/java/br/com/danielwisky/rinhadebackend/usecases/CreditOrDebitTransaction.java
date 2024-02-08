package br.com.danielwisky.rinhadebackend.usecases;

import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.CLIENT_NOT_FOUND;
import static java.math.BigInteger.ZERO;

import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.exceptions.InsufficientFundsException;
import br.com.danielwisky.rinhadebackend.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.rinhadebackend.gateways.output.ClientDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.TransactionDataGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditOrDebitTransaction {

  private final CalculateBalance calculateBalance;
  private final ClientDataGateway clientDataGateway;
  private final TransactionDataGateway transactionDataGateway;

  @Transactional
  public Client execute(final Long clientId, final Transaction transaction) {
    final Client client = clientDataGateway.findById(clientId)
        .orElseThrow(() -> new ResourceNotFoundException(CLIENT_NOT_FOUND));
    transaction.setClient(client);
    client.setBalance(calculateBalance.execute(client.getBalance(), transaction));

    validateSufficientFunds(client);

    transactionDataGateway.save(transaction);
    return clientDataGateway.save(client);
  }

  private static void validateSufficientFunds(final Client client) {
    if (client.getLimit().add(client.getBalance()).compareTo(ZERO) < 0) {
      throw new InsufficientFundsException();
    }
  }
}
