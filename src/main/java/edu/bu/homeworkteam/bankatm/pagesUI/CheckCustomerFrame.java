package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CheckCustomerFrame extends JFrame {
    ManagerService managerService;
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Input customer id: ");
    JTextField textField = new JTextField(30);
    JButton confirmButton = new JButton("Confirm");

    public CheckCustomerFrame() {
        managerService = new ManagerService();
        setLocation(1500, 400);
        setSize(900, 1100);
        add(panel);
        panel.setLayout(null);

        label.setBounds(300, 250, 80, 25);
        panel.add(label);
        textField.setBounds(400, 250, 200, 25);
        panel.add(textField);
        confirmButton.setBounds(350, 350, 100, 25);
        panel.add(confirmButton);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int customerId = Integer.parseInt(textField.getText());
                Vector<Vector<String>> customerAccounts = managerService.checkUpCustomerAccounts(customerId);
                CustomerAccountsFrame customerAccountsFrame = new CustomerAccountsFrame(customerAccounts, customerId);
                customerAccountsFrame.setVisible(true);
            }
        });
    }

}
