package com.tiurinvalery.agile.engine.repo;

import com.tiurinvalery.agile.engine.dto.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountRepository {

    @Value("${account.id}")
    private String testUserAccountId;

    private final Account testAccount = Account.builder()
            .id(testUserAccountId)
            .sum(0.0)
            .build();

    /**
     * Mock integration with real DB.
     * @return test user if correct id provided.
     */
    public Account findById(String id) {
        if (id.equals(testUserAccountId)) {
            return testAccount;
        } else {
            return null;
        }
    }
}
