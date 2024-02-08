package br.com.danielwisky.rinhadebackend.domains;

import java.math.BigInteger;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  private Long id;
  private BigInteger limit;
  private BigInteger balance;
  private LocalDateTime lastModifiedDate;

}
