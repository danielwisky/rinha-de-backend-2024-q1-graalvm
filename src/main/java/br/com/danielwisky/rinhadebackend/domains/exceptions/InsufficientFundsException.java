package br.com.danielwisky.rinhadebackend.domains.exceptions;

import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.INSUFFICIENT_FUNDS;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import java.io.Serial;
import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends I18NRuntimeException implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  public InsufficientFundsException() {
    super(INSUFFICIENT_FUNDS);
  }

  @Override
  public HttpStatus getHttpStatus() {
    return UNPROCESSABLE_ENTITY;
  }
}