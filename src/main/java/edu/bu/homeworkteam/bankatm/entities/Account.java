package edu.bu.homeworkteam.bankatm.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author gung
 *
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int id;
    boolean balance;
}


