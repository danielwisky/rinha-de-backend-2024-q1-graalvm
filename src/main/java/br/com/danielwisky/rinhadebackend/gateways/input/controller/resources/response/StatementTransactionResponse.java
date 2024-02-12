package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementTransactionResponse {

  @JsonProperty("valor")
  private BigInteger value;
  @JsonProperty("tipo")
  private Character type;
  @JsonProperty("descricao")
  private String description;
  @JsonProperty("realizada_em")
  private LocalDateTime createdAt;

  public StatementTransactionResponse(final Transaction transaction) {
    this.value = transaction.getValue();
    this.type = transaction.getType().getCode();
    this.description = transaction.getDescription();
    this.createdAt = transaction.getCreatedAt();
  }
}
