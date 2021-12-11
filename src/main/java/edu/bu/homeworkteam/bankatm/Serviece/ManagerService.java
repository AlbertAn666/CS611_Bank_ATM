package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import edu.bu.homeworkteam.bankatm.repositories.LoanRepository;
import edu.bu.homeworkteam.bankatm.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.EndElement;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class ManagerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    LoanRepository loanRepository;


    public int payInterest() {
        Iterable<Account> accounts = accountRepository.findAll();
        for(Account account: accounts) {
            if(account.getAccountType() == AccountType.SAVINGS) {
                Map<Currency, Float> currentBalances = account.getBalances();
                for (Currency currency : currentBalances.keySet()) {
                    float balance = currentBalances.get(currency);
                    if(balance >= 10000)
                        balance += balance * 0.03;
                    currentBalances.put(currency, balance);
                }
                account.setBalances(currentBalances);
            }
            int accountId = account.getId();
            accountRepository.deleteById(accountId);
            accountRepository.save(account);
        }
        return ServiceConfig.OK;
    }

    public int chargeInterest() {
        Iterable<Loan> loans = loanRepository.findAll();
        for(Loan loan: loans) {
            float currentMoney = loan.getAmount();
            currentMoney += currentMoney * 0.05;
            loan.setAmount(currentMoney);
        }
        return ServiceConfig.OK;
    }

    public Customer getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        return optionalCustomer.get();
    }

    Vector<Vector<String>> checkUpTransactions(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        List<Transaction> transactions = transactionRepository.getTransactionsByCustomerId(customerId);
        for(Transaction transaction: transactions) {
            Vector<String> temp = transaction.getTransaction();
            ret.add(temp);
        }
        return ret;
    }

    Vector<Vector<String>> checkUpLoans(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        Iterable<Loan> loanIterable = loanRepository.findAll();
        for(Loan loan: loanIterable) {
            if(loan.getCustomer().getId() == customerId) {
                Vector<String> temp = new Vector<>();
                temp.add(String.valueOf(loan.getId()));
                temp.add(EntitiesConfig.getCurrencyType(loan.getCurrency()));
                temp.add(String.valueOf(loan.getAmount()));
                temp.add(loan.getDataTimeString());
                ret.add(temp);
            }
        }
        return ret;
    }

    Vector<Vector<String>> chekUpCustomerAccounts(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        Customer customer = getCustomer(customerId);
        List<Account> accounts = customer.getAccounts();

        for(Account account: accounts) {
            Vector<String> accountInfo = new Vector<>();
            accountInfo.add(String.valueOf(account.getId()));
            accountInfo.add(EntitiesConfig.getAccountType(account.getAccountType()));

            ret.add(accountInfo);
        }
        return ret;
    }

    Vector<Vector<String>> checkUpBalances(int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty()) return null;
        Account account = optionalAccount.get();
        return account.getAccountBalances();
    }

    public Vector<Vector<String>> checkUpTodayTransactions() {
        Vector<Vector<String>> ret = new Vector<>();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFmt.format(new Date());
        Iterable<Transaction> transactions = transactionRepository.findAll();
        for(Transaction transaction: transactions) {
            Vector<String> transactionInfo = new Vector<>();
            // Tag:
            String transDate = dateFmt.format(transaction.getInstant());
            if(todayDate.equals(transDate)) {
                ret.add(transaction.getTransaction());
            }
        }
        return ret;
    }
}
