package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Account repository
 * @author gung
 *
 */
@Repository
public interface AccountRepository extends CrudRepository<Account,Integer> {

    /**
     * Create a new account
     * @return a new empty account with a valid account ID
     */
    default Account create(){
        Account account=new Account();
        save(account);
        return account;
    }



}

