/*
 * Created by JFormDesigner on Thu Dec 16 12:22:07 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class HomeFrame extends JFrame {
    public HomeFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menu2 = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu3 = new JMenu();
        menu4 = new JMenu();
        menuItem6 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        accountList = new JList();
        accountIdLabel = new JLabel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        accountIdLabel2 = new JLabel();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== menuBar ========
        {

            //======== menu1 ========
            {
                menu1.setText("Accounts");

                //---- menuItem1 ----
                menuItem1.setText("New account");
                menu1.add(menuItem1);
            }
            menuBar.add(menu1);

            //======== menu2 ========
            {
                menu2.setText("Stocks");

                //---- menuItem2 ----
                menuItem2.setText("View my stocks");
                menu2.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("View stocks in markets");
                menu2.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setText("Sell my stock");
                menu2.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("Buy my stock");
                menu2.add(menuItem5);
            }
            menuBar.add(menu2);

            //======== menu3 ========
            {
                menu3.setText("Loans");
            }
            menuBar.add(menu3);

            //======== menu4 ========
            {
                menu4.setText("Customer");

                //---- menuItem6 ----
                menuItem6.setText("Logout");
                menu4.add(menuItem6);
            }
            menuBar.add(menu4);
        }
        setJMenuBar(menuBar);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(accountList);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 35, 235, 530);

        //---- accountIdLabel ----
        accountIdLabel.setText("Account ID");
        contentPane.add(accountIdLabel);
        accountIdLabel.setBounds(15, 10, 145, accountIdLabel.getPreferredSize().height);

        //======== scrollPane2 ========
        {

            //---- textArea1 ----
            textArea1.setEditable(false);
            textArea1.setBackground(UIManager.getColor("Button.background"));
            scrollPane2.setViewportView(textArea1);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(270, 35, 545, 525);

        //---- accountIdLabel2 ----
        accountIdLabel2.setText("Account Information");
        contentPane.add(accountIdLabel2);
        accountIdLabel2.setBounds(270, 10, 145, 16);

        contentPane.setPreferredSize(new Dimension(840, 625));
        setSize(840, 625);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu menu3;
    private JMenu menu4;
    private JMenuItem menuItem6;
    private JScrollPane scrollPane1;
    private JList accountList;
    private JLabel accountIdLabel;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JLabel accountIdLabel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new LoginFrame();
        new HomeFrame();
    }
}
