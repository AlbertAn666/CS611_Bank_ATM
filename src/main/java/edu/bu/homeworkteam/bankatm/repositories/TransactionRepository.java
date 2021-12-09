package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gung
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction,Integer> {

    /**
     * Create a new transaction
     * @return a new empty transaction with a valid customer ID, and the creation time
     */
    default Transaction create(){
        Transaction transaction=new Transaction();
        save(transaction);
        return transaction;
    }

}

