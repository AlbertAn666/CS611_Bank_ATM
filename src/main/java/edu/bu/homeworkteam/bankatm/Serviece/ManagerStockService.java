package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.Stock;
import edu.bu.homeworkteam.bankatm.pagesUI.GuiManager;
import edu.bu.homeworkteam.bankatm.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Vector;


@Component
public class ManagerStockService {
    @Autowired
    StockRepository stockRepository;

    public int addStock(String symbol, String name, float price, Currency currency) {
        Stock newStock = GuiManager.getInstance().getStockRepository().create();
        newStock.setSymbol(symbol);
        newStock.setName(name);
        newStock.setCurrency(currency);
        newStock.setPrice(price);
        stockRepository.save(newStock);

        return ServiceConfig.OK;
    }

    public int modifyPrice(int stockId, float price) {
        Optional<Stock> optionalStock =GuiManager.getInstance().getStockRepository().findById(stockId);
        if(optionalStock.isEmpty()) return ServiceConfig.STOCK_ERROR;
        Stock stock = optionalStock.get();
        stock.setPrice(price);

        stockRepository.save(stock);
        return ServiceConfig.OK;
    }

    public Vector<Vector<String>> getStockInfo() {
        Vector<Vector<String>> ret = new Vector<>();
        Iterable<Stock> stockIterable = GuiManager.getInstance().getStockRepository().findAll();
        for(Stock stock: stockIterable) {
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(stock.getId()));
            temp.add(stock.getSymbol());
            temp.add(stock.getName());
            temp.add(stock.getCurrency().toString());
            temp.add(String.valueOf(stock.getPrice()));

            ret.add(temp);
        }
        return ret;
    }
}
