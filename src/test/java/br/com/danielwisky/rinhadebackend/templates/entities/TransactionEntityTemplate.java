package br.com.danielwisky.rinhadebackend.templates.entities;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;

public class TransactionEntityTemplate {

  public static TransactionEntity validCredit() {
    final TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setType('c');
    transactionEntity.setValue(BigInteger.valueOf(100));
    transactionEntity.setDescription("description");
    transactionEntity.setClient(ClientEntityTemplate.valid());
    return transactionEntity;
  }
}