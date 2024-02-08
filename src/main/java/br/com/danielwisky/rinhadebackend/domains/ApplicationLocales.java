package br.com.danielwisky.rinhadebackend.domains;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

import java.util.Locale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationLocales {

  public static final Locale EN_US = new Locale("en", "US");
  public static final Locale PT_BR = new Locale("pt", "BR");

  public static Locale getDefault() {
    return getLocale();
  }
}