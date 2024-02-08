package br.com.danielwisky.rinhadebackend.templates.domains;

import static java.math.BigInteger.ZERO;

import br.com.danielwisky.rinhadebackend.domains.Client;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class ClientTemplate {

  public static Client valid() {
    return Client.builder()
        .id(1L)
        .limit(BigInteger.valueOf(1000))
        .balance(ZERO)
        .lastModifiedDate(LocalDateTime.now())
        .build();
  }
}
