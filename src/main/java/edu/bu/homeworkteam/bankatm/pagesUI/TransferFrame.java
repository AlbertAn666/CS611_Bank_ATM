/*
 * Created by JFormDesigner on Thu Dec 16 20:09:59 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.repositories.CustomerRepository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author gung
 */
public class TransferFrame extends JFrame {

    DefaultComboBoxModel<Currency> currencyDefaultComboBoxModel;
    private HomeFrame homeFrame;
    public void setHomeFrame(HomeFrame homeFrame){
        this.homeFrame=homeFrame;
    }

    public TransferFrame() {
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

        int transferResult=-1;
        try {
            int fromAccountId = Integer.parseInt(fromAccountField.getText());
            int toAccountId = Integer.parseInt(toAccountField.getText());

            Currency currency = (Currency) currencyDefaultComboBoxModel.getSelectedItem();
            float amount = Float.parseFloat(amountField.getText());
            int customerId = GuiManager.getInstance().getLoggedInCustomerId();


            transferResult = GuiManager.getInstance().getCustomerService().transferMoney(fromAccountId, toAccountId, customerId, currency, amount, "Transfer");
        }catch (Exception e){

        }

        if(transferResult== ServiceConfig.OK){
            System.out.println("Transfer successful.");
            new PromptFrame("Transfer successful!");
            homeFrame.refresh();
            dispose();
        }else{
            new PromptFrame("Transfer failed. Please check and try again.");

        }

    }






    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        fromAccountField = new JTextField();
        toAccountField = new JTextField();
        amountField = new JTextField();
        currencyComboBox = new JComboBox();
        label1 = new JLabel();
        confirmButton = new JButton();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();

        //======== this ========
        setVisible(true);
        setTitle("Transfer");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(fromAccountField);
        fromAccountField.setBounds(180, 90, 325, 26);
        contentPane.add(toAccountField);
        toAccountField.setBounds(180, 145, 325, 26);
        contentPane.add(amountField);
        amountField.setBounds(180, 265, 325, 26);
        contentPane.add(currencyComboBox);
        currencyComboBox.setBounds(180, 205, 325, currencyComboBox.getPreferredSize().height);

        //---- label1 ----
        label1.setText("From account");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(75, 95), label1.getPreferredSize()));

        //---- confirmButton ----
        confirmButton.setText("Confirm");
        contentPane.add(confirmButton);
        confirmButton.setBounds(390, 340, 115, confirmButton.getPreferredSize().height);

        //---- label2 ----
        label2.setText("To account");
        contentPane.add(label2);
        label2.setBounds(75, 150, 86, 16);

        //---- label3 ----
        label3.setText("Currency");
        contentPane.add(label3);
        label3.setBounds(75, 205, 86, 16);

        //---- label4 ----
        label4.setText("Amount");
        contentPane.add(label4);
        label4.setBounds(75, 265, 86, 16);

        contentPane.setPreferredSize(new Dimension(610, 440));
        setSize(610, 440);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;
    private JComboBox currencyComboBox;
    private JLabel label1;
    private JButton confirmButton;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
