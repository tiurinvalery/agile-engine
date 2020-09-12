package com.tiurinvalery.agile.engine.service;

import com.tiurinvalery.agile.engine.dto.Account;
import com.tiurinvalery.agile.engine.exception.BadRequestException;
import com.tiurinvalery.agile.engine.exception.InternalServerError;
import com.tiurinvalery.agile.engine.exception.NegativeBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@RequiredArgsConstructor
public class CreditOperationServiceImpl implements CreditOperationService {

    LinkedList<Account> otherMicroserviceMock = new LinkedList<>();

    @Override
    public Double applyCreditOnAccount(Account account, Double sum) {
        Double result;
        if (sum.compareTo(0.0) > 0) {
            if (account != null) {
                synchronized (account) {
                    result = account.getSum() - sum;
                    if (result.compareTo(0.0) < 0) {
                        mockOtherMicroservice(account);
                        throw new NegativeBalanceException("Balance after operation less than 0");
                    } else {
                        account.setSum(result);
                    }
                }
            } else {
                throw new InternalServerError("Account null passing for credit");
            }
        } else {
            throw new BadRequestException("Sum for debit should be positive");
        }
        return result;
    }

    /*
     * Other microservice should handle this event and then send new event to client system.
     * Check answear about http correct status in ExceptionHandlingController.
     */
    private void mockOtherMicroservice(Account account) {
        otherMicroserviceMock.addLast(account);
        System.out.println("Resolve negative balance");
    }

}
