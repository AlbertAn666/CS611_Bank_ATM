package edu.bu.homeworkteam.bankatm.entities;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Vector;

/**
 * @author gung
 * transaction
 */
@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //  auto-generated, unique, unchangeable. e.g. 918233

    private Instant instant= Instant.now(); // the creation time

    @ManyToOne
    private Account fromAccount;

    @ManyToOne
    private Account toAccount;

    private Currency currency;
    private float amount;
    private TransactionType transactionType; //DEPOSIT, WITHDRAW, TRANSFER

    private String note="";

    /**
     * get the String of the formatted creation time in American Eastern Standard Time
     * @return  the formatted time in American Eastern Standard Time
     */
    public String getDateTimeString(){

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, YYYY 'at' hh:mm:ss a zzzz").withZone(ZoneId.of("America/New_York"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z").withZone(ZoneId.of("America/New_York"));
        return formatter.format(instant);
    }

    public Vector<String> getTransaction() {
        Vector<String> temp = new Vector<>();
        temp.add(String.valueOf(this.getId()));
        temp.add(this.getDateTimeString());
        temp.add(EntitiesConfig.getTransactionType(this.getTransactionType()));
        temp.add(String.valueOf(this.getFromAccount()));
        temp.add(String.valueOf(this.getToAccount()));
        temp.add(EntitiesConfig.getCurrencyType(this.getCurrency()));
        temp.add(String.valueOf(this.getAmount()));
        return temp;
    }



}
