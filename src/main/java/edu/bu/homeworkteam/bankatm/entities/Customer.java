package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

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
    @ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name = "customer_numbers_of_shares")
    @MapKeyColumn(name = "currency")
    @Column(name ="loan")
    private Map<Currency, Float> loans=new HashMap<>(); //key: currency; value: loans;

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
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Account> accounts=new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_shareholdings")
    @MapKeyJoinColumn(name = "stock_id")
    //@Column(name ="number_of_shares")
    private Map<Stock, Shareholding> shareholdings = new HashMap<>(); //key: stock; value: shareholding


    /*
    save(customer) with null id
    Hibernate: insert into customer (name, password) values (?, ?)
     */
    /*
       save(customer)
    Hibernate: select customer0_.id as id1_2_0_, customer0_.name as name2_2_0_, customer0_.password as password3_2_0_ from customer customer0_ where customer0_.id=?
    Hibernate: select loans0_.customer_id as customer1_3_0_, loans0_.loan as loan2_3_0_, loans0_.currency as currency3_0_ from customer_loans loans0_ where loans0_.customer_id=?
    Hibernate: select numbersofs0_.customer_id as customer1_4_0_, numbersofs0_.number_of_shares as number_o2_4_0_, numbersofs0_.stock_id as stock_id3_0_, stock1_.id as id1_5_1_, stock1_.exchange as exchange2_5_1_, stock1_.name as name3_5_1_, stock1_.price as price4_5_1_, stock1_.symbol as symbol5_5_1_ from customer_numbers_of_shares numbersofs0_ inner join stock stock1_ on numbersofs0_.stock_id=stock1_.id where numbersofs0_.customer_id=?
    Hibernate: update customer set name=?, password=? where id=?
    */

    /*
    findById
    Hibernate: select customer0_.id as id1_2_0_, customer0_.name as name2_2_0_, customer0_.password as password3_2_0_ from customer customer0_ where customer0_.id=?
     */
}




