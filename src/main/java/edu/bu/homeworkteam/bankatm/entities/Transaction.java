package edu.bu.homeworkteam.bankatm.entities;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author gung
 * transaction
 */
@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id; //  auto-generated, unique, unchangeable. e.g. 918233

    Instant instant= Instant.now();

    @ManyToOne
    Account fromAccount;

    @ManyToOne
    Account toAccount;

    float amount;

    String note="";

    /**
     * get the String of the formatted time in American Eastern Standard Time
     * @return  the formatted time in American Eastern Standard Time
     */
    public String getDateTimeString(){

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, YYYY 'at' hh:mm:ss a zzzz").withZone(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss z").withZone(ZoneId.of("America/New_York"));
        return formatter.format(instant);
    }

}
