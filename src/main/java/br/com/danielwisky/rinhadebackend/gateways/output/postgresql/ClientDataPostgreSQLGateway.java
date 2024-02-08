package br.com.danielwisky.rinhadebackend.gateways.output.postgresql;

import br.com.danielwisky.rinhadebackend.domains.Client;
import br.com.danielwisky.rinhadebackend.gateways.output.ClientDataGateway;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.ClientEntityPostgreSQLRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientDataPostgreSQLGateway implements ClientDataGateway {

  private final ClientEntityPostgreSQLRepository repository;

  @Override
  public Client save(final Client client) {
    return repository.save(new ClientEntity(client)).toDomain();
  }

  @Override
  public Optional<Client> findById(final Long id) {
    return repository.findById(id).map(ClientEntity::toDomain);
  }
}
