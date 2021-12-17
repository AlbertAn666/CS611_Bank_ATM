package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * frame for checking on a specific customer
 */
public class CheckCustomerFrame extends JFrame {
    ManagerService managerService;
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Input customer id: ");
    JTextField textField = new JTextField(30);
    JButton confirmButton = new JButton("Confirm");

    public CheckCustomerFrame() {
        managerService = new ManagerService();
        setSize(500, 300);
        add(panel);
        panel.setLayout(null);

        label.setBounds(100, 100, 150, 25);
        panel.add(label);
        textField.setBounds(250, 100, 150, 25);
        panel.add(textField);
        confirmButton.setBounds(200, 200, 100, 25);
        panel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = Integer.parseInt(textField.getText());
                Vector<Vector<String>> customerAccounts = managerService.checkUpCustomerAccounts(customerId);
                if(customerAccounts.size() == 0) {
                    JOptionPane.showMessageDialog(null, "Customer has no accounts",
                            "ERROR",JOptionPane.PLAIN_MESSAGE);
                } else {
                    CustomerAccountsFrame customerAccountsFrame = new CustomerAccountsFrame(customerAccounts, customerId);
                    customerAccountsFrame.setVisible(true);
                }
            }
        });
    }

}
