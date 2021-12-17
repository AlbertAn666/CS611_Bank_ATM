/*
 * Created by JFormDesigner on Thu Dec 16 22:03:09 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author gung
 */
public class ViewStocksFrame extends JFrame {
    public ViewStocksFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        myStocksJList = new JList();
        scrollPane3 = new JScrollPane();
        stocksInMarketsJList = new JList();
        label2 = new JLabel();
        stockInfoField = new JTextField();
        buyStockButton = new JButton();
        sellStockButton = new JButton();

        //======== this ========
        setTitle("Stocks");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Stocks in markets");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(myStocksJList);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(310, 40, 570, 255);

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(stocksInMarketsJList);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(20, 40, 260, 560);

        //---- label2 ----
        label2.setText("My stocks");
        contentPane.add(label2);
        label2.setBounds(305, 10, 113, 16);

        //---- stockInfoField ----
        stockInfoField.setBackground(UIManager.getColor("CheckBox.background"));
        contentPane.add(stockInfoField);
        stockInfoField.setBounds(305, 315, 575, 215);

        //---- buyStockButton ----
        buyStockButton.setText("Buy stock");
        contentPane.add(buyStockButton);
        buyStockButton.setBounds(540, 565, 160, buyStockButton.getPreferredSize().height);

        //---- sellStockButton ----
        sellStockButton.setText("Sell stock");
        contentPane.add(sellStockButton);
        sellStockButton.setBounds(700, 565, 160, 29);

        contentPane.setPreferredSize(new Dimension(905, 645));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JList myStocksJList;
    private JScrollPane scrollPane3;
    private JList stocksInMarketsJList;
    private JLabel label2;
    private JTextField stockInfoField;
    private JButton buyStockButton;
    private JButton sellStockButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
