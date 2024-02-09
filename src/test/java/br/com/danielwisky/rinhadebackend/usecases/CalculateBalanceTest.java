package br.com.danielwisky.rinhadebackend.usecases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.danielwisky.rinhadebackend.UnitTest;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.templates.domains.TransactionTemplate;
import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

@DisplayName("CalculateBalance Test Case")
class CalculateBalanceTest extends UnitTest {

  @InjectMocks
  private CalculateBalance calculateBalance;

  @Test
  @DisplayName("should calculate credit balance")
  void shouldCalculateCreditBalance() {
    final Transaction transaction = TransactionTemplate.validCredit();
    final BigInteger calculatedBalance = calculateBalance.execute(BigInteger.TEN, transaction);
    final BigInteger expectedBalance = BigInteger.valueOf(110);
    assertTrue(calculatedBalance.equals(expectedBalance));
  }

  @Test
  @DisplayName("should calculate debit balance")
  void shouldCalculateDebitBalance() {
    final Transaction transaction = TransactionTemplate.validDebit();
    final BigInteger calculatedBalance = calculateBalance.execute(BigInteger.TEN, transaction);
    final BigInteger expectedBalance = BigInteger.valueOf(5);
    assertTrue(calculatedBalance.equals(expectedBalance));
  }
}