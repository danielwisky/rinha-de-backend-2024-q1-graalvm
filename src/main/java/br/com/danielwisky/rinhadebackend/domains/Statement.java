package br.com.danielwisky.rinhadebackend.domains;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statement {

  private Client client;
  private List<Transaction> recentTransactions;
}
