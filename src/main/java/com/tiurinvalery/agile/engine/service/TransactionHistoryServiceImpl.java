package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Transaction;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {
    private ConcurrentHashMap<String, LinkedList<Transaction>> transactionHistory = new ConcurrentHashMap<>();

    @Override
    public void push(Transaction transaction) {
        @NonNull String accountId = transaction.getAccountId();
        LinkedList<Transaction> transactions = transactionHistory.get(accountId);
        if (transactions != null) {
            transactions.addLast(transaction);
            transactionHistory.put(accountId, transactions);
        }
    }

    @Override
    public Transaction getTransactionById(String accountId, String transactionId) {
        LinkedList<Transaction> transactionsForUser = transactionHistory.get(accountId);
        if (transactionsForUser != null) {
            for (Transaction transaction : transactionsForUser) {
                if (transaction.getTransactionId().equals(transactionId)) {
                    return transaction;
                }
            }
        }
        return null;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        LinkedList<Transaction> result = new LinkedList<>();
        transactionHistory.values().forEach(result::addAll);
        return result;
    }
}
