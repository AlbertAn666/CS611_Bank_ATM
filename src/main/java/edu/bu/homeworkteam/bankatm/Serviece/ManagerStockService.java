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
        newStock.setLastPrice(price);
        newStock.setOwner(null);
        newStock.setInMarket(true);
        stockRepository.save(newStock);

        return ServiceConfig.OK;
    }

    public int modifyPriceForAllStocks(String symbol, float price) {
        List<Stock> stocks = stockRepository.getStocksBySymbol(symbol);
        for(Stock stock: stocks) {
            stock.setPrice(price);
            stockRepository.save(stock);
        }
        return ServiceConfig.OK;
    }

    public int modifyPriceForSingleStock(int stockId, float price) {
        Optional<Stock> stockOptional = stockRepository.findById(stockId);
        if(stockOptional.isEmpty()) {
            System.out.println("Error stock Id");
            return ServiceConfig.STOCK_ERROR;
        }
        Stock stock = stockOptional.get();
        stock.setPrice(price);
        stockRepository.save(stock);
        return ServiceConfig.OK;
    }
}
