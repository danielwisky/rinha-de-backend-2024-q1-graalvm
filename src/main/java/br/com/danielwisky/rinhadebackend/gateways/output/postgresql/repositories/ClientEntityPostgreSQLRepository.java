package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientEntityPostgreSQLRepository
    extends CrudRepository<ClientEntity, Long> {

}