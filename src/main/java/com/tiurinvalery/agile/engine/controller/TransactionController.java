package com.tiurinvalery.agile.engine.controller;

import com.tiurinvalery.agile.engine.dto.Transaction;
import com.tiurinvalery.agile.engine.service.TransactionHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/transaction/v1")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionHistoryService transactionHistoryService;

    @GetMapping
    public ResponseEntity<List<Transaction>> fetchTransactions() {
        return new ResponseEntity<>(transactionHistoryService.getAllTransactions(), HttpStatus.OK);
    }
}
