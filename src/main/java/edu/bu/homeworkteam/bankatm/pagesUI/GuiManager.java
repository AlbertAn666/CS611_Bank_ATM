package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.*;
import edu.bu.homeworkteam.bankatm.repositories.AccountRepository;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import edu.bu.homeworkteam.bankatm.repositories.StockRepository;
import edu.bu.homeworkteam.bankatm.repositories.TransactionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class GuiManager {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerStockService customerStockService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private ManagerStockService managerStockService;
    @Autowired
    private ManagerService managerService;


    private int loggedInCustomerId;

    private static GuiManager guiManager;
    public static GuiManager getInstance(){
        if(guiManager ==null) guiManager =new GuiManager();
        return guiManager;
    }

    private GuiManager(){

    }

    public void startGui(){
        new LoginFrame();

    }



}
