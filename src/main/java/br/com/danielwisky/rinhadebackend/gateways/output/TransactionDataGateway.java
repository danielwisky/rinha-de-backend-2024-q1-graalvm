package br.com.danielwisky.rinhadebackend.gateways.output;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import java.util.List;

public interface TransactionDataGateway {

  Transaction save(Transaction transaction);

  List<Transaction> findTop10ByClientIdOrderByIdDesc(Long clientId);
}
