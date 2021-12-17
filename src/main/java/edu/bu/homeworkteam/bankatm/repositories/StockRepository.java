package edu.bu.homeworkteam.bankatm.repositories;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Stock;
import edu.bu.homeworkteam.bankatm.entities.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Stock repository,deals with the data access and storage of stocks
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

    /**
     * Get all the stocks of a specific symbol
     * @param symbol the stocks symbol
     * @return the list of stocks
     */
    default Stock getStockBySymbol(String symbol) {
        Iterable<Stock> stockIterable = findAll();
        for(Stock stock: stockIterable) {
            if(stock.getSymbol().equals(symbol)) {
                return stock;
            }
        }
        return null;
    }

}

