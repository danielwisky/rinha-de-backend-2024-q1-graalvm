package br.com.danielwisky.rinhadebackend.templates.resources;

import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.request.TransactionRequest;
import java.math.BigDecimal;

public class TransactionRequestTemplate {

  public static TransactionRequest validCredit() {
    final TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setType('c');
    transactionRequest.setValue(BigDecimal.valueOf(100));
    transactionRequest.setDescription("credit");
    return transactionRequest;
  }

  public static TransactionRequest validDebit() {
    final TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setType('d');
    transactionRequest.setValue(BigDecimal.valueOf(10));
    transactionRequest.setDescription("debit");
    return transactionRequest;
  }

  public static TransactionRequest validDebitWithHighValue() {
    final TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setType('d');
    transactionRequest.setValue(BigDecimal.valueOf(1001));
    transactionRequest.setDescription("debit");
    return transactionRequest;
  }
}