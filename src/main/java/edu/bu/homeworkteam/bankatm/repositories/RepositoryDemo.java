package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

/**
 * a demo for development
 * @author gung
 */
@Component
public class RepositoryDemo {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    UniversalRepository universalRepository;
    //@EventListener(ApplicationReadyEvent.class)
    //automatically run the method when Spring Application ready
    public void runDemo(){
        /*
          create a new customer with new valid customer ID
         */
        Customer customer=customerRepository.create();
        System.out.println("The new customer's ID is "+customer.getId());


        /*
          save the customer after changing some of the fields
         */
        customer.setName("Sarah");
        customer.setPassword("aaa");
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

        /*
         * Please note that with respect to our project, the OneToMany Customer-Account association relationship
         * is handled by the Account side, rather than the Customer side. so the when we change the customer.accounts field and save it,
         * actually nothing wil happen in the database.
         *
         * Following are demos.
         * @author gung
         *
         */

        /*
         * INCORRECT Demonstration
         */
        Account account = accountRepository.create();//create a empty account with Id.
        customer.getAccounts().add(account); // assign the account to the customer created previously through CUSTOMER class
        customerRepository.save(customer); //update the customer.
        accountRepository.save(account);//update the account.
        System.out.println(customer.getAccounts().size());//1
        if(account.getCustomer()==null){
            System.out.println("The account "+account.getId()+" has not assigned to any customer.");//The account 1 has not assigned to any customer.
        }

        /*
         * Correct Demonstration
         */
        account.setCustomer(customer); // assign the account to the customer Sarah created previously through ACCOUNT class
        accountRepository.save(account);//update the account
        customerRepository.save(customer); //update the customer
        System.out.println("The number of accounts of "+customer.getName()+" is "+customer.getAccounts().size()+"."); //The number of accounts of Sarah is 1.


        /*
         * create a stock and add it to a customer
         */
        Stock appleStock= stockRepository.create();
        appleStock.setName("Apple, Inc.");
        appleStock.setSymbol("AAPL");
        stockRepository.save(appleStock);

        Shareholding appleShareholding =new Shareholding();
        appleShareholding.setAverageCostPricePerShare(234);
        appleShareholding.setNumberOfShares(3411);

        customer.getShareholdings().put(appleStock,appleShareholding);
        customerRepository.save(customer);





        /*
          create a new transaction with valid Id and creation time;
         */
        Transaction transaction= transactionRepository.create();
        System.out.println(transaction.getDateTimeString());
        System.out.println(transaction.toString());


        /*
         * get a list of transactions by customer id
         */


        List<Transaction> transactionList=transactionRepository.getTransactionsByCustomerId(30);
        for (Transaction transactionInList :
                transactionList) {
            System.out.println("Transaction: "+transactionInList.getId());
        }



    }


    //@EventListener(ApplicationReadyEvent.class)
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
