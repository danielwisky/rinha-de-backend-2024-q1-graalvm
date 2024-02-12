package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response;

import static java.time.LocalDateTime.now;

import br.com.danielwisky.rinhadebackend.domains.Client;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementBalanceResponse {

  @JsonProperty("limite")
  private BigInteger limit;
  @JsonProperty("total")
  private BigInteger balance;
  @JsonProperty("data_extrato")
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime createdAt;

  public StatementBalanceResponse(final Client client) {
    this.limit = client.getLimit();
    this.balance = client.getBalance();
    this.createdAt = now();
  }
}
