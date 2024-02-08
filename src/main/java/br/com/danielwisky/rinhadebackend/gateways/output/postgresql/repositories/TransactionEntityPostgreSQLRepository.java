package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;

public interface TransactionEntityPostgreSQLRepository
    extends CrudRepository<TransactionEntity, BigInteger> {

}