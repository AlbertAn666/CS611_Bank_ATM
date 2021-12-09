package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}


