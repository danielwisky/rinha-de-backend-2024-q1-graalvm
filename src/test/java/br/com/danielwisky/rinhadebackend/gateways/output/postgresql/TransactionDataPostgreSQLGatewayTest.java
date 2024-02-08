package br.com.danielwisky.rinhadebackend.gateways.output.postgresql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.UnitTest;
import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.TransactionEntityPostgreSQLRepository;
import br.com.danielwisky.rinhadebackend.templates.domains.TransactionTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("TransactionDataPostgreSQLGateway Test Case")
class TransactionDataPostgreSQLGatewayTest extends UnitTest {

  @InjectMocks
  private TransactionDataPostgreSQLGateway TransactionDataPostgreSQLGateway;

  @Mock
  private TransactionEntityPostgreSQLRepository TransactionEntityPostgreSQLRepository;

  @Test
  @DisplayName("should save")
  void shouldSave() {
    final Transaction Transaction = TransactionTemplate.validCredit();
    when(TransactionEntityPostgreSQLRepository.save(any()))
        .thenReturn(new TransactionEntity(Transaction));

    final Transaction TransactionSaved = TransactionDataPostgreSQLGateway.save(Transaction);

    assertEquals(Transaction, TransactionSaved);
  }
}