package edu.bu.homeworkteam.bankatm.Serviece;

import com.sun.xml.bind.CycleRecoverable;
import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * the customer stock service logic, including creating a stock account, buying/selling stocks,
 * showing stocks information, showing profit, etc.
 */
@Component
public class CustomerStockService extends CustomerService {
    @Autowired
    StockRepository stockRepository;

    public boolean ableForStock(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return false;
        Customer customer = optionalCustomer.get();

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

//    public int creatSecAccount(int customerId, float deposit) {
//        return super.createAccount(customerId, AccountType.SECURITIES, deposit);
//    }

//    public int transferForStock(int fromAccount, int customerId, float value) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        Optional<Account> optionalAccount = accountRepository.findById(fromAccount);
//        if(value <= 1000) return ServiceConfig.BELOW_LIMIT;
//        if(optionalAccount.isEmpty()) {
//            System.out.println("Not found account.");
//            return ServiceConfig.ACCOUNT_ERROR;
//        }
//        if(optionalAccount.get().getAccountType() != AccountType.SAVINGS) {
//            System.out.println("Not savings account");
//            return ServiceConfig.NOT_SAVINGS;
//        }
//        float currentMoney = 0;
//        if(optionalAccount.get().getBalances().containsKey(Currency.USD))
//            currentMoney = optionalAccount.get().getBalances().get(Currency.USD);
//        if(value >= currentMoney - 2500) return ServiceConfig.NOT_ENOUGH_MONEY;
//        return super.transferMoney(fromAccount, customer.getSecAccount().getId(), customerId, Currency.USD, value, "");
//    }

    public Vector<Vector<String>> showStocksOfCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        Customer customer = optionalCustomer.get();

        Vector<Vector<String>> ret = new Vector<>();
        Map<Stock, Shareholding> stockMap = customer.getShareholdings();
        for (Stock stock : stockMap.keySet()) {
            // stock id, symbol, name, price and number of shares
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(stock.getId()));
            temp.add(stock.getSymbol());
            temp.add(stock.getName());
            temp.add(String.valueOf(stock.getPrice()));
            temp.add(String.valueOf(stockMap.get(stock).getNumberOfShares()));

            ret.add(temp);
        }
      return ret;
    }

    public Vector<Vector<String>> showAllStocksInMarket() {
        Iterable<Stock> stockIterable = stockRepository.findAll();

        Vector<Vector<String>> ret = new Vector<>();

        for(Stock stock: stockIterable) {
            // stock id, symbol, name, price
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(stock.getId()));
            temp.add(stock.getSymbol());
            temp.add(stock.getName());
            temp.add(String.valueOf(stock.getPrice()));

            ret.add(temp);
        }
        return ret;
    }

//    public int buyStocksBySymbol(int customerId, String symbol, int n) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        Stock stock = stockRepository.getStockBySymbol(symbol);
//        if(stock == null) return ServiceConfig.STOCK_ERROR;
//        float price = stock.getPrice();
//        float totalPrice = n * price;
//
//        // change the account money
//        Account secAccount = customer.getSecAccount();
//        float balance = 0;
//        if(secAccount.getBalances().containsKey(Currency.USD))
//            balance = secAccount.getBalances().get(Currency.USD);
//        if(balance < totalPrice) {
//            System.out.println("Securities account not enough money to buy all the stocks");
//            return ServiceConfig.NOT_ENOUGH_MONEY;
//        }
//        balance -= totalPrice;
//        secAccount.getBalances().put(Currency.USD, balance);
//        accountRepository.save(secAccount);
//        customer.setSecAccount(secAccount);
//
//        // change the shares and the average price of the customer
//        Map<Stock,Shareholding> stockMap = customer.getShareholdings();
//        for (Stock stock1 : stockMap.keySet()) {
//            if(stock1.getSymbol().equals(symbol)) {
//                int newNumberOfShares = stockMap.get(stock1).getNumberOfShares() + n;
//                float newAveragePrice = (stockMap.get(stock1).getAverageCostPricePerShare() *
//                        stockMap.get(stock1).getNumberOfShares() + totalPrice) / newNumberOfShares;
//
//                stockMap.get(stock1).setNumberOfShares(newNumberOfShares);
//                stockMap.get(stock1).setAverageCostPricePerShare(newAveragePrice);
//                customer.setShareholdings(stockMap);
//                break;
//            }
//        }
//        customerRepository.save(customer);
//
//        return ServiceConfig.OK;
//    }




    public ServiceResult buyStock(int customerId, int stockId, int numberOfShares){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.get();
        Account securitiesAccount=customer.getSecuritiesAccount();
        if(securitiesAccount==null){
            return new ServiceResult(false,"You don't have a securities account");
        }

        Optional<Stock> optionalStock=stockRepository.findById(stockId);
        if(optionalStock.isEmpty()){
            return new ServiceResult(false, "We cannot find this stock");
        }
        Stock stock=optionalStock.get();

        Map<Stock,Shareholding> stockShareholdings=customer.getShareholdings();
        Shareholding shareholding=stockShareholdings.get(stock);
        if(shareholding==null) {
            shareholding=new Shareholding();
            shareholding.setNumberOfShares(0);
            shareholding.setAverageCostPricePerShare(0);
        }



        Float balance=securitiesAccount.getBalances().get(stock.getCurrency());
        if(balance==null){
            return new ServiceResult(false,"You don't have currency "+stock.getCurrency());
        }

        float costAmount=numberOfShares*stock.getPrice();
        if(balance<costAmount){
            return new ServiceResult(false,"You don't have enough money");
        }

        balance=balance-costAmount;
        securitiesAccount.getBalances().put(stock.getCurrency(),balance);
        int newNumberOfShares=shareholding.getNumberOfShares()+numberOfShares;
        float newAveragePrice=(shareholding.getAverageCostPricePerShare()*shareholding.getNumberOfShares()+costAmount)/newNumberOfShares;
        shareholding.setNumberOfShares(newNumberOfShares);
        shareholding.setAverageCostPricePerShare(newAveragePrice);
        stockShareholdings.put(stock,shareholding);

        accountRepository.save(securitiesAccount);
        customerRepository.save(customer);

        return new ServiceResult(true,"");
    }

    public ServiceResult sellStock(int customerId, int stockId, int numberOfShares){
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.get();
        Account securitiesAccount=customer.getSecuritiesAccount();
        if(securitiesAccount==null){
            return new ServiceResult(false,"You don't have a securities account");
        }

        Optional<Stock> optionalStock=stockRepository.findById(stockId);
        if(optionalStock.isEmpty()){
            return new ServiceResult(false, "We cannot find this stock");
        }
        Stock stock=optionalStock.get();

        Map<Stock,Shareholding> stockShareholdings=customer.getShareholdings();
        Shareholding shareholding=stockShareholdings.get(stock);
        if(shareholding==null){
            return new ServiceResult(false, "You don't have this stock.");
        }

        if(shareholding.getNumberOfShares()<numberOfShares){
            return new ServiceResult(false, "You don't have enough number of shares.");
        }

        shareholding.setNumberOfShares(shareholding.getNumberOfShares()-numberOfShares);

        Float balance=securitiesAccount.getBalances().get(stock.getCurrency());
        if(balance!=null){
            balance=balance+numberOfShares*stock.getPrice();
        }else{
            balance=numberOfShares*stock.getPrice();
        }
        securitiesAccount.getBalances().put(stock.getCurrency(),balance);

        float benefit=numberOfShares*(stock.getPrice()-shareholding.getAverageCostPricePerShare());
        float income=numberOfShares*stock.getPrice();

        accountRepository.save(securitiesAccount);
        customerRepository.save(customer);

        return new ServiceResult(true,"Your income is "+income+". Your benefit is "+benefit+". ");
    }

//
//    public int sellStockBySymbol(int customerId, String symbol, int n) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//
//        Map<Stock, Shareholding> stockMap = customer.getShareholdings();
//        float averageBuyingPrice = -1;
//        int totalNumberOfStocks = 0;
//        for (Stock stock : stockMap.keySet()) {
//            if (stock.getSymbol().equals(symbol)) {
//                averageBuyingPrice = stockMap.get(stock).getAverageCostPricePerShare();
//                totalNumberOfStocks = stockMap.get(stock).getNumberOfShares();
//                if (totalNumberOfStocks < n) return ServiceConfig.NOT_ENOUGH_STOCKS;
//
//                // set the remaining number of shares
//                int remainingShares = totalNumberOfStocks - n;
//                if (remainingShares == 0) stockMap.remove(stock);
//                else stockMap.get(stock).setNumberOfShares(remainingShares);
//
//                break;
//            }
//        }
//        if (averageBuyingPrice == -1) {
//            return ServiceConfig.STOCK_ERROR;
//        }
//
//        Stock stock = stockRepository.getStockBySymbol(symbol);
//        if (stock == null) return ServiceConfig.STOCK_ERROR;
//
//        // calculate profit and add to the total profit
//        float profit = (stock.getPrice() - averageBuyingPrice) * n;
//        customer.setTotalStockProfit(customer.getTotalStockProfit() + profit);
//        customer.setShareholdings(stockMap);
//
//        customerRepository.save(customer);
//        return ServiceConfig.OK;
//    }
//



//    public float getOpenPosition(int customerId) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        if(customer.getSecAccount() != null) {
//            if(customer.getSecAccount().getBalances().containsKey(Currency.USD))
//                return customer.getSecAccount().getBalances().get(Currency.USD);
//            return 0;
//        }
//        return ServiceConfig.NO_SEC_ACCOUNT;
//    }
//
//    public float getRealizedProfit(int customerId) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if (optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        // realized profit
//        return customer.getTotalStockProfit();
//    }

    public float getUnrealizedProfit(int customerId) {
        float unrealizedProfit = 0;
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;

        Customer customer = optionalCustomer.get();
        Map<Stock, Shareholding> stockMap = customer.getShareholdings();

        for (Stock stock : stockMap.keySet()) {
            int numberOfShares = stockMap.get(stock).getNumberOfShares();
            float averageBuyingPrice = stockMap.get(stock).getAverageCostPricePerShare();
            unrealizedProfit += numberOfShares * (stock.getPrice() - averageBuyingPrice);
        }
        return unrealizedProfit;
    }
}
