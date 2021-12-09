package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author gung
 */
@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue
    int id;
    String symbol; //e.g. AAPL, MSFT
    String name;//e.g. Apple Inc., Microsoft Corporation
    Exchange exchange; //e.g. NASDAQ, NYSE, HKEX
    float price;
}
