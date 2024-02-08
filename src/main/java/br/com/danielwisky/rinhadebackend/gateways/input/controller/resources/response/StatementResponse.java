package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response;

import static java.util.Optional.ofNullable;

import br.com.danielwisky.rinhadebackend.domains.Statement;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("saldo")
  private StatementBalanceResponse balance;
  @JsonProperty("ultimas_transacoes")
  private List<StatementTransactionResponse> recentTransactions;

  public StatementResponse(final Statement statement) {
    this.balance = ofNullable(statement.getClient())
        .map(StatementBalanceResponse::new)
        .orElse(null);
    this.recentTransactions = statement.getRecentTransactions()
        .stream()
        .map(StatementTransactionResponse::new)
        .toList();
  }
}
