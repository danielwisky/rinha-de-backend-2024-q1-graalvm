package br.com.danielwisky.rinhadebackend.templates.entities;

import static java.time.LocalDateTime.now;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;

public class TransactionEntityTemplate {

  public static TransactionEntity valid(final ClientEntity clientEntity) {
    final TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setType('c');
    transactionEntity.setValue(BigInteger.valueOf(100));
    transactionEntity.setDescription("credit");
    transactionEntity.setClient(clientEntity);
    transactionEntity.setCreatedAt(now());
    return transactionEntity;
  }
}