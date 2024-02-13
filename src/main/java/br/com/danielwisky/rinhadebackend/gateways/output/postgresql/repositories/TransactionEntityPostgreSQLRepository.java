package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityPostgreSQLRepository extends
    CrudRepository<TransactionEntity, BigInteger> {

  List<TransactionEntity> findTop10ByClientIdOrderByIdDesc(Long clientId);
}