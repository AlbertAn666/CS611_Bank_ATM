package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;

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
    boolean balance;


    @ManyToOne
    Customer customer;
}


