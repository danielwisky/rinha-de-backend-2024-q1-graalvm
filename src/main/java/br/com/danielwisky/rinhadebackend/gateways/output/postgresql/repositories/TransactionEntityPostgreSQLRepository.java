package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import static jakarta.persistence.LockModeType.NONE;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityPostgreSQLRepository
    extends CrudRepository<TransactionEntity, BigInteger> {

  @Lock(NONE)
  List<TransactionEntity> findTop10ByClientIdOrderByCreatedAtDesc(Long clientId);
}