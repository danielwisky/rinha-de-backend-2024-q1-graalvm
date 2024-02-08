package br.com.danielwisky.rinhadebackend.gateways.input.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import br.com.danielwisky.rinhadebackend.IntegrationTest;
import br.com.danielwisky.rinhadebackend.gateways.input.controller.resources.request.TransactionRequest;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.entities.ClientEntity;
import br.com.danielwisky.rinhadebackend.templates.entities.ClientEntityTemplate;
import br.com.danielwisky.rinhadebackend.templates.resources.TransactionRequestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@DisplayName("TransactionController Test Case")
class TransactionControllerTest extends IntegrationTest {

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
  @DisplayName("should process credit transaction")
  void shouldProcessCreditTransaction() throws Exception {
    final ClientEntity clientEntity =
        clientEntityPostgreSQLRepository.save(ClientEntityTemplate.valid());
    final TransactionRequest request = TransactionRequestTemplate.validCredit();

    mockMVC.perform(post(String.format("/clientes/%s/transacoes", clientEntity.getId()))
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.limite", is(clientEntity.getLimit().intValue())))
        .andExpect(
            jsonPath("$.saldo", is(clientEntity.getBalance().add(request.getValue()).intValue())));
  }

  @Test
  @DisplayName("should process debit transaction")
  void shouldProcessDebitTransaction() throws Exception {
    final ClientEntity clientEntity =
        clientEntityPostgreSQLRepository.save(ClientEntityTemplate.valid());
    final TransactionRequest request = TransactionRequestTemplate.validDebit();

    mockMVC.perform(post(String.format("/clientes/%s/transacoes", clientEntity.getId()))
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(header().string(CONTENT_TYPE, APPLICATION_JSON_VALUE))
        .andExpect(jsonPath("$.limite", is(clientEntity.getLimit().intValue())))
        .andExpect(jsonPath("$.saldo",
            is(clientEntity.getBalance().subtract(request.getValue()).intValue())));
  }

  @Test
  @DisplayName("should validate insufficient funds")
  void shouldValidateInsufficientFunds() throws Exception {
    final ClientEntity clientEntity =
        clientEntityPostgreSQLRepository.save(ClientEntityTemplate.valid());
    final TransactionRequest request = TransactionRequestTemplate.validDebitWithHighValue();

    mockMVC.perform(post(String.format("/clientes/%s/transacoes", clientEntity.getId()))
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("should validate non-existent client")
  void shouldValidateNonExistentClient() throws Exception {
    final TransactionRequest request = TransactionRequestTemplate.validCredit();
    mockMVC.perform(post("/clientes/10/transacoes")
            .contentType(APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isNotFound());
  }
}