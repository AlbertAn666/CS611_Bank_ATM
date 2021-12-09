package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gung
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {

    /**
     * Create a new customer
     * @return a new empty customer with a valid customer ID
     */
    default Customer create(){
        Customer customer=new Customer();
        save(customer);
        return customer;
    }

}

