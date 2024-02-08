package br.com.danielwisky.rinhadebackend.gateways.output.postgresql;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.gateways.output.TransactionDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.TransactionEntityPostgreSQLRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionDataPostgreSQLGateway implements TransactionDataGateway {

  private final TransactionEntityPostgreSQLRepository repository;

  @Override
  public Transaction save(final Transaction transaction) {
    return repository.save(new TransactionEntity(transaction)).toDomain();
  }
}
