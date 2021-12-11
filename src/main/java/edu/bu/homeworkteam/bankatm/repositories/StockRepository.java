package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Stock;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock repository
 * @author gung
 */
@Repository
public interface StockRepository extends CrudRepository<Stock,Integer> {

    /**
     * Create a new stock
     * @return a new empty stock with a valid stock ID
     */
    default Stock create(){
        Stock stock=new Stock();
        save(stock);
        return stock;
    }
}

