package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RepositoryDemo {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @EventListener(ApplicationReadyEvent.class)
    //automatically run the method when Spring Application ready
    public void runCustomerDemo(){
        /*
          create a new customer with new valid customer ID
         */
        Customer customer=customerRepository.create();
        System.out.println("The new customer's ID is "+customer.getId());


        /*
          save the customer after changing some of the fields
         */
        customer.setName("Sarah");
        customerRepository.save(customer);


        /*
         * find a customer by customer ID
         */
        int customerId=20;
        Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            //if found
            Customer customerFound=optionalCustomer.get();
            System.out.println("The name of the customer with customer ID "+customerId+" is "+customerFound.getName());
        }else{
            //if not found
            System.out.println("Unable to find a customer with customer ID "+customerId);
        }

    }


    @EventListener(ApplicationReadyEvent.class)
    //automatically run the method when Spring Application ready
    public void runTransactionDemo(){
        /*
          create a new transaction with valid Id and creation time;
         */
        Transaction transaction= transactionRepository.create();
        System.out.println(transaction.getDateTimeString());
        System.out.println(transaction.toString());



    }
}
