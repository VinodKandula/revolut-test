package com.jullierme.revolut.business.account.create;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import com.jullierme.revolut.model.Account;
import com.jullierme.revolut.model.AccountBuilder;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DatabaseIntegrationTest
public class AccountCreateServiceIntegrationTest {

    private AccountCreateServiceFactory accountCreateServiceFactory;
    private AccountCreateService accountCreateService;

    @BeforeEach
    void beforeEach() {
        init();
    }

    void init() {
        accountCreateServiceFactory = new AccountCreateServiceFactory();
        accountCreateService = accountCreateServiceFactory.getInstance();
    }

    Account getDefaultAccout(String accountNumber) {
        return AccountBuilder
                .builder()
                .accountNumber(accountNumber)
                .sortCode("987654")
                .name("Jullierme Barros")
                .balance(new BigDecimal(1000))
                .build();
    }

    @Test
    void givenANewAccount_thenCreate_shouldSucceed() throws SQLException {
        Account account = getDefaultAccout("12344569");

        account = accountCreateService.create(account);

        assertNotNull(account);
        assertNotNull(account.getId());
    }

    @Test
    void givenADuplicateAccount_thenCreate_shouldError() {
        assertThrows(JdbcSQLIntegrityConstraintViolationException.class, () -> {
            Account account = getDefaultAccout("12312553");

            account = accountCreateService.create(account);

            assertNotNull(account);
            assertNotNull(account.getId());


            account.setId(null);

            accountCreateService.create(account);
        });
    }
}