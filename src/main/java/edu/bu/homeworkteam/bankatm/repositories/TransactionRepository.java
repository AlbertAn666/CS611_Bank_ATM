package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.hibernate.usertype.CompositeUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    /**
     * get a list of transactions by customer id
     * @param customerId
     * @return
     */

    default List<Transaction> getTransactionsByCustomerId(Integer customerId){

        List<Transaction> resultList=new ArrayList<>();
        Iterable<Transaction> transactionIterable=findAll();

        for (Transaction transaction : transactionIterable) {
            Account toAccount = transaction.getToAccount();

            if (toAccount != null) {
                //System.out.println(" 8768798 "+toAccount.getId());
                Customer toCustomer = toAccount.getCustomer();
                if (toCustomer != null) {
                    //System.out.println("78888 "+toCustomer.getId());
                    if (toCustomer.getId() == customerId) {
                        //System.out.println("khkdf");
                        resultList.add(transaction);
                        continue;
                    }
                }
            }

            Account fromAccount = transaction.getFromAccount();
            if (fromAccount != null) {
                Customer fromCustomer = fromAccount.getCustomer();
                if (fromCustomer != null) {
                    if (fromCustomer.getId() == customerId) {
                        resultList.add(transaction);
                    }
                }
            }
        }

        return resultList;
    }

}

