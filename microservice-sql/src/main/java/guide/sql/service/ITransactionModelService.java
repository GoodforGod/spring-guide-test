package guide.sql.service;


import guide.sql.model.Transaction;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
public interface ITransactionModelService extends IBasicModelService<Transaction, String> {

    Transaction create(String address, Integer amount);
}
