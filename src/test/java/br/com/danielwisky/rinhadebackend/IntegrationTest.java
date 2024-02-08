package br.com.danielwisky.rinhadebackend;

import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.ClientEntityPostgreSQLRepository;
import br.com.danielwisky.rinhadebackend.gateways.output.postgresql.repositories.TransactionEntityPostgreSQLRepository;
import br.com.danielwisky.rinhadebackend.supports.PostgreSQLContainerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = RinhaDeBackendApplication.class)
public abstract class IntegrationTest {

  protected static final PostgreSQLContainerConfiguration postgreSQLContainer;

  static {
    postgreSQLContainer = PostgreSQLContainerConfiguration.getInstance();
    postgreSQLContainer.start();
  }

  @Autowired
  protected ClientEntityPostgreSQLRepository clientEntityPostgreSQLRepository;

  @Autowired
  protected TransactionEntityPostgreSQLRepository transactionEntityPostgreSQLRepository;
}
