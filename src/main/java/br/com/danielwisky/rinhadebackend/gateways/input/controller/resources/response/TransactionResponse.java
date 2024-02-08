package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response;

import br.com.danielwisky.rinhadebackend.domains.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("limite")
  private BigInteger limit;
  @JsonProperty("saldo")
  private BigInteger balance;

  public TransactionResponse(final Client client) {
    this.limit = client.getLimit();
    this.balance = client.getBalance();
  }
}
