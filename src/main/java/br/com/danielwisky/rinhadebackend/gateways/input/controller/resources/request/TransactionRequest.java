package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.request;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import lombok.Data;

@Data
public class TransactionRequest implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @JsonProperty("valor")
  @NotNull(message = "{field.cant.be.null}")
  @Positive(message = "{field.must.be.positive}")
  private BigInteger value;
  @JsonProperty("tipo")
  @NotNull(message = "{field.cant.be.null}")
  private Character type;
  @JsonProperty("descricao")
  @Size(max = 10, message = "{field.size.exceeded}")
  @NotBlank(message = "{field.cant.be.null}")
  private String description;

  public Transaction toDomain() {
    return Transaction.builder()
        .value(value)
        .type(TransactionType.fromCode(type))
        .description(description)
        .build();
  }
}
