package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    //mappedBy indicates the corresponding member variable in Account class
    //using the already created relationship table, rather than creating a new one.
    @OneToMany(mappedBy = "customer")
    List<Account> accounts;

    @ElementCollection
    @CollectionTable(name = "customer_number_of_shares")
    @MapKeyJoinColumn(name = "stock_id")
    @Column(name ="number_of_shares")
    Map<Stock, Integer> numbersOfShares;
}




