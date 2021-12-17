/*
 * Created by JFormDesigner on Thu Dec 16 21:31:55 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import edu.bu.homeworkteam.bankatm.entities.Currency;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * use for customer to withdraw money
 * @author gung
 */
public class WithdrawFrame extends JFrame {


    private DefaultComboBoxModel<Currency> currencyDefaultComboBoxModel;

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        accountIdField = new JTextField();
        label1 = new JLabel();
        currencyComboBox = new JComboBox();
        label2 = new JLabel();
        label3 = new JLabel();
        amountField = new JTextField();
        confirmButton = new JButton();

        //======== this ========
        setTitle("Withdraw");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(accountIdField);
        accountIdField.setBounds(120, 120, 325, accountIdField.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Account");
        contentPane.add(label1);
        label1.setBounds(45, 125, 55, 20);
        contentPane.add(currencyComboBox);
        currencyComboBox.setBounds(120, 170, 325, currencyComboBox.getPreferredSize().height);

        //---- label2 ----
        label2.setText("Currency");
        contentPane.add(label2);
        label2.setBounds(45, 170, 75, 20);

        //---- label3 ----
        label3.setText("Amount");
        contentPane.add(label3);
        label3.setBounds(45, 220, 55, 20);
        contentPane.add(amountField);
        amountField.setBounds(120, 220, 325, 26);

        //---- confirmButton ----
        confirmButton.setText("Confirm");
        contentPane.add(confirmButton);
        confirmButton.setBounds(295, 315, 150, confirmButton.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(510, 425));
        setSize(510, 425);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    private HomeFrame homeFrame;
    public void setHomeFrame(HomeFrame homeFrame){
        this.homeFrame=homeFrame;
    }

    public WithdrawFrame() {
        initComponents();

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                submit();
            }
        });

        currencyDefaultComboBoxModel=new DefaultComboBoxModel<>(Currency.values());
        currencyComboBox.setModel(currencyDefaultComboBoxModel);

    }

    private void submit(){
        int withdrawResult=-1;
        try {
            int accountId = Integer.parseInt(accountIdField.getText());
            Currency currency = (Currency) currencyDefaultComboBoxModel.getSelectedItem();
            float amount = Float.parseFloat(amountField.getText());
            int customerId = GuiManager.getInstance().getLoggedInCustomerId();
            withdrawResult = GuiManager.getInstance().getCustomerService().withdrawMoney(accountId,customerId,currency,amount);
        }catch (Exception e){

        }

        if(withdrawResult== ServiceConfig.OK){
            new PromptFrame("Withdraw successful!");
            homeFrame.refresh();
            dispose();
        }else{
            new PromptFrame("Withdraw failed. Please check and try again.");
        }
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField accountIdField;
    private JLabel label1;
    private JComboBox currencyComboBox;
    private JLabel label2;
    private JLabel label3;
    private JTextField amountField;
    private JButton confirmButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
