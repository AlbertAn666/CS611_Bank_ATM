package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class CustomerAccountsFrame extends JFrame {
    int selectedAccount = 0;
    ManagerService managerService;
    JScrollPane pane = new JScrollPane();
    JPanel panel = new JPanel();
    JTable accountTable = new JTable();
    Vector<String> column = new Vector<>();
    JButton selectButton = new JButton("Check account info");
    JButton checkButton = new JButton("Check his transactions");


    public CustomerAccountsFrame(Vector<Vector<String>> data, int customerId) {
        managerService = new ManagerService();

        column.add("Account Id"); column.add("Account Type");
        DefaultTableModel model = new DefaultTableModel(data, column);
        accountTable.setModel(model);
        accountTable.setRowSelectionAllowed(true);

        ListSelectionModel selectionModel = accountTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setSize(900, 1100);
        setLocation(1500, 400);
        accountTable.setBounds(10, 10, 880, 900);
        selectButton.setBounds(100,  1000, 300, 80);
        checkButton.setBounds(500, 1000, 300, 80);
        panel.add(selectButton);
        panel.add(checkButton);
        pane.add(accountTable);
        add(pane);
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
                    AccountInfoFrame accountInfoFrame = new AccountInfoFrame(accountInfo);
                    accountInfoFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Didn't select an account!",
                            "ERROR",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> data = managerService.checkUpTransactions(customerId);
                ShowTransactionFrame showTransactionFrame = new ShowTransactionFrame(data);
                showTransactionFrame.setVisible(true);
            }
        });


    }
}
