package edu.bu.homeworkteam.bankatm.entities;

public class EntitiesConfig {
    public static String getTransactionType(TransactionType transaction) {
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

    public static String getCurrencyType(Currency currency) {
        switch (currency) {
            case USD:
                return "USD";
            case CNY:
                return "CNY";
        }
        return "EUR";
    }

    public static String getAccountType(AccountType accountType) {
        switch (accountType) {
            case CHECKING:
                return "CHECKING";
            case SAVINGS:
                return "SAVINGS";
        }
        return "SECURITIES";
    }
}
