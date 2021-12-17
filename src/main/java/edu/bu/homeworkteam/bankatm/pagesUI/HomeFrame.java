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
 *  the main frame of customer and displays options for customer
 * @author gung
 */

public class HomeFrame extends JFrame {
    Customer customer;
    public HomeFrame(){
        initComponents();

//        sellStockMenuItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                new SellStockFrame();
//            }
//        });
//
//        buyStockMenuItem.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                new BuyStockFrame();
//            }
//        });

        refreshMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                refresh();
            }
        });

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

        depositMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openDepositFrame();
            }
        });

        withdrawMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openWithdrawFrame();
            }
        });

        viewStocksMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openViewStocksFrame();
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


    private void openViewStocksFrame(){
        new ViewStocksFrame().setHomeFrame(this);
    }
    private void openTransferFrame(){
        new TransferFrame().setHomeFrame(this);
    }

    private void openDepositFrame(){
        new DepositFrame().setHomeFrame(this);
    }

    private void openCreateAccountFrame(){
        new CreateAccountFrame().setHomeFrame(this);
    }

    private void openWithdrawFrame(){
        new WithdrawFrame().setHomeFrame(this);
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
        withdrawMenuItem = new JMenuItem();
        refreshMenuItem = new JMenuItem();
        depositMenuItem = new JMenuItem();
        stockMenu = new JMenu();
        viewStocksMenuItem = new JMenuItem();
        sellStockMenuItem = new JMenuItem();
        buyStockMenuItem = new JMenuItem();
        loanMenu = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menuItem3 = new JMenuItem();
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

                //---- withdrawMenuItem ----
                withdrawMenuItem.setText("Withdraw");
                accountMenu.add(withdrawMenuItem);

                //---- refreshMenuItem ----
                refreshMenuItem.setText("Refresh");
                accountMenu.add(refreshMenuItem);

                //---- depositMenuItem ----
                depositMenuItem.setText("Deposit");
                accountMenu.add(depositMenuItem);
            }
            menuBar.add(accountMenu);

            //======== stockMenu ========
            {
                stockMenu.setText("Stocks");

                //---- viewStocksMenuItem ----
                viewStocksMenuItem.setText("View stocks");
                stockMenu.add(viewStocksMenuItem);

                //---- sellStockMenuItem ----
                sellStockMenuItem.setText("Sell stock");
                stockMenu.add(sellStockMenuItem);

                //---- buyStockMenuItem ----
                buyStockMenuItem.setText("Buy stock");
                stockMenu.add(buyStockMenuItem);
            }
            menuBar.add(stockMenu);

            //======== loanMenu ========
            {
                loanMenu.setText("Loans");

                //---- menuItem1 ----
                menuItem1.setText("View collateral");
                loanMenu.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("Borrow loan");
                loanMenu.add(menuItem2);

                //---- menuItem3 ----
                menuItem3.setText("Repay loan");
                loanMenu.add(menuItem3);
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
    private JMenuItem withdrawMenuItem;
    private JMenuItem refreshMenuItem;
    private JMenuItem depositMenuItem;
    private JMenu stockMenu;
    private JMenuItem viewStocksMenuItem;
    private JMenuItem sellStockMenuItem;
    private JMenuItem buyStockMenuItem;
    private JMenu loanMenu;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem3;
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
