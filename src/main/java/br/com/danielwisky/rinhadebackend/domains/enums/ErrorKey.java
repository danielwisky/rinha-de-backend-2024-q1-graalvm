package br.com.danielwisky.rinhadebackend.domains.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorKey {
  PARAMETER_TYPE_MISMATCH_MESSAGE_KEY("msg.parameter.invalid.or.malformed"),
  INSUFFICIENT_FUNDS("msg.insufficient.funds"),
  CLIENT_NOT_FOUND("msg.client.not.found");

  @Getter
  private final String key;
}