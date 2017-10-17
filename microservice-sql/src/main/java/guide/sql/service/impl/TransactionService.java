package guide.sql.service.impl;

import guide.sql.model.Transaction;
import guide.sql.repository.TransactionRepository;
import guide.sql.service.ITransactionModelService;
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

    @Override
    public Transaction create(String address, Integer amount) {
        return (address != null && amount != null)
                ? save(new Transaction(amount, address))
                : null;
    }
}
