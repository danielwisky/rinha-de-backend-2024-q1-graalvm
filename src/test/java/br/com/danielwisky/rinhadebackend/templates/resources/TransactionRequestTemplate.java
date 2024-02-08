package br.com.danielwisky.rinhadebackend.templates.resources;

import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.request.TransactionRequest;
import java.math.BigInteger;

public class TransactionRequestTemplate {

  public static TransactionRequest validCredit() {
    final TransactionRequest transactionRequest = new TransactionRequest();
    transactionRequest.setType('c');
    transactionRequest.setValue(BigInteger.valueOf(100));
    transactionRequest.setDescription("description");
    return transactionRequest;
  }
}