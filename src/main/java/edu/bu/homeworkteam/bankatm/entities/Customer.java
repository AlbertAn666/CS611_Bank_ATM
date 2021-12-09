package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
}
