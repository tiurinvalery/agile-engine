package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;

public interface CreditOperationService {
    Double applyCreditOnAccount(Account account, Double sum);
}
