package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import edu.bu.homeworkteam.bankatm.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public int createAccount(Customer customer, AccountType accountType) {
        Account account = accountRepository.create();
        account.setCustomer(customer);
        account.setAccountType(accountType);
        accountRepository.save(account);
        customer.getAccounts().add(account);
        customerRepository.save(customer);
        return account.getId();
    }

    public int deleteAccount(Customer customer, int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if(account.getCustomer().getId() != customer.getId()) {
                return ServiceConfig.ACCOUNT_ERROR;
            }
            if(account.getBalances().get(Currency.USD) <= 10) {
                return ServiceConfig.NOT_ENOUGH_MONEY;
            }
            accountRepository.deleteById(accountId);
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public int depositMoney(int accountId, Customer customer, Currency type, float value) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if(account.getCustomer().getId() != customer.getId()) {
                System.out.println("Account Id input error.");
                return ServiceConfig.ACCOUNT_ERROR;
            }

            // add a transaction record
            Transaction newTransaction = transactionRepository.create();
            newTransaction.setFromAccount(null);
            newTransaction.setAmount(value);
            newTransaction.setCurrency(type);
            newTransaction.setToAccount(account);
            newTransaction.setTransactionType(TransactionType.DEPOSIT);

            // set the new balance
            float currentMoney = account.getBalances().get(type);
            account.getBalances().put(type, value + currentMoney);
            accountRepository.save(account);
            System.out.println("Successfully deposit Money.");
            return ServiceConfig.OK;
        }
        System.out.println("Account Id input error.");
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public int withdrawMoney(int accountId, Customer customer, Currency type, float value) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if(account.getCustomer().getId() != customer.getId()) {
                System.out.println("Account Id input error.");
                return ServiceConfig.ACCOUNT_ERROR;
            }

            float currentMoney = account.getBalances().get(type);
            if(currentMoney < value) {
                System.out.println("Balance is not enough.");
                return ServiceConfig.NOT_ENOUGH_MONEY;
            }

            // add a transaction record
            Transaction newTransaction = transactionRepository.create();
            newTransaction.setFromAccount(account);
            newTransaction.setToAccount(null);
            newTransaction.setAmount(value);
            newTransaction.setCurrency(type);
            newTransaction.setTransactionType(TransactionType.WITHDRAW);

            // set the new balance
            account.getBalances().put(type, currentMoney - value);
            accountRepository.save(account);
            System.out.println("Successfully withdraw money.");
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public int transferMoney(int fromAccount, int toAccount, Customer customer, Currency type, float value, String note) {
        Optional<Account> optionalAccount1 = accountRepository.findById(fromAccount);
        if(optionalAccount1.isPresent()) {
            Account account = optionalAccount1.get();
            Optional<Account> optionalAccount2 = accountRepository.findById(toAccount);
            if(optionalAccount2.isEmpty()) return ServiceConfig.ACCOUNT_ERROR;
            Account account2 = optionalAccount2.get();
            if(account.getCustomer().getId() != customer.getId()) {
                System.out.println("Account Id error.");
                return ServiceConfig.ACCOUNT_ERROR;
            }

            float currentMoney = account.getBalances().get(type);
            if(currentMoney < value) {
                System.out.println("Balance is not enough.");
                return ServiceConfig.NOT_ENOUGH_MONEY;
            }

            // add a transaction record
            Transaction newTransaction = transactionRepository.create();
            newTransaction.setFromAccount(account);
            newTransaction.setToAccount(account2);
            newTransaction.setNote(note);
            newTransaction.setCurrency(type);
            newTransaction.setAmount(value);
            newTransaction.setTransactionType(TransactionType.TRANSFER);

            // set the new balance
            float currentMoney2 = account2.getBalances().get(type);
            account.getBalances().put(type, currentMoney - value);
            accountRepository.save(account);
            account2.getBalances().put(type, currentMoney2 + value);
            accountRepository.save(account2);
            
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }


    public int requestLoans(int AccountId, float amount) {
        return ServiceConfig.OK;
        // TODO: 12/10/21
    }

    public List<Integer> allAccounts(Customer customer) {
        List<Account> accounts = customer.getAccounts();
        List<Integer> ret = new ArrayList<>();
        for(Account account: accounts) {
            ret.add(account.getId());
        }
        return ret;
    }

    public Vector<Vector<String>> viewBalances(int accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            Map<Currency, Float> balances = account.getBalances();
            Vector<Vector<String>> ret = new Vector<>();
            for(Currency currency: balances.keySet()) {
                Vector<String> temp = new Vector<>();
                temp.add(getCurrencyType(currency));
                temp.add(balances.get(currency).toString());

                ret.add(temp);
            }
            return ret;
        }
        System.out.println("AccountId error");
        return null;
    }

    public Vector<Vector<String>> viewTransactions(Customer customer) {
        Vector<Vector<String>> ret = new Vector<>();
        List<Transaction> transactionList = transactionRepository.getTransactionsByCustomerId(customer.getId());
        // format is like:
        // id, time, type, from, to, currency, amount
        for(Transaction transaction: transactionList) {
            Vector<String> temp = new Vector<>();
            temp.add(String.valueOf(transaction.getId()));
            temp.add(transaction.getDateTimeString());
            temp.add(getTransactionType(transaction.getTransactionType()));
            temp.add(String.valueOf(transaction.getFromAccount()));
            temp.add(String.valueOf(transaction.getToAccount()));
            temp.add(getCurrencyType(transaction.getCurrency()));
            temp.add(String.valueOf(transaction.getAmount()));

            ret.add(temp);
        }
        return ret;
    }

    private String getTransactionType(TransactionType transaction) {
        switch (transaction) {
            case TRANSFER:
                return "Transfer";
            case DEPOSIT:
                return "Deposit";
            case WITHDRAW:
                return "Withdraw";
            case LOAN:
                return "Loan";
        }
        return "Other";
    }

    private String getCurrencyType(Currency currency) {
        switch (currency) {
            case USD:
                return "USD";
            case CNY:
                return "CNY";
        }
        return "EUR";
    }
}
