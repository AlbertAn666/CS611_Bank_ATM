package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.Stock;
import edu.bu.homeworkteam.bankatm.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ManagerStockService {
    @Autowired
    StockRepository stockRepository;

    public int addStock(String symbol, String name, float price) {
        Stock newStock = stockRepository.create();
        newStock.setSymbol(symbol);
        newStock.setName(name);
        newStock.setCurrency(Currency.USD);
        newStock.setPrice(price);
        stockRepository.save(newStock);

        return ServiceConfig.OK;
    }

    public int modifyPrice(String symbol, float price) {
        Stock stock = stockRepository.getStockBySymbol(symbol);
        if(stock == null) return ServiceConfig.STOCK_ERROR;
        stock.setPrice(price);

        stockRepository.save(stock);
        return ServiceConfig.OK;
    }
}
