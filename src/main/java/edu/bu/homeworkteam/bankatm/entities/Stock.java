package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    int id; //  auto-generated, unique, unchangeable. e.g. 918233

    String symbol; //e.g. AAPL, MSFT
    String name;//e.g. Apple Inc., Microsoft Corporation
    Exchange exchange; //e.g. NASDAQ, NYSE, HKEX
    float price;
}
