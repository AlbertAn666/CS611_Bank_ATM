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

    /**
     * Get all the stocks of a specific symbol
     * @param symbol the stocks symbol
     * @return the list of stocks
     */
    default List<Stock> getStocksBySymbol(String symbol) {
        List<Stock> resultList = new ArrayList<>();
        Iterable<Stock> stockIterable = findAll();
        for(Stock stock: stockIterable) {
            if(stock.getSymbol().equals(symbol)) {
                resultList.add(stock);
            }
        }
        return resultList;
    }

    /**
     * Get all the stocks in market
     * @return the list of stocks
     */
    default List<Stock> getAllStocksInMarket() {
        List<Stock> resultList = new ArrayList<>();
        Iterable<Stock> stockIterable = findAll();
        for(Stock stock: stockIterable) {
            if(stock.isInMarket()) {
                resultList.add(stock);
            }
        }
        return resultList;
    }

    /**
     * Get all the stocks of a specific symbol in market
     * @param symbol the symbol of stocks
     * @return the list of stocks
     */
    default List<Stock> getStocksInMarket(String symbol) {
        List<Stock> stocks = getStocksBySymbol(symbol);
        List<Stock> resultList = new ArrayList<>();
        for(Stock stock: stocks) {
            if(stock.isInMarket())
                resultList.add(stock);
        }
        return resultList;
    }

    /**
     * Get all the stocks of a specific customer
     * @param customer the customer of the stocks
     * @return the list of stocks
     */
    default List<Stock> getStocksOfCustomer(Customer customer) {
        List<Stock> resultList = new ArrayList<>();
        Iterable<Stock> stockIterable = findAll();
        for(Stock stock: stockIterable) {
            if(stock.getOwner().getId() == customer.getId()) {
                resultList.add(stock);
            }
        }
        return resultList;
    }

    /**
     * Get all the stocks of a specific customer and of specific symbol
     * @param customer the customer of the stocks
     * @param symbol the symbol of the stocks
     * @return the list of stocks
     */
    default List<Stock> getStocksOfCustomerAndSymbol(Customer customer, String symbol) {
        List<Stock> customerStocks = getStocksOfCustomer(customer);
        List<Stock> resultList = new ArrayList<>();
        for(Stock stock: customerStocks) {
            if(stock.getSymbol().equals(symbol)) {
                resultList.add(stock);
            }
        }
        return resultList;
    }
}

