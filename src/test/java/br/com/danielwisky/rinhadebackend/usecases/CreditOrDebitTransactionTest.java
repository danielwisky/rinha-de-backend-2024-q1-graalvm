package br.com.danielwisky.rinhadebackend.usecases;

import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.CLIENT_NOT_FOUND;
import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.INSUFFICIENT_FUNDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.UnitTest;
import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.exceptions.InsufficientFundsException;
import br.com.danielwisky.rinhadebackend.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.rinhadebackend.gateways.output.ClientDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.TransactionDataGateway;
import br.com.danielwisky.rinhadebackend.templates.domains.ClientTemplate;
import br.com.danielwisky.rinhadebackend.templates.domains.TransactionTemplate;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

@DisplayName("CreditOrDebitTransaction Test Case")
class CreditOrDebitTransactionTest extends UnitTest {

  @InjectMocks
  private CreditOrDebitTransaction creditOrDebitTransaction;

  @Spy
  private CalculateBalance calculateBalance;

  @Mock
  private ClientDataGateway clientDataGateway;

  @Mock
  private TransactionDataGateway transactionDataGateway;

  @Captor
  private ArgumentCaptor<Client> clientCaptor;

  @Captor
  private ArgumentCaptor<Transaction> transactionCaptor;

  @Test
  @DisplayName("should process credit transaction")
  void shouldProcessCreditTransaction() {
    final Client client = ClientTemplate.valid();
    final Transaction transaction = TransactionTemplate.validCredit();

    when(clientDataGateway.findById(client.getId())).thenReturn(Optional.of(client));

    creditOrDebitTransaction.execute(client.getId(), transaction);

    verify(transactionDataGateway).save(transactionCaptor.capture());
    verify(clientDataGateway).save(clientCaptor.capture());

    final Client clientCaptured = clientCaptor.getValue();
    assertEquals(BigInteger.valueOf(100), clientCaptured.getBalance());

    final Transaction transactionCaptured = transactionCaptor.getValue();
    assertNotNull(transactionCaptured.getCreatedAt());
  }

  @Test
  @DisplayName("should process debit transaction")
  void shouldProcessDebitTransaction() {
    final Client client = ClientTemplate.valid();
    final Transaction transaction = TransactionTemplate.validDebit();

    when(clientDataGateway.findById(client.getId())).thenReturn(Optional.of(client));

    creditOrDebitTransaction.execute(client.getId(), transaction);

    verify(transactionDataGateway).save(transactionCaptor.capture());
    verify(clientDataGateway).save(clientCaptor.capture());

    final Client clientCaptured = clientCaptor.getValue();
    assertEquals(BigInteger.valueOf(-5), clientCaptured.getBalance());

    final Transaction transactionCaptured = transactionCaptor.getValue();
    assertNotNull(transactionCaptured.getCreatedAt());
  }

  @Test
  @DisplayName("should validate non-existent client")
  void shouldValidateNonExistentClient() {
    final Client client = ClientTemplate.valid();
    final Transaction transaction = TransactionTemplate.validDebit();

    when(clientDataGateway.findById(client.getId())).thenReturn(Optional.empty());

    final ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class,
            () -> creditOrDebitTransaction.execute(client.getId(), transaction));

    assertEquals(CLIENT_NOT_FOUND, exception.getKey());
  }

  @Test
  @DisplayName("should validate insufficient funds")
  void shouldValidateInsufficientFunds() {
    final Client client = ClientTemplate.validWithZeroLimit();
    final Transaction transaction = TransactionTemplate.validDebit();

    when(clientDataGateway.findById(client.getId())).thenReturn(Optional.of(client));

    final InsufficientFundsException exception =
        assertThrows(InsufficientFundsException.class,
            () -> creditOrDebitTransaction.execute(client.getId(), transaction));

    assertEquals(INSUFFICIENT_FUNDS, exception.getKey());
  }
}