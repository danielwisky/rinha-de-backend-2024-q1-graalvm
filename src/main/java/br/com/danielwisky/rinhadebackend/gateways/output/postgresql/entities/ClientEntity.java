package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities;

import static jakarta.persistence.GenerationType.IDENTITY;

import br.com.danielwisky.rinhadebackend.domains.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "clients")
public class ClientEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "account_limit")
  private BigInteger limit;
  @NotNull
  private BigInteger balance;
  @Version
  private LocalDateTime lastModifiedDate;

  public ClientEntity(final Client client) {
    this.id = client.getId();
    this.limit = client.getLimit();
    this.balance = client.getBalance();
    this.lastModifiedDate = client.getLastModifiedDate();
  }

  public Client toDomain() {
    return Client.builder()
        .id(id)
        .limit(limit)
        .balance(balance)
        .lastModifiedDate(lastModifiedDate)
        .build();
  }
}
