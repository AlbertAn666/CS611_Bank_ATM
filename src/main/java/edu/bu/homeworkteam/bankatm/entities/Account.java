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
    private int id; //  auto-generated, unique, unchangeable. e.g. 918233

    /**
     * if this account type is credit, then the balance is allowed to be negative,
     * which represents that the customer owes the bank some money.
     */
    @ElementCollection
    //@CollectionTable(name = "customer_balances")
    @MapKeyColumn(name = "currency")
    @Column(name ="balance")
    private Map<Currency, Float> balances= new HashMap<>();

    @ManyToOne
    private Customer customer; // the owner

    private AccountType accountType; //
}


