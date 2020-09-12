package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;
import com.tiurinvalery.agile.engine.dto.Transaction;

public interface AccountService {

    Account getAccountById(String accountId);

    void doTransaction(Transaction transaction);
}
