package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Shareholding {
    int numberOfShares;
    int averageCostPricePerShare;
}
