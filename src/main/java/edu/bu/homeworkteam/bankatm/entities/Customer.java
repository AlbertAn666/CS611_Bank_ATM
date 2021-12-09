package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @author gung
 *
 */
@Data
@Entity

public class Customer {
    @Id
    @GeneratedValue
    int id;
    String name;
    String password;

    /*
    for example
    if the customer borrow 100usd from the bank, then the corresponding balance in his account will increase by 100usd,
    the loan will increase by 100usd;
    if the customer return 200usd to the bank, then the corresponding balance in his account will reduce by 200usd,
    the loan will reduce by 200usd;
     */
    @ElementCollection
    //@CollectionTable(name = "customer_numbers_of_shares")
    @MapKeyColumn(name = "currency")
    @Column(name ="loan")
    Map<Currency, Float> loans;

    //credit
    //mappedBy indicates the corresponding member variable in Account class
    //using the already created relationship table, rather than creating a new one.
    @OneToMany(mappedBy = "customer")
    List<Account> accounts;

    @ElementCollection
    //@CollectionTable(name = "customer_numbers_of_shares")
    @MapKeyJoinColumn(name = "stock_id")
    @Column(name ="number_of_shares")
    Map<Stock, Integer> numbersOfShares;
}




