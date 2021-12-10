package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Transient;

/**
 * Customer repository
 * @author gung
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    /**
     * Create a new customer
     * @return a new empty customer with a valid customer ID
     * insert into customer (name, password) values (?, ?)
     */
    default Customer create(){
        Customer customer=new Customer();
        save(customer);
        return customer;
    }





}

