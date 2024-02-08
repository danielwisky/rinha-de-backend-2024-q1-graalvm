package br.com.danielwisky.rinhadebackend.templates.entities;

import static java.math.BigInteger.ZERO;
import static java.time.LocalDateTime.now;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import java.math.BigInteger;

public class ClientEntityTemplate {

  public static ClientEntity valid() {
    final ClientEntity clientEntity = new ClientEntity();
    clientEntity.setLimit(BigInteger.valueOf(1000));
    clientEntity.setBalance(ZERO);
    clientEntity.setLastModifiedDate(now());
    return clientEntity;
  }
}
