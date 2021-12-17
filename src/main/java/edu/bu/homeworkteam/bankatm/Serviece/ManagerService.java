package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.pagesUI.GuiManager;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
//import edu.bu.homeworkteam.bankatm.repositories.LoanRepository;
import edu.bu.homeworkteam.bankatm.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.EndElement;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * the manager bank service logic, including pay interests and charge interest,
 * show information of customers, show transactions information, etc.
 */
@Component
public class ManagerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;


    public int chargeInterest() {
        Iterable<Customer> customers = GuiManager.getInstance().getCustomerRepository().findAll();
        for(Customer customer: customers) {
            Map<Currency, Float> loan = customer.getLoans();
            for(Currency currency: loan.keySet()) {
                float loanOfCur = loan.get(currency);
                loanOfCur += loanOfCur * 0.05;
                loan.put(currency, loanOfCur);
            }
            customer.setLoans(loan);
            GuiManager.getInstance().getCustomerRepository().save(customer);
        }
        return ServiceConfig.OK;
    }

    public int payInterest() {
        Iterable<Account> accounts = GuiManager.getInstance().getAccountRepository().findAll();
        for(Account account: accounts) {
            if(account.getAccountType() == AccountType.SAVINGS) {
                Map<Currency, Float> currentBalances = account.getBalances();
                for (Currency currency : currentBalances.keySet()) {
                    float balance = 0;
                    if(currentBalances.containsKey(currency))
                        balance = currentBalances.get(currency);
                    if(balance >= 1000)
                        balance += balance * 0.03;
                    currentBalances.put(currency, balance);
                }
                account.setBalances(currentBalances);
            }
            GuiManager.getInstance().getAccountRepository().save(account);
        }
        return ServiceConfig.OK;
    }

    public Customer getCustomer(int customerId) {
        Optional<Customer> optionalCustomer = GuiManager.getInstance().getCustomerRepository().findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        return optionalCustomer.get();
    }

    public Vector<Vector<String>> checkUpTransactions(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        List<Transaction> transactions = GuiManager.getInstance().getTransactionRepository().getTransactionsByCustomerId(customerId);
        for(Transaction transaction: transactions) {
            Vector<String> temp = transaction.getTransaction();
            ret.add(temp);
        }
        return ret;
    }

    public Vector<Vector<String>> checkUpCustomerLoans(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        Customer customer = getCustomer(customerId);
        if(customer == null) return null;
        Map<Currency, Float> loans = customer.getLoans();
        for (Currency currency : loans.keySet()) {
            Vector<String> temp = new Vector<>();
            temp.add(currency.toString());
            temp.add(String.valueOf(loans.get(currency)));

            ret.add(temp);
        }
        return ret;
    }

//    Vector<Vector<String>> checkUpLoans(int customerId) {
//        Vector<Vector<String>> ret = new Vector<>();
//        Iterable<Loan> loanIterable = loanRepository.findAll();
//        for(Loan loan: loanIterable) {
//            if(loan.getCustomer().getId() == customerId) {
//                Vector<String> temp = new Vector<>();
//                temp.add(String.valueOf(loan.getId()));
//                temp.add(EntitiesConfig.getCurrencyType(loan.getCurrency()));
//                temp.add(String.valueOf(loan.getAmount()));
//                temp.add(loan.getDataTimeString());
//                ret.add(temp);
//            }
//        }
//        return ret;
//    }

    public Vector<Vector<String>> checkUpCustomerAccounts(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        Customer customer = getCustomer(customerId);
        List<Account> accounts = customer.getAccounts();

        // show as accountId, accountType
        for(Account account: accounts) {
            System.out.println(account.getId());
            Vector<String> accountInfo = new Vector<>();
            accountInfo.add(String.valueOf(account.getId()));
            accountInfo.add(account.getAccountType().toString());

            ret.add(accountInfo);
        }
        return ret;
    }

    public Vector<Vector<String>> checkUpBalances(int accountId) {
        Optional<Account> optionalAccount = GuiManager.getInstance().getAccountRepository().findById(accountId);
        if(optionalAccount.isEmpty()) return null;
        Account account = optionalAccount.get();
        return account.getAccountBalances();
    }

    public Vector<Vector<String>> checkUpDailyTransactions(String date) {
        Vector<Vector<String>> ret = new Vector<>();
        Iterable<Transaction> transactions = GuiManager.getInstance().getTransactionRepository().findAll();
        for(Transaction transaction: transactions) {
            // Tag:
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("America/New_York"));
            String transDate = dateTimeFormatter.format(transaction.getInstant());
            if(date.equals(transDate)) {
                ret.add(transaction.getTransaction());
            }
        }
        return ret;
    }

    public Vector<Vector<String>> checkUpTodayTransactions() {
        Vector<Vector<String>> ret = new Vector<>();
        SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFmt.format(new Date());
        return checkUpDailyTransactions(todayDate);
    }
}
