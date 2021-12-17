/*
 * Created by JFormDesigner on Thu Dec 16 22:36:58 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import edu.bu.homeworkteam.bankatm.Serviece.ServiceResult;
import edu.bu.homeworkteam.bankatm.entities.Currency;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * frame for customer to borrow loan
 * @author gung
 */
public class BorrowLoanFrame extends JFrame {

    private DefaultComboBoxModel<Currency> currencyDefaultComboBoxModel;

    private ViewCollateralFrame viewCollateralFrame;
    public void setViewCollateralFrame(ViewCollateralFrame viewCollateralFrame){
        this.viewCollateralFrame =viewCollateralFrame;
    }

    public BorrowLoanFrame() {
        initComponents();


        currencyDefaultComboBoxModel=new DefaultComboBoxModel<>(Currency.values());
        currencyComboBox.setModel(currencyDefaultComboBoxModel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ServiceResult serviceResult=null;
                try {

                    String name=nameField.getText();
                    float value=Float.parseFloat(valueField.getText());
                    int accountId=Integer.parseInt(accountIdField.getText());
                    Currency currency=(Currency)currencyDefaultComboBoxModel.getSelectedItem();
                    int customerId = GuiManager.getInstance().getLoggedInCustomerId();

                    serviceResult=GuiManager.getInstance().getCustomerService().addCollateral(customerId,name,value,currency, accountId);

                }catch (Exception e){

                }

                if(serviceResult== null){
                    new PromptFrame("Borrowing failed. Please check and try again.");
                }else if(serviceResult.isSuccessful()){
                    new PromptFrame("Borrowing successful.");
                    viewCollateralFrame.refresh();
                    dispose();
                }else {
                    new PromptFrame("Borrowing failed. "+serviceResult.getMessage());
                }
            }
        });

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        nameField = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        valueField = new JTextField();
        label3 = new JLabel();
        currencyComboBox = new JComboBox();
        label4 = new JLabel();
        accountIdField = new JTextField();
        button = new JButton();

        //======== this ========
        setTitle("Borrow Loan");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(nameField);
        nameField.setBounds(125, 85, 385, 30);

        //---- label1 ----
        label1.setText("Collateral name");
        contentPane.add(label1);
        label1.setBounds(10, 85, 105, 30);

        //---- label2 ----
        label2.setText("value");
        contentPane.add(label2);
        label2.setBounds(45, 180, 55, 30);
        contentPane.add(valueField);
        valueField.setBounds(125, 180, 155, 30);

        //---- label3 ----
        label3.setText("Currency");
        contentPane.add(label3);
        label3.setBounds(35, 130, 70, 30);
        contentPane.add(currencyComboBox);
        currencyComboBox.setBounds(125, 135, 155, currencyComboBox.getPreferredSize().height);

        //---- label4 ----
        label4.setText("Account ID");
        contentPane.add(label4);
        label4.setBounds(30, 230, 85, 30);
        contentPane.add(accountIdField);
        accountIdField.setBounds(125, 230, 155, 30);

        //---- button ----
        button.setText("Confrim");
        contentPane.add(button);
        button.setBounds(370, 305, 155, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField nameField;
    private JLabel label1;
    private JLabel label2;
    private JTextField valueField;
    private JLabel label3;
    private JComboBox currencyComboBox;
    private JLabel label4;
    private JTextField accountIdField;
    private JButton button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
