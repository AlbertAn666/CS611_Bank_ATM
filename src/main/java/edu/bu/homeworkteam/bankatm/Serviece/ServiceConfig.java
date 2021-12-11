package edu.bu.homeworkteam.bankatm.Serviece;

import edu.bu.homeworkteam.bankatm.entities.AccountType;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.TransactionType;

public class ServiceConfig {
    public static final int OK = 0;
    public static final int ACCOUNT_ERROR = -1;
    public static final int NOT_ENOUGH_MONEY = -2;
    public static final int NOT_SAVINGS = -3;
    public static final int NO_SEC_ACCOUNT = -4;
    public static final int BELOW_LIMIT = -5;
    public static final int CUSTOMER_ERROR = -6;
    public static final int LOAN_ERROR = -7;
    public static final int EXCEED_LOANS = -8;

}
