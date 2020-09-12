package com.tiurinvalery.agile.engine.controller;

import com.tiurinvalery.agile.engine.dto.Transaction;
import com.tiurinvalery.agile.engine.service.AccountService;
import com.tiurinvalery.agile.engine.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequiredArgsConstructor
public class UserOperationController {

    @Value("${account.id}")
    private String testUserAccountId;

    private AtomicLong atomicLong = new AtomicLong(0);

    private final AccountService accountService;
    private final TransactionHistoryService transactionHistoryService;

    @PostMapping("/user/v1/transaction")
    public ResponseEntity<String> applyTransaction(@RequestBody Transaction transaction) {
        if (transaction != null) {
            transaction.setTransactionId(atomicLong.addAndGet(1));
            accountService.doTransaction(transaction);
        }
        return new ResponseEntity<>("ACCEPTED", HttpStatus.ACCEPTED);
    }

    @GetMapping("/user/v1/transaction/{transaction_id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(name = "transaction_id") String transactionId) {
        return new ResponseEntity<>(transactionHistoryService.getTransactionById(testUserAccountId, transactionId), HttpStatus.OK);
    }


}
