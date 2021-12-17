package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *  frame for the admin to check the account of the customer
 */
public class CustomerAccountsFrame extends JFrame {
    private int selectedAccount = 0;
    ManagerService managerService;
    JPanel panel = new JPanel();
    JTable accountTable = new JTable();
    Vector<String> column = new Vector<>();
    JButton selectButton = new JButton("Check account info");
    JButton checkAccountButton = new JButton("Check his transactions");
    JButton checkLoanButton = new JButton("Check his loans");


    public CustomerAccountsFrame(Vector<Vector<String>> data, int customerId) {
        managerService = new ManagerService();

        column.add("Account Id"); column.add("Account Type");
        DefaultTableModel model = new DefaultTableModel(data, column);
        accountTable.setModel(model);
        accountTable.setRowSelectionAllowed(true);

        ListSelectionModel selectionModel = accountTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setSize(600, 600);
//        accountTable.setBounds(10, 10, 880, 900);
//        selectButton.setBounds(100,  1000, 100, 80);
//        checkButton.setBounds(400, 1000, 100, 80);
        JScrollPane pane = new JScrollPane(accountTable);
        panel.add(pane);
        panel.add(checkAccountButton);
        panel.add(selectButton);
        panel.add(checkLoanButton);
        add(panel);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String accountId = "";
                int row = accountTable.getSelectedRow();
                accountId = (String)accountTable.getValueAt(row, 0);
                selectedAccount = Integer.parseInt(accountId);
            }
        });

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedAccount != 0) {
                    Vector<Vector<String>> accountInfo =  managerService.checkUpBalances(selectedAccount);
                    if(accountInfo.size() == 0) {
                        JOptionPane.showMessageDialog(null, "No balance in this account",
                                "ERROR",JOptionPane.PLAIN_MESSAGE);
                    } else {
                        AccountInfoFrame accountInfoFrame = new AccountInfoFrame(accountInfo);
                        accountInfoFrame.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Didn't select an account!",
                            "ERROR",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        checkAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> data = managerService.checkUpTransactions(customerId);
                ShowTransactionFrame showTransactionFrame = new ShowTransactionFrame(data);
                showTransactionFrame.setVisible(true);
            }
        });

        checkLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> data = managerService.checkUpCustomerLoans(customerId);
                ShowLoanFrame showLoanFrame = new ShowLoanFrame(data);
                showLoanFrame.setVisible(true);
            }
        });


    }
}
