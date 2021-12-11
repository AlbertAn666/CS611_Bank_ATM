package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginService {
    @Autowired
    CustomerRepository customerRepository;

    public Customer loginCustomer(int userID, String password) {
        String id = String.valueOf(userID);
        Optional<Customer> optionalCustomer = customerRepository.findById(userID);
        if(optionalCustomer.isPresent()) {
            //if found
            Customer customerFound = optionalCustomer.get();
            System.out.println("The name of the customer with customer ID " + id + " is " + customerFound.getName());
            if (customerFound.getPassword().equals(password)) {
                return customerFound;
            }
        }
        System.out.println("UserName/Password error.");
        return null;
    }

    public Customer register(String name, String password) {
        Customer customer = customerRepository.create();
        customer.setName(name);
        customer.setPassword(password);
        customerRepository.save(customer);
        return customer;
    }
}
