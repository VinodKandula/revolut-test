package com.jullierme.revolut.business.account.find;

import com.jullierme.revolut.config.integration.extension.database.DatabaseIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DatabaseIntegrationTest
@DisplayName("Test suite of the class: AccountFindServiceFactory")
class AccountFindServiceFactoryITest {
    @Test
    @DisplayName("Should return a new AccountFindByAccountService instance")
    void givenAccountFindServiceFactory_whenGetAccountFindByIdService_thenShouldReturnNewInstance() {
        //given

        //when
        AccountFindByIdService service = AccountFindServiceFactory.getInstance();

        //then
        assertNotNull(service);
    }
}
