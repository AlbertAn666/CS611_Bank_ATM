package edu.bu.homeworkteam.bankatm.entities;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author gung
 * account
 */
@Entity
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //  auto-generated, unique, unchangeable. e.g. 918233

    /**
     * if this account type is credit, then the balance is allowed to be negative,
     * which represents that the customer owes the bank some money.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    //@CollectionTable(name = "customer_balances")
    @MapKeyColumn(name = "currency")
    @Column(name ="balance")
    private Map<Currency, Float> balances= new HashMap<>(); //key: currency; value: balance;

    @ManyToOne
    private Customer customer; // the owner

    private AccountType accountType; //

    public Vector<Vector<String>> getAccountBalances() {
        Vector<Vector<String>> ret = new Vector<>();
        Map<Currency, Float> balances = this.getBalances();

        for(Currency currency: balances.keySet()) {
            Vector<String> temp = new Vector<>();
            temp.add(EntitiesConfig.getCurrencyType(currency));
            temp.add(balances.get(currency).toString());

            ret.add(temp);
        }
        return ret;
    }
}


