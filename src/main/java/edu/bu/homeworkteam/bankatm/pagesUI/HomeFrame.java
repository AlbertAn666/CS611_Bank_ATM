/*
 * Created by JFormDesigner on Thu Dec 16 12:22:07 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Customer;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */

public class HomeFrame extends JFrame {
    private int customerId;
    private Customer customer;


    public HomeFrame(int customerId){

        this();
        this.customerId=customerId;
        System.out.println(customerId);
        customer= GuiManager.getInstance().getCustomerRepository().findById(customerId).get();
        System.out.println(customer.getAccounts().size());
        renderAccountJList();

    }

    private void update(){

    }

    private void renderAccountJList(){
        DefaultListModel<Account> listModel=new DefaultListModel<>();
        for (Account account:customer.getAccounts()
             ) {
            listModel.addElement(account);
        }
        accountJList.setModel(listModel);
        accountJList.setCellRenderer(new ListCellRenderer<Account>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Account> jList, Account account, int i, boolean isSelected, boolean b1) {
                JLabel jLabel=new JLabel();
                jLabel.setOpaque(true);
                jLabel.setText(String.valueOf(account.getId()));
                if(!isSelected){
                    jLabel.setBackground(UIManager.getColor("List.background"));
                    jLabel.setForeground(UIManager.getColor("List.foreground"));
                }else{
                    jLabel.setBackground(UIManager.getColor("List.selectionBackground"));
                    jLabel.setForeground(UIManager.getColor("List.selectionForeground"));
                }
                return jLabel;
            }
        });

    }

    public HomeFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar = new JMenuBar();
        accountMenu = new JMenu();
        menuItem1 = new JMenuItem();
        stockMenu = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        loanMenu = new JMenu();
        customerMenu = new JMenu();
        menuItem6 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        accountJList = new JList<>();
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

            //======== accountMenu ========
            {
                accountMenu.setText("Accounts");

                //---- menuItem1 ----
                menuItem1.setText("New account");
                accountMenu.add(menuItem1);
            }
            menuBar.add(accountMenu);

            //======== stockMenu ========
            {
                stockMenu.setText("Stocks");

                //---- menuItem2 ----
                menuItem2.setText("View my stocks");
                stockMenu.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("View stocks in markets");
                stockMenu.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setText("Sell my stock");
                stockMenu.add(menuItem4);

                //---- menuItem5 ----
                menuItem5.setText("Buy my stock");
                stockMenu.add(menuItem5);
            }
            menuBar.add(stockMenu);

            //======== loanMenu ========
            {
                loanMenu.setText("Loans");
            }
            menuBar.add(loanMenu);

            //======== customerMenu ========
            {
                customerMenu.setText("Customer");

                //---- menuItem6 ----
                menuItem6.setText("Logout");
                customerMenu.add(menuItem6);
            }
            menuBar.add(customerMenu);
        }
        setJMenuBar(menuBar);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(accountJList);
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
    private JMenu accountMenu;
    private JMenuItem menuItem1;
    private JMenu stockMenu;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JMenuItem menuItem5;
    private JMenu loanMenu;
    private JMenu customerMenu;
    private JMenuItem menuItem6;
    private JScrollPane scrollPane1;
    private JList accountJList;
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
