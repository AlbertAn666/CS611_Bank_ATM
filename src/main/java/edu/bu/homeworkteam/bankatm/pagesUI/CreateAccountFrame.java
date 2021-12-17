/*
 * Created by JFormDesigner on Thu Dec 16 18:02:39 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.entities.AccountType;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class CreateAccountFrame extends JFrame {
    private DefaultComboBoxModel<AccountType> comboBoxModel;


    private HomeFrame homeFrame;
    public void setHomeFrame(HomeFrame homeFrame){
        this.homeFrame=homeFrame;
    }
    public CreateAccountFrame() {
        initComponents();
        comboBoxModel=new DefaultComboBoxModel<>(AccountType.values());
        comboBox.setModel(comboBoxModel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                submit();
            }
        });

    }






    private void submit(){
        int customerId= GuiManager.getInstance().getLoggedInCustomerId();
        AccountType accountType=(AccountType)comboBoxModel.getSelectedItem();
        int accountId=GuiManager.getInstance().getCustomerService().createAccount(customerId,accountType);
        dispose();
        PromptFrame promptFrame=new PromptFrame("Your created account ID is "+accountId);
        //promptDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
         homeFrame.refresh();
        dispose();
    }
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        comboBox = new JComboBox<>();
        label = new JLabel();
        button = new JButton();

        //======== this ========
        setVisible(true);
        setTitle("Create Account");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(comboBox);
        comboBox.setBounds(40, 90, 315, comboBox.getPreferredSize().height);

        //---- label ----
        label.setText("Please select your type of account");
        contentPane.add(label);
        label.setBounds(new Rectangle(new Point(45, 60), label.getPreferredSize()));

        //---- button ----
        button.setText("Okay");
        contentPane.add(button);
        button.setBounds(245, 180, 120, button.getPreferredSize().height);

        contentPane.setPreferredSize(new Dimension(405, 255));
        setSize(405, 255);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JComboBox<AccountType> comboBox;
    private JLabel label;
    private JButton button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
