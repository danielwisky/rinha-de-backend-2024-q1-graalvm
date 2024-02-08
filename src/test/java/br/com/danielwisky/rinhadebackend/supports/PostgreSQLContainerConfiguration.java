package br.com.danielwisky.rinhadebackend.supports;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgreSQLContainerConfiguration extends PostgreSQLContainer {

  private static PostgreSQLContainerConfiguration container;

  public static PostgreSQLContainerConfiguration getInstance() {
    if (container == null) {
      container = new PostgreSQLContainerConfiguration();
    }

    return container;
  }

  @Override
  public void start() {
    super.start();
    System.setProperty("POSTGRES_URI", container.getJdbcUrl());
    System.setProperty("POSTGRES_USER", container.getUsername());
    System.setProperty("POSTGRES_PASSWORD", container.getPassword());
  }
}
