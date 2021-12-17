package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;
import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * the frame for the admin to operate with stocks
 */
public class StockMarketFrame extends JFrame {
    /*
    ManagerStockService managerStockService;
    JButton addButton = new JButton("Add new stocks");
    JButton modifyButton = new JButton("Modify stocks price");
    JPanel panel = new JPanel();

    public StockMarketFrame() {
        managerStockService = new ManagerStockService();
        setSize(900, 1100);
        add(panel);
        setLayout(null);

        addButton.setBounds(400, 400, 100, 50);
        panel.add(addButton);
        modifyButton.setBounds(400, 600, 100, 50);
        panel.add(modifyButton);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStockFrame addStockFrame = new AddStockFrame();
                addStockFrame.setVisible(true);
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyStockFrame modifyStockFrame = new ModifyStockFrame();
                modifyStockFrame.setVisible(true);
            }
        });

     */
    ManagerService managerService;
    JPanel panel = new JPanel();
    JButton addButton = new JButton("Add Stock");
    JButton modifyButton = new JButton("Modify Stock");

    public StockMarketFrame() {
        managerService = new ManagerService();
        setSize(900, 800);
        add(panel);
        panel.setLayout(null);

        addButton.setBounds(300, 250, 300, 50);
        panel.add(addButton);
        modifyButton.setBounds(300, 500, 300, 50);
        panel.add(modifyButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStockFrame addStockFrame = new AddStockFrame();
                addStockFrame.setVisible(true);
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModifyStockFrame modifyStockFrame = new ModifyStockFrame();
                modifyStockFrame.setVisible(true);
            }
        });
    }
}
