/*
 * Created by JFormDesigner on Fri Dec 17 13:49:46 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author unknown
 */
public class SignUpFrame extends JFrame {
    public SignUpFrame() {
        initComponents();
        logoLabel.setIcon(new ImageIcon(System.getProperty("user.dir")+"/src/main/java/edu/bu/homeworkteam/bankatm/pagesUI/logo.png"));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String name=nameField.getText();
                String password=String.valueOf(passwordField.getPassword());
                String repeatedPassword=String.valueOf(repeatPasswordField.getPassword());

                System.out.println(password);
                if(name.equals("")||password.equals("")||repeatedPassword.equals("")){
                    new PromptFrame("Sorry, you have to input all the fields.");
                    return;
                }

                if(!password.equals(repeatedPassword)){
                    new PromptFrame("Sorry, the repeated password doesn't match the password.");
                    return;
                }

                int customerId=GuiManager.getInstance().getLoginService().register(name,password);
                new PromptFrame(("Congrats! please remember your customer ID: "+customerId));
                dispose();

            }
        });



    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        logoLabel = new JLabel();
        nameField = new JTextField();
        button = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        passwordField = new JPasswordField();
        repeatPasswordField = new JPasswordField();

        //======== this ========
        setTitle("Sign up");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(logoLabel);
        logoLabel.setBounds(335, 20, 230, 220);
        contentPane.add(nameField);
        nameField.setBounds(25, 55, 240, nameField.getPreferredSize().height);

        //---- button ----
        button.setText("confirm");
        contentPane.add(button);
        button.setBounds(405, 250, 135, button.getPreferredSize().height);

        //---- label2 ----
        label2.setText("Your name");
        contentPane.add(label2);
        label2.setBounds(25, 30, 90, label2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("Password");
        contentPane.add(label3);
        label3.setBounds(25, 95, 90, 16);

        //---- label4 ----
        label4.setText("Repeat your password");
        contentPane.add(label4);
        label4.setBounds(25, 155, 170, 16);
        contentPane.add(passwordField);
        passwordField.setBounds(25, 115, 240, passwordField.getPreferredSize().height);
        contentPane.add(repeatPasswordField);
        repeatPasswordField.setBounds(25, 180, 240, 26);

        contentPane.setPreferredSize(new Dimension(600, 320));
        setSize(600, 320);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel logoLabel;
    private JTextField nameField;
    private JButton button;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
