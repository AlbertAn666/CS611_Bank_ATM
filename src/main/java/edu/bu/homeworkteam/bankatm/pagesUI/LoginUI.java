package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.CustomerService;
import edu.bu.homeworkteam.bankatm.Serviece.LoginService;
import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import edu.bu.homeworkteam.bankatm.entities.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {

    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel mainPanel;
    private JPanel panel1;
    private JPanel panel2;


    public LoginUI() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = textField1.getText();
                int userId = Integer.parseInt(user);
                String password = new String(passwordField1.getPassword());
                LoginService loginService = new LoginService();
                int result = loginService.loginCustomer(userId, password);
                if(result == ServiceConfig.OK) {
                    // change page
                } else {
                    JOptionPane.showMessageDialog(null, "User Id / password error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("22222");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginUI");
        frame.setContentPane(new LoginUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
