package io.spring.guide.manager;

import io.spring.guide.service.modelbased.ITransactionModelService;
import io.spring.guide.service.modelbased.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
@Service
public class TransactionManager {

    private final ITransactionModelService transService;

    @Autowired
    public TransactionManager(TransactionService transactionService) {
        this.transService = transactionService;
    }

    public boolean proceed(String transId) {
        // imitate that do work
        transService.remove(transId);
        return true;
    }
}
