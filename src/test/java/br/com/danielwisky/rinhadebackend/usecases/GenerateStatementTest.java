package br.com.danielwisky.rinhadebackend.usecases;

import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.CLIENT_NOT_FOUND;
import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.UnitTest;
import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.domains.Statement;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.rinhadebackend.gateways.output.ClientDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.TransactionDataGateway;
import br.com.danielwisky.rinhadebackend.templates.domains.ClientTemplate;
import br.com.danielwisky.rinhadebackend.templates.domains.TransactionTemplate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("GenerateStatement Test Case")
class GenerateStatementTest extends UnitTest {

  @InjectMocks
  private GenerateStatement generateStatement;

  @Mock
  private ClientDataGateway clientDataGateway;

  @Mock
  private TransactionDataGateway transactionDataGateway;

  @Test
  @DisplayName("should generate statement")
  void shouldGenerateStatement() {
    final Client client = ClientTemplate.valid();
    final Transaction transaction = TransactionTemplate.validCredit();

    when(clientDataGateway.findById(client.getId()))
        .thenReturn(java.util.Optional.of(client));
    when(transactionDataGateway.findTop10ByClientIdOrderByIdDesc(client.getId()))
        .thenReturn(List.of(transaction));

    final Statement statement = generateStatement.execute(client.getId());
    assertNotNull(statement);
  }

  @Test
  @DisplayName("should validate non-existent client")
  void shouldValidateNonExistentClient() {
    final Client client = ClientTemplate.valid();
    when(clientDataGateway.findById(client.getId())).thenReturn(empty());

    final ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class,
            () -> generateStatement.execute(client.getId()));

    assertEquals(CLIENT_NOT_FOUND, exception.getKey());
  }
}