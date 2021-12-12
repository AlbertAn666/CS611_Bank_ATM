package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.AccountType;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.TransactionType;

public class ServiceConfig {
    public static final int OK = 0;
    public static final int ACCOUNT_ERROR = -1;
    public static final int NOT_ENOUGH_MONEY = -2;   // Not enough money in the account to do the operation
    public static final int NOT_SAVINGS = -3;
    public static final float NO_SEC_ACCOUNT = -4;
    public static final int BELOW_LIMIT = -5;
    public static final int CUSTOMER_ERROR = -6;
    public static final int LOAN_ERROR = -7;
    public static final int EXCEED_LOANS = -8;
    public static final int STOCK_ERROR = -9;  // Stock Id Error
    public static final int NOT_ENOUGH_STOCKS = -10; // Not enough stocks for operation

    public static final float ACCOUNT_FEE = 20;  // Fee for open or delete an account
    public static final float STOCK_REQUIRE = 5000;  // Minimum requirement of saving account for stock
}
