package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


/**
 * @author gung
 * UniversalRepository handles complicated data queries
 */
@Repository
public class UniversalRepository {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;

}
