package edu.bu.homeworkteam.bankatm.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author gung
 *
 */
@Entity
public class Customer {
    @Id
    int id;
    String name;
    String password;
}
