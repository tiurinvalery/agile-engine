package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Transaction;

import java.util.List;

public interface TransactionHistoryService {

    void push(Transaction transaction);

    Transaction getTransactionById(String accountId, String transactionId);

    List<Transaction> getAllTransactions();
}
