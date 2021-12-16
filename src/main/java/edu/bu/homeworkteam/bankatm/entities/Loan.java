///**
// * newly added class
// */
//package edu.bu.homeworkteam.bankatm.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//
//@Entity
//@Data
//public class Loan {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private Instant instant = Instant.now();
//
//    @ManyToOne
//    private Account account;
//
//    @ManyToOne
//    private Customer customer;
//
//    private Currency currency;
//    private float amount;
//
//    public String getDataTimeString() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("America/New_York"));
//        return formatter.format(instant);
//    }
//}
