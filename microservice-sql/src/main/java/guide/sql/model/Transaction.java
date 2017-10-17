package guide.sql.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Entity
@Table(name = "transactions")
public class Transaction extends BasicJpaModel<String> {

    private Integer amount;
    private String address;

    private Transaction() {
        super(UUID.randomUUID().toString());
    }

    public Transaction(Integer amount, String address) {
        this();
        this.amount = amount;
        this.address = address;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getAddress() {
        return address;
    }
}
