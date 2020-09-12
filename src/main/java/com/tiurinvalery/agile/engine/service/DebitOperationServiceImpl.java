package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;
import com.tiurinvalery.agile.engine.exception.AccountNotFoundException;
import com.tiurinvalery.agile.engine.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class DebitOperationServiceImpl implements DebitOperationService {

    @Override
    public Double applyDebitOnAccount(Account account, Double sum) {
        Double result;
        if (sum.compareTo(0.0) > 0) {
            if (account != null) {
                synchronized (account) {
                    result = account.getSum() + sum;
                    account.setSum(result);
                }
            } else {
                throw new AccountNotFoundException("Account null passing for debit");
            }
        } else {
            throw new BadRequestException("Sum for debit should be positive");
        }
        return result;
    }
}
