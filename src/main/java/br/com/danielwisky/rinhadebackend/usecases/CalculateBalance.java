package br.com.danielwisky.rinhadebackend.usecases;

import static br.com.danielwisky.rinhadebackend.domains.enums.TransactionType.DEBIT;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import java.math.BigInteger;
import org.springframework.stereotype.Component;

@Component
public class CalculateBalance {

  public BigInteger execute(final BigInteger balance, final Transaction transaction) {
    return DEBIT.equals(transaction.getType())
        ? balance.subtract(transaction.getValue())
        : balance.add(transaction.getValue());
  }
}
