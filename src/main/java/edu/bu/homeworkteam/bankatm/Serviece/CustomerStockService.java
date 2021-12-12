package edu.bu.homeworkteam.bankatm.Serviece;

import com.sun.xml.bind.CycleRecoverable;
import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

@Component
public class CustomerStockService extends CustomerService {
    @Autowired
    StockRepository stockRepository;


    public boolean ableForStock(Customer customer) {
        List<Account> accountList = customer.getAccounts();
        for(Account account: accountList) {
            if(!account.getBalances().containsKey(Currency.USD)) return false;
            if(account.getAccountType() == AccountType.SAVINGS &&
                    account.getBalances().get(Currency.USD) >= ServiceConfig.STOCK_REQUIRE) {
                return true;
            }
        }
        return false;
    }

    public int creatSecAccount(Customer customer, float deposit) {
        return super.createAccount(customer, AccountType.SECURITIES, deposit);
    }

    public int transferForStock(int fromAccount, Customer customer, float value) {
        Optional<Account> optionalAccount = accountRepository.findById(fromAccount);
        if(value <= 1000) return ServiceConfig.BELOW_LIMIT;
        if(optionalAccount.isEmpty()) {
            System.out.println("Not found account.");
            return ServiceConfig.ACCOUNT_ERROR;
        }
        if(optionalAccount.get().getAccountType() != AccountType.SAVINGS) {
            System.out.println("Not savings account");
            return ServiceConfig.NOT_SAVINGS;
        }
        float currentMoney = 0;
        if(optionalAccount.get().getBalances().containsKey(Currency.USD))
            currentMoney = optionalAccount.get().getBalances().get(Currency.USD);
        if(value >= currentMoney - 2500) return ServiceConfig.NOT_ENOUGH_MONEY;
        return super.transferMoney(fromAccount, customer.getSecAccount().getId(), customer, Currency.USD, value, "");
    }

    public Vector<Vector<String>> showStocksOfCustomer(Customer customer) {
        List<Stock> allStocks = stockRepository.getStocksOfCustomer(customer);
        return getStocksInfo(allStocks);
    }

    public Vector<Vector<String>> showAllStocksInMarket() {
        List<Stock> allStocks = stockRepository.getAllStocksInMarket();
        return getStocksInfo(allStocks);
    }

    public Vector<Vector<String>> showMarketStocksBySymbol(String symbol) {
        List<Stock> allStocks = stockRepository.getStocksInMarket(symbol);
        return getStocksInfo(allStocks);
    }

    private Vector<Vector<String>> getStocksInfo(List<Stock> allStocks) {
        Vector<Vector<String>> res = new Vector<>();
        for(Stock stock: allStocks) {
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(stock.getId()));
            temp.add(stock.getName());
            temp.add(stock.getSymbol());
            temp.add(String.valueOf(stock.getPrice()));

            res.add(temp);
        }
        return res;
    }

    public List<Stock> buyStocksBySymbol(Customer customer, String symbol, int n) {
        List<Stock> allStocks = stockRepository.getStocksInMarket(symbol);
        if(n > allStocks.size()) {
            System.out.println("Not enough stocks for sell.");
            return null;
        }

        // change the account money
        Account secAccount = customer.getSecAccount();
        float balance = 0;
        if(secAccount.getBalances().containsKey(Currency.USD))
            balance = secAccount.getBalances().get(Currency.USD);
        if(balance < n * allStocks.get(0).getPrice()) {
            System.out.println("Securities account not enough money to buy all the stocks");
            return null;
        }

        balance -= n * allStocks.get(0).getPrice();
        secAccount.getBalances().put(Currency.USD, balance);
        accountRepository.save(secAccount);

        List<Stock> stocks = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            Stock buyingStock = allStocks.get(i);

            // change the stock attributes
            buyingStock.setInMarket(false);
            Customer lastOwner = buyingStock.getOwner();
            buyingStock.setOwner(customer);
            // if the stock has the last owner, calculate the profit and set its attributes
            if(lastOwner != null) {
                float totalProfit = lastOwner.getTotalStockProfit();
                float profit = buyingStock.getPrice() - buyingStock.getLastPrice();
                totalProfit += profit;
                lastOwner.setTotalStockProfit(totalProfit);
                float beforeBalance = 0;
                if(lastOwner.getSecAccount().getBalances().containsKey(Currency.USD))
                    beforeBalance = lastOwner.getSecAccount().getBalances().get(Currency.USD);
                lastOwner.getSecAccount().getBalances().put(Currency.USD, beforeBalance + profit);
                customerRepository.save(lastOwner);
            }
            buyingStock.setLastPrice(buyingStock.getPrice());

            stockRepository.save(buyingStock);
            stocks.add(buyingStock);
        }
        return stocks;
    }

    public int sellStockById(Customer customer, int stockId) {
        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        if(optionalStock.isEmpty()) {
            System.out.println("Stock Id Error.");
            return ServiceConfig.STOCK_ERROR;
        }
        Stock stock = optionalStock.get();
        if(stock.getOwner().getId() != customer.getId()) {
            System.out.println("Stock Id Error.");
            return ServiceConfig.STOCK_ERROR;
        }

        // change stock attributes
        stock.setInMarket(true);
        stockRepository.save(stock);

        return ServiceConfig.OK;
    }

    public int sellStocksBySymbol(Customer customer, String symbol, int n) {
        List<Stock> stocks = stockRepository.getStocksOfCustomerAndSymbol(customer, symbol);
        if(n > stocks.size()) {
            System.out.println("Not enough stocks");
            return ServiceConfig.NOT_ENOUGH_STOCKS;
        }
        for(int i = 0; i < n; i++) {
            Stock stock = stocks.get(i);
            stock.setInMarket(true);
            stockRepository.save(stock);
        }
        return ServiceConfig.OK;
    }

    public float getOpenPosition(Customer customer) {
        if(customer.getSecAccount() != null) {
            if(customer.getSecAccount().getBalances().containsKey(Currency.USD))
                return customer.getSecAccount().getBalances().get(Currency.USD);
            return 0;
        }
        return ServiceConfig.NO_SEC_ACCOUNT;
    }

    public float[] getProfit(Customer customer) {
        // realized profit
        float realizedProfit = customer.getTotalStockProfit();
        // unrealized profit
        float unrealizedProfit = 0;
        List<Stock> allStock = stockRepository.getStocksOfCustomer(customer);
        for(Stock stock: allStock) {
            unrealizedProfit += stock.getPrice() - stock.getLastPrice();
        }
        return new float[]{realizedProfit, unrealizedProfit};
    }
}
