package br.com.danielwisky.rinhadebackend.domains.exceptions;

import br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class I18NRuntimeException extends RuntimeException implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  @Getter
  private final ErrorKey key;

  @Getter
  private final transient Object[] params;

  protected I18NRuntimeException(final ErrorKey errorKey) {
    this.key = errorKey;
    this.params = new Object[0];
  }

  protected I18NRuntimeException(final ErrorKey errorKey, final Object... params) {
    this.key = errorKey;
    this.params = params;
  }

  public abstract HttpStatus getHttpStatus();
}