package io.spring.guide.repository.sql;

import io.spring.guide.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * "default comment"
 *
 * @author Anton Kurako (anku0817)
 * @since 12.10.2017
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
