package revolut.database;

import com.jullierme.revolut.config.commonsConfigurations.CommonsConfigurationsService;
import com.jullierme.revolut.database.DatabaseConnectionService;
import com.jullierme.revolut.database.DatabaseConnectionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionServiceInegrationTest {

    private DatabaseConnectionService service;
    private Connection connection;

    @BeforeAll
    static void beforeAll() {
        CommonsConfigurationsService.load();
    }

    @BeforeEach
    void beforeEach() throws SQLException {
        instantiateServices();
        closeConnection();
    }

    private void instantiateServices() {
        service = new DatabaseConnectionServiceImpl();
    }

    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    void shouldReturnAValidConnection() throws SQLException {
        connection = service.getConnection();

        Assertions.assertNotNull(connection);
        Assertions.assertFalse(connection.isClosed());
    }
}