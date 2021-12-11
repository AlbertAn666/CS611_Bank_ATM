package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
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

    private int secAccountId;
    private float realizedProfit;

    public boolean ableForStock(Customer customer) {
        List<Account> accountList = customer.getAccounts();
        for(Account account: accountList) {
            if(account.getAccountType() == AccountType.SAVINGS &&
                    account.getBalances().get(Currency.USD) >= 5000) {
                return true;
            }
        }
        return false;
    }

    public void setSecAccountId(Customer customer) {
        this.secAccountId = getSecId(customer);
    }

    public int getSecId(Customer customer) {
        List<Account> accountList = customer.getAccounts();
        for(Account account: accountList) {
            if(account.getAccountType() == AccountType.SECURITIES) {
                return account.getId();
            }
        }
        return ServiceConfig.NO_SEC_ACCOUNT;
    }

    public int creatSecAccount(Customer customer) {
        int secId = super.createAccount(customer, AccountType.SECURITIES);
        this.secAccountId = secId;
        return secId;
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
        float currentMoney = optionalAccount.get().getBalances().get(Currency.USD);
        if(value >= currentMoney - 2500) return ServiceConfig.NOT_ENOUGH_MONEY;
        return super.transferMoney(fromAccount, secAccountId, customer, Currency.USD, value, "");
    }

    public Vector<Vector<String>> showAllStocks() {
        Iterable<Stock> optionalStock = stockRepository.findAll();
        Vector<Vector<String>> res = new Vector<>();
        // format: id, name, symbol, exchange, price(dollar)
        for(Stock stock: optionalStock) {
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(stock.getId()));
            temp.add(stock.getName());
            temp.add(stock.getSymbol());
            temp.add(getExchange(stock.getExchange()));
            temp.add(String.valueOf(stock.getPrice()));

            res.add(temp);
        }
        return res;
    }

    String getExchange(Exchange stockExchange) {
        switch (stockExchange) {
            case JPX:
                return "JPX";
            case NYSE:
                return "NYSE";
        }
        return "NASDAQ";
    }

    public List<Stock> buyStocksBySymbol(String symbol, int n) {
        return null;
        // TODO: 12/11/21  
    }

    public int sellStock() {
        return ServiceConfig.OK;
        // TODO: 12/10/21
    }

    public int getOpenPosition() {
        return ServiceConfig.OK;
        // TODO: 12/10/21
    }

    public int[] getProfit() {
        return null;
        // TODO: 12/10/21
    }
}
