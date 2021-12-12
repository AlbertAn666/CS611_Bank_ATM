package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import edu.bu.homeworkteam.bankatm.repositories.LoanRepository;
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
    @Autowired
    LoanRepository loanRepository;

    public int createAccount(Customer customer, AccountType accountType, float deposit) {
        if(deposit < ServiceConfig.ACCOUNT_FEE) {
            System.out.println("Deposit not enough for open an account");
            return ServiceConfig.NOT_ENOUGH_MONEY;
        }
        Account account = accountRepository.create();
        account.setCustomer(customer);
        account.getBalances().put(Currency.USD, deposit - ServiceConfig.ACCOUNT_FEE);
        account.setAccountType(accountType);
        accountRepository.save(account);
        customer.getAccounts().add(account);
        if(accountType.equals(AccountType.SECURITIES)) {
            customer.setSecAccount(account);
        }
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
            if(account.getBalances().get(Currency.USD) <= ServiceConfig.ACCOUNT_FEE) {
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
            float currentMoney = 0;
            if(account.getBalances().containsKey(type))
                currentMoney = account.getBalances().get(type);
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

            float currentMoney = 0;
            if(account.getBalances().containsKey(type))
                currentMoney = account.getBalances().get(type);
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

            float currentMoney = 0;
            if(account.getBalances().containsKey(type))
                currentMoney = account.getBalances().get(type);
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
            float currentMoney2 = 0;
            if(account.getBalances().containsKey(type))
                currentMoney2 = account2.getBalances().get(type);
            account.getBalances().put(type, currentMoney - value);
            accountRepository.save(account);
            account2.getBalances().put(type, currentMoney2 + value);
            accountRepository.save(account2);
            
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public int requestLoans(int accountId, Currency type, float amount) {
        return ServiceConfig.OK;
        // TODO: 12/10/21
    }

    public int payBackLoan(int loanId, int accountId, float amount) {
        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
        if(optionalLoan.isEmpty()) return ServiceConfig.LOAN_ERROR;
        Loan loan = optionalLoan.get();
        Currency currency = loan.getCurrency();
        float loanMoney = loan.getAmount();
        if(amount > loanMoney) return ServiceConfig.EXCEED_LOANS;

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty()) return ServiceConfig.ACCOUNT_ERROR;
        Account account = optionalAccount.get();
        float currentBalance = 0;
        if(account.getBalances().containsKey(currency))
            currentBalance = account.getBalances().get(currency);
        if(amount > currentBalance) return ServiceConfig.NOT_ENOUGH_MONEY;

        account.getBalances().put(currency, currentBalance - amount);
        accountRepository.save(account);
        loan.setAmount(loan.getAmount() - amount);
        if(loan.getAmount() == 0) {
            loanRepository.deleteById(loanId);
            return ServiceConfig.OK;
        }
        loanRepository.save(loan);
        return ServiceConfig.OK;
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
            return account.getAccountBalances();
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
           Vector<String> temp = transaction.getTransaction();
           ret.add(temp);
        }
        return ret;
    }

}
