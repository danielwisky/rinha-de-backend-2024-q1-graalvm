package br.com.danielwisky.rinhadebackend.domains.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey;
import java.io.Serial;
import java.io.Serializable;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends I18NRuntimeException implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(final ErrorKey errorKey) {
    super(errorKey);
  }

  @Override
  public HttpStatus getHttpStatus() {
    return NOT_FOUND;
  }
}