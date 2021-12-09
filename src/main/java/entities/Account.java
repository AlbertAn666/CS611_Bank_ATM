package entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author gung
 *
 */
@Entity
public class Account {
    @Id
    int id;
    boolean balance;
}
