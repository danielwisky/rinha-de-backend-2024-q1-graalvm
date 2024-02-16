package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import static jakarta.persistence.LockModeType.PESSIMISTIC_WRITE;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import jakarta.persistence.QueryHint;
import java.util.Optional;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

public interface ClientEntityPostgreSQLRepository extends CrudRepository<ClientEntity, Long> {

  @Lock(PESSIMISTIC_WRITE)
  @QueryHints({@QueryHint(name = "jakarta.persistence.lock.timeout", value = "3000")})
  Optional<ClientEntity> findWithPessimisticWriteLockById(Long id);
}