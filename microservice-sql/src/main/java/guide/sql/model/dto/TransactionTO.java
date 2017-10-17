package guide.sql.model.dto;

import guide.sql.model.Transaction;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 16.10.2017
 */
public class TransactionTO {

    private final String id;
    private final Integer amount;
    private final String address;

    private TransactionTO() {
        this.id = null;
        this.address = null;
        this.amount = null;
    }

    private TransactionTO(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.address = transaction.getAddress();
    }

    public static TransactionTO of(Transaction transaction) {
        return (transaction == null)
                ? null
                : new TransactionTO(transaction);
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionTO that = (TransactionTO) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
