package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.CustomerService;
import edu.bu.homeworkteam.bankatm.Serviece.LoginService;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class GuiManager {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;

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
