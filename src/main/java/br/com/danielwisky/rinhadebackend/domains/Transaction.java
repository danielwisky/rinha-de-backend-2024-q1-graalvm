package br.com.danielwisky.rinhadebackend.domains;

import br.com.danielwisky.rinhadebackend.domains.enums.TransactionType;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private Long id;
  private TransactionType type;
  private BigInteger value;
  private String description;
  private Client client;
}
