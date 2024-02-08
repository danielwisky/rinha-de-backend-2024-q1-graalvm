package br.com.danielwisky.rinhadebackend.templates.domains;

import static br.com.danielwisky.rinhadebackend.domains.enums.TransactionType.CREDIT;
import static java.math.BigInteger.ONE;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import java.math.BigInteger;

public class TransactionTemplate {

  public static Transaction validCredit() {
    return Transaction.builder()
        .id(1L)
        .type(CREDIT)
        .value(BigInteger.valueOf(100))
        .client(ClientTemplate.valid())
        .build();
  }
}
