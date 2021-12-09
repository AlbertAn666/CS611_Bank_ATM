package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gung
 * account
 */
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; //  auto-generated, unique, unchangeable. e.g. 918233



    @ElementCollection
    //@CollectionTable(name = "customer_balances")
    @MapKeyColumn(name = "currency")
    @Column(name ="balance")
    Map<Currency, Float> balances= new HashMap<>();

    @ManyToOne
    Customer customer; // the owner

    AccountType accountType; //
}


