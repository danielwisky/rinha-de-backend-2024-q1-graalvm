package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEntityPostgreSQLRepository
    extends JpaRepository<TransactionEntity, BigInteger> {

  List<TransactionEntity> findTop10ByClientIdOrderByCreatedAtDesc(Long clientId);
}