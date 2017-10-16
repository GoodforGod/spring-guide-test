package io.spring.guide.service.modelbased.impl;

import io.spring.guide.model.Transaction;
import io.spring.guide.repository.TransactionRepository;
import io.spring.guide.service.modelbased.ITransactionModelService;
import org.springframework.stereotype.Service;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Service
public class TransactionService extends BasicJpaModelService<Transaction, String>
                                implements ITransactionModelService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
