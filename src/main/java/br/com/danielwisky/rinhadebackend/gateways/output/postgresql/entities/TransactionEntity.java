package br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.util.Optional.of;

import br.com.danielwisky.rinhadebackend.domains.Transaction;
import br.com.danielwisky.rinhadebackend.domains.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity(name = "transactions")
public class TransactionEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  @NotNull
  @Column(name = "transaction_type")
  private Character type;
  @NotNull
  private BigInteger value;
  @NotBlank
  @Size(max = 10)
  private String description;
  @ManyToOne
  @JoinColumn(name = "client_id", nullable = false, foreignKey = @ForeignKey(name = "fk_transactions_clients"))
  private ClientEntity client;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  public TransactionEntity(final Transaction transaction) {
    this.id = transaction.getId();
    this.type = of(transaction.getType())
        .map(TransactionType::getCode)
        .orElse(null);
    this.value = transaction.getValue();
    this.description = transaction.getDescription();
    this.client = of(transaction.getClient())
        .map(ClientEntity::new)
        .orElse(null);
    this.createdAt = transaction.getCreatedAt();
  }

  public Transaction toDomain() {
    return Transaction.builder()
        .id(id)
        .type(of(type)
            .map(TransactionType::fromCode)
            .orElse(null))
        .value(value)
        .description(description)
        .client(of(client)
            .map(ClientEntity::toDomain)
            .orElse(null))
        .createdAt(createdAt)
        .build();
  }
}
