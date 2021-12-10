package edu.bu.homeworkteam.bankatm.entities;

import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // auto-generated, unique, unchangeable. e.g. 23442
    private String name; // not always unique. e.g. “Sam”
    private String password;

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
    private Map<Currency, Float> loans=new HashMap<>();

    /*
    the value of mappedBy is the name of the association-mapping attribute on the owning side
    mappedBy indicates the corresponding member variable in Account class
    using the already created relationship table, rather than creating a new one.

    The annotation of @OneToMany association is marked with the mappedBy attribute
    which indicates that the @ManyToOne side is responsible for handling this bidirectional association.
    for the mapped-by @OneToMany field, it is a unidirectional update when we save the Customer using the
    repository.save(). It only updates the field in the heap with changes from database, doesn't
    update the field in the database with changes from the heap, because in the database level, the association
    is not maintained by this side.
    */
    @OneToMany(mappedBy = "customer")
    private List<Account> accounts=new ArrayList<>();

    @ElementCollection
    //@CollectionTable(name = "customer_numbers_of_shares")
    @MapKeyJoinColumn(name = "stock_id")
    @Column(name ="number_of_shares")
    private Map<Stock, Integer> numbersOfShares= new HashMap<>();

}




