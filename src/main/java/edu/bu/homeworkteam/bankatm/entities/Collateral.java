package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity

public class Collateral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Currency currency;
    private float value;
    @ManyToOne
    private Customer customer;// the owner

}
