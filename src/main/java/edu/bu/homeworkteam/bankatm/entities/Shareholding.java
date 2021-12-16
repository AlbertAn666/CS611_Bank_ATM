package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.Embeddable;

/**
 *
 * @author gung
 * shareholding records the holding information with respect to a stock
 */
@Embeddable
@Data
public class Shareholding {
    private int numberOfShares;
    private float averageCostPricePerShare;
}
