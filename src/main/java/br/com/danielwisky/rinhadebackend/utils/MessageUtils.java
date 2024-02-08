package br.com.danielwisky.rinhadebackend.utils;

import static br.com.danielwisky.rinhadebackend.domains.ApplicationLocales.getDefault;

import br.com.danielwisky.rinhadebackend.domains.enums.ErrorKey;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtils {

  private final MessageSource messageSource;

  public String getMessage(final ErrorKey errorKey, final Locale locale, final Object... params) {
    return getMessage(errorKey.getKey(), locale, params);
  }

  public String getMessage(final ErrorKey errorKey, final Object... params) {
    return getMessage(errorKey.getKey(), params);
  }

  public String getMessage(final String key, final Object... params) {
    return getMessage(key, getDefault(), params);
  }

  public String getMessage(final String key, final Locale locale, final Object... params) {
    return messageSource.getMessage(key, params, locale);
  }
}