package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ManagerMenuFrame extends JFrame {
    JPanel jp = new JPanel();
    JButton checkCustomerButton = new JButton("Check Customers");
    JButton getTransactionButton = new JButton("Daily Report");
    JButton payInterestButton = new JButton("Pay Interest");
    JButton chargeInterestButton = new JButton("Charge Interest");
    JButton stockMarketButton = new JButton("Stock Market");
    ManagerService managerService;

    public ManagerMenuFrame() {
        managerService = new ManagerService();

        setSize(900, 1100);
        setLocation(1500, 400);
        add(jp);
        jp.setLayout(null);

        checkCustomerButton.setBounds(250, 250, 400, 100);
        getTransactionButton.setBounds(250, 375, 400, 100);
        payInterestButton.setBounds(250, 500, 400, 100);
        chargeInterestButton.setBounds(250, 625, 400, 100);
        stockMarketButton.setBounds(250, 750, 400, 100);
        jp.add(checkCustomerButton);
        jp.add(getTransactionButton);
        jp.add(payInterestButton);
        jp.add(chargeInterestButton);
        jp.add(stockMarketButton);

        setVisible(true);

        checkCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckCustomerFrame checkCustomerFrame = new CheckCustomerFrame();
                checkCustomerFrame.setVisible(true);
            }
        });

        getTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> data = managerService.checkUpTodayTransactions();
                ShowTransactionFrame showTransactionFrame = new ShowTransactionFrame(data);
                showTransactionFrame.setVisible(true);
            }
        });

        payInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerService.payInterest();
                JOptionPane.showMessageDialog(null, "Successfully pay interest",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });

        chargeInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                managerService.chargeInterest();
                JOptionPane.showMessageDialog(null, "Successfully charge interest",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });

        stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockMarketFrame stockMarketFrame = new StockMarketFrame();
                stockMarketFrame.setVisible(true);
            }
        });
    }
}
