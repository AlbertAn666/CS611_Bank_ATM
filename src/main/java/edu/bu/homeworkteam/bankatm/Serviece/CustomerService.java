package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.*;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.pagesUI.GuiManager;
import edu.bu.homeworkteam.bankatm.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

/**
 *  the customer bank service logic, including create accounts,
 *  delete accounts, deposit, withdraw, transfer money, view information, etc.
 */
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;
    //@Autowired
    //LoanRepository loanRepository;
    @Autowired
    CollateralRepository collateralRepository;

    public int createAccount(int customerId, AccountType accountType
       //     , float deposit
    ) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

/*        if(deposit < ServiceConfig.ACCOUNT_FEE) {
            System.out.println("Deposit not enough for open an account");
            return ServiceConfig.NOT_ENOUGH_MONEY;
        }*/


        if(accountType==AccountType.SECURITIES&&customer.getSecuritiesAccount()!=null){
            return ServiceConfig.SECURITIES_EXIST;
        }

        Account account = accountRepository.create();
        account.setCustomer(customer);
//        account.getBalances().put(Currency.USD, deposit - ServiceConfig.ACCOUNT_FEE);
        account.setAccountType(accountType);
        accountRepository.save(account);
        customer.getAccounts().add(account);
//        if(accountType.equals(AccountType.SECURITIES)) {
//            customer.setSecAccount(account);
//        }

        customerRepository.save(customer);
        return account.getId();
    }



    public int deleteAccount(int customerId, int accountId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

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

    public int depositMoney(int accountId, int customerId, Currency type, float value) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

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
            newTransaction.setInstant(Instant.now());
            transactionRepository.save(newTransaction);

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

    public int withdrawMoney(int accountId, int customerId, Currency type, float value) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if(account.getCustomer().getId() != customerId) {
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
            newTransaction.setInstant(Instant.now());
            transactionRepository.save(newTransaction);

            // set the new balance
            account.getBalances().put(type, currentMoney - value);
            accountRepository.save(account);
            System.out.println("Successfully withdraw money.");
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public int transferMoney(int fromAccountId, int toAccountId, int customerId, Currency currency, float value, String note) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

        Optional<Account> optionalFromAccount = accountRepository.findById(fromAccountId);
        if(optionalFromAccount.isPresent()) {
            Account fromAccount = optionalFromAccount.get();
            Optional<Account> optionalToAccount = accountRepository.findById(toAccountId);
            if(optionalToAccount.isEmpty()) return ServiceConfig.ACCOUNT_ERROR;
            Account toAccount = optionalToAccount.get();
            if(fromAccount.getCustomer().getId() != customer.getId()) {
                System.out.println("Account Id error.");
                return ServiceConfig.ACCOUNT_ERROR;
            }

            float currentMoney = 0;
            if(fromAccount.getBalances().containsKey(currency))
                currentMoney = fromAccount.getBalances().get(currency);
            if(currentMoney < value) {
                System.out.println("Balance is not enough.");
                return ServiceConfig.NOT_ENOUGH_MONEY;
            }

            // add a transaction record
            Transaction newTransaction = transactionRepository.create();
            newTransaction.setFromAccount(fromAccount);
            newTransaction.setToAccount(toAccount);
            newTransaction.setNote(note);
            newTransaction.setCurrency(currency);
            newTransaction.setAmount(value);
            newTransaction.setTransactionType(TransactionType.TRANSFER);
            newTransaction.setInstant(Instant.now());
            transactionRepository.save(newTransaction);


            fromAccount.getBalances().put(currency, currentMoney - value-value*0.001f);
            accountRepository.save(fromAccount);

            // set the new balance
            float currentMoney2 = 0;
            if(toAccount.getBalances().containsKey(currency)) {
                currentMoney2 = toAccount.getBalances().get(currency);
            }
            toAccount.getBalances().put(currency, currentMoney2 + value);
            accountRepository.save(toAccount);
            
            return ServiceConfig.OK;
        }
        return ServiceConfig.ACCOUNT_ERROR;
    }

    public ServiceResult addCollateral(int customerId, String name, float value, Currency currency, int accountId) {
        // set collateral attribute
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.get();

        Account account=null;
        for (Account accountOfCustomer :
                customer.getAccounts()) {
            if(accountOfCustomer.getId()==accountId){
                account=accountOfCustomer;
            }
        }
        if(account==null){
            return new ServiceResult(false,"You don't have this account");
        }

        Collateral collateral = collateralRepository.create();
        collateral.setCustomer(customer);
        collateral.setValue(value);
        collateral.setCurrency(currency);
        collateral.setName(name);
        collateralRepository.save(collateral);
        float originalBalance=account.getBalances().getOrDefault(currency,0f);
        account.getBalances().put(currency,originalBalance+value);
        accountRepository.save(account);

        updateLoansAfterCollateralChange(customer);

        return new ServiceResult(true,"");
    }


    public void updateLoansAfterCollateralChange(Customer customer){
        List<Collateral> collateralList= collateralRepository.getByCustomerId(customer.getId());
        Map<Currency, Float> loans= new HashMap<>();
        for (Collateral collateral:collateralList
        ) {
            Float loan=loans.get(collateral.getCurrency());
            if(loan==null){
                loans.put(collateral.getCurrency(),collateral.getValue());
            }else{
                float newLoan=loan+collateral.getValue();
                loans.put(collateral.getCurrency(),newLoan);

            }
        }

        customer.setLoans(loans);
        customerRepository.save(customer);
    }

    public ServiceResult removeCollateral(int customerId, int collateralId, int accountId) {
        // set collateral attribute
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Customer customer = optionalCustomer.get();

        List<Collateral> collateralList=collateralRepository.getByCustomerId(customerId);

        Collateral collateral=null;
        for (Collateral collateralOfCustomer :
                collateralList) {
            if(collateralOfCustomer.getId()==collateralId){
                collateral=collateralOfCustomer;
            }
        }

        if(collateral==null){
            return new ServiceResult(false, "You don't have this collateral");
        }


        Account account=null;
        for (Account accountOfCustomer :
                customer.getAccounts()) {
            if(accountOfCustomer.getId()==accountId){
                account=accountOfCustomer;
            }
        }
        if(account==null){
            return new ServiceResult(false,"You don't have this account");
        }

        Float balance=account.getBalances().getOrDefault(collateral.getCurrency(),0f);
        if(balance<collateral.getValue()){
            return new ServiceResult(false,"You don't have this currency or enough money");
        }


        float newBalance=balance-collateral.getValue();
        account.getBalances().put(collateral.getCurrency(),newBalance);
        accountRepository.save(account);
        collateralRepository.delete(collateral);

        updateLoansAfterCollateralChange(customer);

        return new ServiceResult(true,"");
    }









//
//    public int requestLoans(int customerId, int accountId, Currency currency, float amount) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        Optional<Account> optionalAccount = accountRepository.findById(accountId);
//        if(optionalAccount.isEmpty()) return ServiceConfig.ACCOUNT_ERROR;
//        Account account = optionalAccount.get();
//        if(account.getCustomer().getId() != customerId) return ServiceConfig.ACCOUNT_ERROR;
//
//        float currentLoan = customer.getLoans().get(currency);
//        float collateralValue = getCollateralValue(customerId, currency);
//        if(amount + currentLoan >= collateralValue) {
//            return ServiceConfig.COLLATERAL_LESS_THAN_LOAN;
//        }
//        // set account attribute
//        float currentBalance = account.getBalances().get(currency);
//        account.getBalances().put(currency, currentBalance + amount);
//        accountRepository.save(account);
//
//        // set customer attribute
//        Loan loan = loanRepository.create();
//        customer.getLoans().put(currency, currentLoan + amount);
//        customerRepository.save(customer);
//
//        loan.setCustomer(customer);
//        loan.setCurrency(currency);
//        loan.setAccount(account);
//        loan.setInstant(Instant.now());
//        loan.setAmount(amount);
//        loanRepository.save(loan);
//        return ServiceConfig.OK;
//    }

/*    public Vector<Vector<String>> showAllCollateral(int customerId) {
        Vector<Vector<String>> ret = new Vector<>();
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        Customer customer = optionalCustomer.get();
        List<Collateral> collaterals = customer.getCollaterals();
        for(Collateral collateral: collaterals) {
            Vector<String> temp = new Vector<>();
            temp.add(collateral.getName());
            temp.add(EntitiesConfig.getCurrencyType(collateral.getCurrency()));
            temp.add(String.valueOf(collateral.getValue()));
            ret.add(temp);
        }
        return ret;
    }*/
/*


    public int removeCollateral(int customerId, int collateralId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
        Customer customer = optionalCustomer.get();

        Optional<Collateral> optionalCollateral = collateralRepository.findById(collateralId);
        if(optionalCollateral.isEmpty()) return ServiceConfig.COLLATERAL_ERROR;
        Collateral collateral = optionalCollateral.get();
        if(collateral.getCustomer().getId() != customer.getId()) return ServiceConfig.COLLATERAL_ERROR;

        float totalLoan = customer.getLoans().get(collateral.getCurrency());
        if(getCollateralValue(customerId, collateral.getCurrency()) - collateral.getValue() >= totalLoan) {
            // able to remove
            int collateralIndex = getCollateralIndex(customerId, collateral);
            if(collateralIndex == ServiceConfig.COLLATERAL_ERROR) return ServiceConfig.COLLATERAL_ERROR;
            customer.getCollaterals().remove(getCollateralIndex(customerId, collateral));
            customerRepository.save(customer);
            collateralRepository.deleteById(collateralIndex);
            return ServiceConfig.OK;
        } else {
            // unable to remove
            return ServiceConfig.COLLATERAL_LESS_THAN_LOAN;
        }
    }*/
//
//    private float getCollateralValue(int customerId, Currency currency) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        float ret = 0;
//        for(Collateral collateral: customer.getCollaterals()) {
//            if(collateral.getCurrency() == currency) {
//                ret += collateral.getValue();
//            }
//        }
//        return ret;
//    }
//
//    private int getCollateralIndex(int customerId, Collateral collateral) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        int res = 0;
//        for(Collateral allCollateral: customer.getCollaterals()) {
//            if(allCollateral.getId() == collateral.getId())
//                return res;
//            res++;
//        }
//        return ServiceConfig.COLLATERAL_ERROR;
//    }

//    public int payBackLoan(int customerId, int loanId, int accountId, float amount) {
//        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
//        if(optionalCustomer.isEmpty()) return ServiceConfig.CUSTOMER_ERROR;
//        Customer customer = optionalCustomer.get();
//
//        Optional<Loan> optionalLoan = loanRepository.findById(loanId);
//        if(optionalLoan.isEmpty()) return ServiceConfig.LOAN_ERROR;
//        Loan loan = optionalLoan.get();
//        Currency currency = loan.getCurrency();
//        float loanMoney = loan.getAmount();
//        if(amount > loanMoney) return ServiceConfig.EXCEED_LOANS;
//
//        Optional<Account> optionalAccount = accountRepository.findById(accountId);
//        if(optionalAccount.isEmpty()) return ServiceConfig.ACCOUNT_ERROR;
//        Account account = optionalAccount.get();
//        if(account.getCustomer().getId() != customerId)
//            return ServiceConfig.ACCOUNT_ERROR;
//        float currentBalance = 0;
//        if(account.getBalances().containsKey(currency))
//            currentBalance = account.getBalances().get(currency);
//        if(amount > currentBalance) return ServiceConfig.NOT_ENOUGH_MONEY;
//
//        account.getBalances().put(currency, currentBalance - amount);
//        accountRepository.save(account);
//        loan.setAmount(loan.getAmount() - amount);
//        if(loan.getAmount() == 0) {
//            loanRepository.deleteById(loanId);
//            return ServiceConfig.OK;
//        }
//        loanRepository.save(loan);
//        return ServiceConfig.OK;
//    }

    public List<Integer> allAccounts(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        Customer customer = optionalCustomer.get();

        List<Account> accounts = customer.getAccounts();
        List<Integer> ret = new ArrayList<>();
        for(Account account: accounts) {
            ret.add(account.getId());
        }
        return ret;
    }

    public Vector<Vector<String>> viewBalances(int customerId, int accountId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        Customer customer = optionalCustomer.get();

        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if(account.getCustomer().getId() != customer.getId()) return null;
            return account.getAccountBalances();
        }
        System.out.println("AccountId error");
        return null;
    }

    public Vector<Vector<String>> viewTransactions(int customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) return null;
        Customer customer = optionalCustomer.get();

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
