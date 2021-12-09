package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Integer> {
}

