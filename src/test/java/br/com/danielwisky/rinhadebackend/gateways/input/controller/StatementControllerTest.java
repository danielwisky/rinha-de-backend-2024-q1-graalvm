package br.com.danielwisky.rinhadebackend.gateways.input.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import br.com.danielwisky.rinhadebackend.IntegrationTest;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.TransactionEntity;
import br.com.danielwisky.rinhadebackend.templates.entities.ClientEntityTemplate;
import br.com.danielwisky.rinhadebackend.templates.entities.TransactionEntityTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("StatementControllerTest Test Case")
class StatementControllerTest extends IntegrationTest {

  @Autowired
  private WebApplicationContext webAppContext;

  @Autowired
  private ObjectMapper objectMapper;

  private MockMvc mockMVC;

  @BeforeEach
  void setUp() {
    mockMVC = webAppContextSetup(webAppContext).build();
    transactionEntityPostgreSQLRepository.deleteAll();
    clientEntityPostgreSQLRepository.deleteAll();
  }

  @Test
  @DisplayName("should generate statement")
  void shouldGenerateStatement() throws Exception {
    final ClientEntity clientEntity =
        clientEntityPostgreSQLRepository.save(ClientEntityTemplate.valid());
    final TransactionEntity transactionEntity =
        transactionEntityPostgreSQLRepository.save(TransactionEntityTemplate.valid(clientEntity));

    mockMVC.perform(get(String.format("/clientes/%s/extrato", clientEntity.getId())))
        .andExpect(status().isOk())
        .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.saldo.limite", is(clientEntity.getLimit().intValue())))
        .andExpect(jsonPath("$.saldo.total", is(clientEntity.getBalance().intValue())))
        .andExpect(
            jsonPath("$.ultimas_transacoes[0].tipo", is(transactionEntity.getType().toString())))
        .andExpect(
            jsonPath("$.ultimas_transacoes[0].valor", is(transactionEntity.getValue().intValue())))
        .andExpect(
            jsonPath("$.ultimas_transacoes[0].descricao", is(transactionEntity.getDescription())))
        .andExpect(jsonPath("$.ultimas_transacoes[0].realizada_em",
            is(transactionEntity.getCreatedAt().toString())));
  }

  @Test
  @DisplayName("should validate non-existent client")
  void shouldValidateNonExistentClient() throws Exception {
    mockMVC.perform(get("/clientes/10/extrato")).andExpect(status().isNotFound());
  }
}