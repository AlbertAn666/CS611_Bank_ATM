package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Map;

/**
 * @author gung
 *
 */
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;


    @ElementCollection
    //@CollectionTable(name = "customer_balances")
    @MapKeyColumn(name = "currency")
    @Column(name ="balance")
    Map<Currency, Float> balances;

    @ManyToOne
    Customer customer;

    AccountType accountType;
}


