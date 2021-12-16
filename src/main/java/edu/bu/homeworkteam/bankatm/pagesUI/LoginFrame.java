/*
 * Created by JFormDesigner on Thu Dec 16 11:33:53 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.LoginService;
import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author unknown
 */
public class LoginFrame extends JFrame {
    public LoginFrame() {
        initComponents();
    }

    @Autowired
    LoginService loginService;
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        loginButton = new JButton();
        passwordField = new JPasswordField();
        customerIdField = new JFormattedTextField();
        signUpLabel = new JLabel();
        customerIdLabel = new JLabel();
        passwordLabel = new JLabel();
        logo = new JLabel();
        loginResultLabel = new JLabel();

        //======== this ========
        setTitle("Login");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(null);
        setIconImage(null);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- loginButton ----
        loginButton.setText("Login");
        loginButton.setBackground(UIManager.getColor("Button.background"));
        contentPane.add(loginButton);
        loginButton.setBounds(190, 465, 145, loginButton.getPreferredSize().height);
        contentPane.add(passwordField);
        passwordField.setBounds(35, 420, 300, passwordField.getPreferredSize().height);
        contentPane.add(customerIdField);
        customerIdField.setBounds(35, 365, 300, customerIdField.getPreferredSize().height);

        //---- signUpLabel ----
        signUpLabel.setText("Sign Up");
        signUpLabel.setForeground(new Color(0, 0, 102));
        contentPane.add(signUpLabel);
        signUpLabel.setBounds(new Rectangle(new Point(40, 470), signUpLabel.getPreferredSize()));

        //---- customerIdLabel ----
        customerIdLabel.setText("Customer ID");
        contentPane.add(customerIdLabel);
        customerIdLabel.setBounds(40, 345, 95, customerIdLabel.getPreferredSize().height);

        //---- passwordLabel ----
        passwordLabel.setText("Password");
        contentPane.add(passwordLabel);
        passwordLabel.setBounds(40, 400, 95, 16);

        //---- logo ----
        logo.setIcon(null);
        contentPane.add(logo);
        logo.setBounds(65, 60, 225, 185);

        //---- loginResult ----
        loginResultLabel.setText("(login error)");
        contentPane.add(loginResultLabel);
        loginResultLabel.setBounds(195, 495, 140, loginResultLabel.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(370, 545));
        setSize(370, 545);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
        System.out.println("Hello");
        logo.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/main/java/edu/bu/homeworkteam/bankatm/pagesUI/logo.png"));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int customerId=Integer.parseInt(customerIdField.getText());
                String password=String.valueOf(passwordField.getPassword());


                int loginResult=loginService.loginCustomer(customerId,password);
                if(loginResult==ServiceConfig.OK){
                    System.out.println("hasdfsdf");
                }else if(loginResult==ServiceConfig.PASSWORD_ERROR){
                    new JDialog().setContentPane(new JLabel("Failed to login. Please check and try again."));
                }
            }
        });


    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JButton loginButton;
    private JPasswordField passwordField;
    private JFormattedTextField customerIdField;
    private JLabel signUpLabel;
    private JLabel customerIdLabel;
    private JLabel passwordLabel;
    private JLabel logo;
    private JLabel loginResultLabel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new LoginFrame();
    }
}
