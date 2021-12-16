package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMarketFrame extends JFrame {
    ManagerStockService managerStockService;
    JButton addButton = new JButton("Add new stocks");
    JButton modifyButton = new JButton("Modify stocks price");
    JPanel panel = new JPanel();

    public StockMarketFrame() {
        managerStockService = new ManagerStockService();
        setLayout(null);
        setSize(900, 1100);
        setLocation(1500, 400);
        addButton.setBounds(300, 400, 300, 100);
        modifyButton.setBounds(300, 600, 300, 100);
        panel.add(addButton);
        panel.add(modifyButton);
        add(panel);

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
