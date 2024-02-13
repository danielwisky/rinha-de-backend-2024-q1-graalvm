package br.com.danielwisky.rinhadebackend.gateways.input.controller;

import static br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey.PARAMETER_TYPE_MISMATCH_MESSAGE_KEY;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.danielwisky.rinhadebackend.domains.exceptions.I18NRuntimeException;
import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response.ErrorResponse;
import br.com.danielwisky.rinhadebackend.utils.MessageUtils;
import jakarta.validation.ConstraintViolationException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler {

  private final MessageUtils messageUtils;

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public HttpEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      final MethodArgumentTypeMismatchException ex) {
    log.debug(ex.getMessage(), ex);
    final var errorMessage = messageUtils.getMessage(
        PARAMETER_TYPE_MISMATCH_MESSAGE_KEY,
        ex.getName(),
        ofNullable(ex.getRequiredType()).map(Class::getName).orElse(null));
    return new ResponseEntity<>(createResponse(errorMessage), buildHttpHeader(), BAD_REQUEST);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public HttpEntity<ErrorResponse> handlerMethodArgumentNotValidException(
      final MethodArgumentNotValidException ex) {
    log.debug(ex.getMessage(), ex);
    final var bindingResult = ex.getBindingResult();
    final var fieldErrors = bindingResult.getFieldErrors();
    final var message = this.processFieldErrors(fieldErrors);
    final var responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    return new ResponseEntity<>(message, responseHeaders, BAD_REQUEST);
  }

  @ExceptionHandler(I18NRuntimeException.class)
  public HttpEntity<ErrorResponse> handlerI18NRuntimeException(final I18NRuntimeException ex) {
    log.debug(ex.getMessage(), ex);
    final var message = messageUtils.getMessage(ex.getKey(), ex.getParams());
    return new ResponseEntity<>(createResponse(message), buildHttpHeader(), ex.getHttpStatus());
  }

  @ExceptionHandler(PessimisticLockingFailureException.class)
  public HttpEntity<ErrorResponse> handlerPessimisticLockingFailureException(
      final PessimisticLockingFailureException e) {
    log.debug(e.getMessage(), e);
    return new ResponseEntity<>(createResponse(e), buildHttpHeader(), UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler({IllegalArgumentException.class, ConstraintViolationException.class})
  public HttpEntity<ErrorResponse> handle400Exceptions(final RuntimeException ex) {
    log.debug(ex.getMessage(), ex);
    return new ResponseEntity<>(createResponse(ex), buildHttpHeader(), BAD_REQUEST);
  }

  @ExceptionHandler(Throwable.class)
  public HttpEntity<ErrorResponse> handleThrowable(final Throwable ex) {
    log.error(ex.getMessage(), ex);
    return new ResponseEntity<>(createResponse(ex), buildHttpHeader(), INTERNAL_SERVER_ERROR);
  }

  private HttpHeaders buildHttpHeader() {
    final var responseHeaders = new HttpHeaders();
    responseHeaders.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);
    return responseHeaders;
  }

  private ErrorResponse createResponse(final Throwable ex) {
    return createResponse(ex.getMessage());
  }

  private ErrorResponse createResponse(final String message) {
    return isNotBlank(message) ? new ErrorResponse(List.of(message)) : null;
  }

  private ErrorResponse processFieldErrors(final List<FieldError> fieldErrors) {
    final var errors = fieldErrors
        .stream()
        .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
        .toList();
    return new ErrorResponse(errors);
  }
}