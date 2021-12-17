package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *  the login and register service logic for customer and admin(manager)
 */
@Service
public class LoginService {
    @Autowired
    CustomerRepository customerRepository;

    public int loginCustomer(int customerId, String password) {
        String id = String.valueOf(customerId);
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()) {
            //if found
            Customer customerFound = optionalCustomer.get();
            System.out.println("The name of the customer with customer ID " + id + " is " + customerFound.getName());
            if (customerFound.getPassword().equals(password)) {
                return ServiceConfig.OK;
            }
        }
        System.out.println("UserName/Password error.");
        return ServiceConfig.PASSWORD_ERROR;
    }

    public int register(String name, String password) {
        Customer customer = customerRepository.create();
        customer.setName(name);
        customer.setPassword(password);
        customerRepository.save(customer);
        return customer.getId();
    }
}
