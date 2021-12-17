/*
 * Created by JFormDesigner on Thu Dec 16 12:22:07 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.entities.Account;
import edu.bu.homeworkteam.bankatm.entities.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author unknown
 */

public class HomeFrame extends JFrame {
    Customer customer;
    public HomeFrame(){
        initComponents();


        createAccountMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openCreateAccountFrame();
            }
        });

        transferMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openTransferFrame();
            }
        });

        accountJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        accountJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList jList=(JList)listSelectionEvent.getSource();
                Account account=(Account)jList.getSelectedValue();
                if(account!=null) {
                    accountInfoArea.setText(getAccountInformation(account));
                }
            }
        });

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
        refresh();
    }


    private void openTransferFrame(){
        new TransferFrame().setHomeFrame(this);
    }


    private void openCreateAccountFrame(){
        new CreateAccountFrame().setHomeFrame(this);
    }

    public void refresh(){
        int customerId=GuiManager.getInstance().getLoggedInCustomerId();
        customer= GuiManager.getInstance().getCustomerRepository().findById(customerId).get();
        System.out.println(customer.getAccounts().size());
        renderAccountJList();
    }

    private void renderAccountJList(){

        DefaultListModel<Account> listModel=new DefaultListModel<>();
        for (Account account:customer.getAccounts()
             ) {
            listModel.addElement(account);
        }
        accountJList.setModel(listModel);
    }
    private String getAccountInformation(Account account){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Account ID: ").append(account.getId()).append("\n");
        stringBuilder.append("Account Type: ").append(account.getAccountType().toString()).append("\n");
        stringBuilder.append("Balances: \n");

        for (Map.Entry entry :account.getBalances().entrySet()
                ) {
            stringBuilder.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        };

        return stringBuilder.toString();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        menuBar = new JMenuBar();
        accountMenu = new JMenu();
        createAccountMenuItem = new JMenuItem();
        transferMenuItem = new JMenuItem();
        stockMenu = new JMenu();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        menuItem5 = new JMenuItem();
        loanMenu = new JMenu();
        customerMenu = new JMenu();
        menuItem6 = new JMenuItem();
        scrollPane1 = new JScrollPane();
        accountJList = new JList();
        accountIdLabel = new JLabel();
        scrollPane2 = new JScrollPane();
        accountInfoArea = new JTextArea();
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

                //---- createAccountMenuItem ----
                createAccountMenuItem.setText("Create account");
                accountMenu.add(createAccountMenuItem);

                //---- transferMenuItem ----
                transferMenuItem.setText("Transfer");
                accountMenu.add(transferMenuItem);
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

            //---- accountInfoArea ----
            accountInfoArea.setEditable(false);
            accountInfoArea.setBackground(UIManager.getColor("Button.background"));
            accountInfoArea.setLineWrap(true);
            scrollPane2.setViewportView(accountInfoArea);
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
    private JMenuItem createAccountMenuItem;
    private JMenuItem transferMenuItem;
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
    private JTextArea accountInfoArea;
    private JLabel accountIdLabel2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        new LoginFrame();
        new HomeFrame();
    }
}
