package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;
import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * displays options for manager(admin) input
 */
public class ManagerMenuFrame extends JFrame {
    ManagerStockService managerStockService;
    JPanel jp = new JPanel();
    JButton checkCustomerButton = new JButton("Check Customers");
    JButton getTransactionButton = new JButton("Daily Report");
    JButton payInterestButton = new JButton("Pay Interest");
    JButton chargeInterestButton = new JButton("Charge Interest");
    JButton stockMarketButton = new JButton("Stock Market");

    public ManagerMenuFrame() {
        managerStockService = new ManagerStockService();
        setSize(900, 800);
        setLocation(1500, 400);
        add(jp);
        jp.setLayout(null);

        checkCustomerButton.setBounds(250, 150, 400, 50);
        getTransactionButton.setBounds(250, 250, 400, 50);
        payInterestButton.setBounds(250, 350, 400, 50);
        chargeInterestButton.setBounds(250, 450, 400, 50);
        stockMarketButton.setBounds(250, 550, 400, 50);
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
                TransactionSelectFrame transactionSelectFrame = new TransactionSelectFrame();
                transactionSelectFrame.setVisible(true);
            }
        });

        payInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.getInstance().getManagerService().payInterest();
                JOptionPane.showMessageDialog(null, "Successfully pay interest",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });

        chargeInterestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GuiManager.getInstance().getManagerService().chargeInterest();
                JOptionPane.showMessageDialog(null, "Successfully charge interest",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });

        stockMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> stocksInfo = managerStockService.getStockInfo();
                StockMarketFrame stockMarketFrame = new StockMarketFrame(stocksInfo);
                stockMarketFrame.setVisible(true);
            }
        });
    }
}
