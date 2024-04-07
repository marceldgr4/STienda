package com.Unimagda.STienda;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostgresContainerBase {
    @Container
    public static PostgreSQLContainer postgresContainer = new PostgreSQLContainer()
            .withDatabaseName("tiendamicro")
            .withUsername("postgres")
            .withPassword("1234");
    @BeforeAll
    public void setUp() {
        postgresContainer.start();
    }


}
