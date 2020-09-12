package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;
import com.tiurinvalery.agile.engine.dto.Action;
import com.tiurinvalery.agile.engine.dto.Transaction;
import com.tiurinvalery.agile.engine.exception.AccountNotFoundException;
import com.tiurinvalery.agile.engine.exception.BadRequestException;
import com.tiurinvalery.agile.engine.exception.InternalServerError;
import com.tiurinvalery.agile.engine.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CreditOperationService creditOperationService;
    private final DebitOperationService debitOperationService;
    private final TransactionHistoryService transactionHistoryService;

    private ConcurrentLinkedDeque<Transaction> transactionsQueue = new ConcurrentLinkedDeque<>();

    @Override
    public Account getAccountById(String accountId) {
        if (accountId != null && !accountId.isEmpty()) {
            return accountRepository.findById(accountId);
        } else {
            throw new BadRequestException("Account id should be present");
        }

    }

    @Override
    public void doTransaction(Transaction transaction) {
        LinkedList<Transaction> tempSave = new LinkedList<>();
        while (!transactionsQueue.isEmpty()) {
            Transaction lastElement = transactionsQueue.poll();
            if (lastElement.getDate().before(transaction.getDate())) {
                transactionsQueue.push(lastElement);
                transactionsQueue.push(transaction);
                tempSave.forEach(transactionsQueue::addLast);
                return;
            } else {
                tempSave.addLast(lastElement);
            }
        }
        transactionsQueue.addFirst(transaction);
        tempSave.forEach(transactionsQueue::addLast);
    }

    /**
     * My technical approach based on possible delay in real transaction applying.
     * I believe that my transactions always ordered correctly, if this rule is broken I will throw log and fix it.
     */
    @Scheduled(fixedDelay = 5000)
    private void applyTransactions() {
        Date lastOperationDate = new Date(0);
        while (!transactionsQueue.isEmpty()) {
            Transaction firstTransaction = transactionsQueue.pollFirst();
            Account account = getAccountById(firstTransaction.getAccountId());
            if (account == null) {
                throw new AccountNotFoundException("For transaction" + firstTransaction.getTransactionId() + " , account is not found");
            }
            if (lastOperationDate.before(firstTransaction.getDate())) {
                lastOperationDate = firstTransaction.getDate();
            } else {
                throw new InternalServerError("Transaction queue was in non-consistent");
            }
            Double result;
            if (firstTransaction.getAction() == Action.CREDIT) {
                result = creditOperationService.applyCreditOnAccount(account, firstTransaction.getSum());
            } else {
                result = debitOperationService.applyDebitOnAccount(account, firstTransaction.getSum());
            }
            System.out.println("Current sum on account: " + result); // For observability of state
            transactionHistoryService.push(firstTransaction);
        }
    }
}
