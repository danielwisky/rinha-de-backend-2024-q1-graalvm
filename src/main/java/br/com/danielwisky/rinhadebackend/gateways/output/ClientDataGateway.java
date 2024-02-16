package br.com.danielwisky.rinhadebackend.gateways.output;

import br.com.danielwisky.rinhadebackend.domains.Client;
import java.util.Optional;

public interface ClientDataGateway {

  Client save(Client client);

  Optional<Client> findById(Long id);

  Optional<Client> findByIdWithPessimisticWriteLock(Long id);
}
