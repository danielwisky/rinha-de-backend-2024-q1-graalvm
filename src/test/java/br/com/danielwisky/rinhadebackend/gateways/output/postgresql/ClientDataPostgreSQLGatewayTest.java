package br.com.danielwisky.rinhadebackend.gateways.output.postgresql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.danielwisky.rinhadebackend.UnitTest;
import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.ClientEntityPostgreSQLRepository;
import br.com.danielwisky.rinhadebackend.templates.domains.ClientTemplate;
import br.com.danielwisky.rinhadebackend.templates.entities.ClientEntityTemplate;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@DisplayName("ClientDataPostgreSQLGateway Test Case")
class ClientDataPostgreSQLGatewayTest extends UnitTest {

  @InjectMocks
  private ClientDataPostgreSQLGateway clientDataPostgreSQLGateway;

  @Mock
  private ClientEntityPostgreSQLRepository clientEntityPostgreSQLRepository;

  @Test
  @DisplayName("should save")
  void shouldSave() {
    final Client client = ClientTemplate.valid();
    when(clientEntityPostgreSQLRepository.save(any())).thenReturn(new ClientEntity(client));

    final Client clientSaved = clientDataPostgreSQLGateway.save(client);

    assertEquals(client, clientSaved);
  }

  @Test
  @DisplayName("should find by id")
  void shouldFindById() {
    final ClientEntity client = ClientEntityTemplate.valid();
    when(clientEntityPostgreSQLRepository.findById(client.getId()))
        .thenReturn(Optional.of(client));

    final Optional<Client> clientReturned = clientDataPostgreSQLGateway.findById(client.getId());

    assertTrue(clientReturned.isPresent());
  }
}