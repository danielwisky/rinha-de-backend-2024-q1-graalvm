package br.com.danielwisky.rinhadebackend.gateways.output;

import br.com.danielwisky.rinhadebackend.domains.Transaction;

public interface TransactionDataGateway {

  Transaction save(Transaction transaction);
}
