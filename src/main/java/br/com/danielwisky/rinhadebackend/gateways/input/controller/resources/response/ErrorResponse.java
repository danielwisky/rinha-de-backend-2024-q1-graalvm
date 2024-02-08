package br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private List<String> errors = new ArrayList<>();

  public void addError(final String error) {
    errors.add(error);
  }
}