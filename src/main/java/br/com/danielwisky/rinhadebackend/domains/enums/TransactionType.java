package br.com.danielwisky.rinhadebackend.domains.enums;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
  DEBIT('d'), CREDIT('c');

  private final Character code;

  public static TransactionType fromCode(final Character code) {
    return Arrays.stream(TransactionType.values())
        .filter(transactionType -> transactionType.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
