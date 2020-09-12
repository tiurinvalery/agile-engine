package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;

public interface DebitOperationService {
    Double applyDebitOnAccount(Account account, Double sum);
}
