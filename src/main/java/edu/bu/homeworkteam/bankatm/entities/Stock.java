package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;

/**
 *
 * @author gung
 * stock
 */
@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //  auto-generated, unique, unchangeable. e.g. 918233

    private String symbol; //e.g. AAPL, MSFT
    private String name;//e.g. Apple Inc., Microsoft Corporation
    private float price;
    private Currency currency;
}
