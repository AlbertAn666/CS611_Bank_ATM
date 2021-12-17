/*
 * Created by JFormDesigner on Thu Dec 16 22:03:09 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Shareholding;
import edu.bu.homeworkteam.bankatm.entities.Stock;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Use for customer to view stocks
 * @author gung
 */
public class ViewStocksFrame extends JFrame {
    private Customer customer;


    public ViewStocksFrame() {
        initComponents();

        sellStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openSellStockFrame();
            }
        });

        buyStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openBuyStockFrame();
            }
        });

        stocksInMarketsJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        stocksInMarketsJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList jList=(JList) listSelectionEvent.getSource();
                Stock stock=(Stock) jList.getSelectedValue();
                if(stock!=null){
                    stockInfoArea.setText(getStockAndShareHoldingInformation(stock));
                }
            }
        });


        stocksInMarketsJList.setCellRenderer(new ListCellRenderer<Stock>() {
            @Override
            public Component getListCellRendererComponent(JList jList, Stock stock, int i, boolean isSelected, boolean b1) {
                JLabel jLabel=new JLabel();
                jLabel.setOpaque(true);

                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(stock.getId()).append(" ");
                stringBuilder.append(stock.getSymbol()).append(" ");
                stringBuilder.append(stock.getPrice()).append(" ");

                jLabel.setText(stringBuilder.toString());
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

        myStocksJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myStocksJList.addListSelectionListener(stocksInMarketsJList.getListSelectionListeners()[0]);
        myStocksJList.setCellRenderer(stocksInMarketsJList.getCellRenderer());
        refresh();
    }




    private void openSellStockFrame(){
        new SellStockFrame().setViewStocksFrame(this);
    }
    private void openBuyStockFrame(){
        new BuyStockFrame().setViewStocksFrame(this);
    }





    public void refresh(){
        int customerId=GuiManager.getInstance().getLoggedInCustomerId();
        customer= GuiManager.getInstance().getCustomerRepository().findById(customerId).get();
        System.out.println(customer.getAccounts().size());
        loadStockData();
    }


    private void loadStockData(){

        DefaultListModel<Stock> stocksInMarketsListModel=new DefaultListModel<>();
        Iterable<Stock> stockIterable = GuiManager.getInstance().getStockRepository().findAll();
        for (Stock stock :
                stockIterable) {
            stocksInMarketsListModel.addElement(stock);
        }
        stocksInMarketsJList.setModel(stocksInMarketsListModel);

        DefaultListModel<Stock> myStocksListModel=new DefaultListModel<>();
        stockIterable = customer.getShareholdings().keySet();
        for (Stock stock :
                stockIterable) {
            myStocksListModel.addElement(stock);
        }
        myStocksJList.setModel(myStocksListModel);
    }


//    private String getStockInformation(Stock stock){
//
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("Stock ID: ").append(stock.getId()).append("\n");
//        stringBuilder.append("Name: ").append(stock.getName()).append("\n");
//        stringBuilder.append("Symbol: ").append(stock.getSymbol()).append("\n");
//        stringBuilder.append("Price: ").append(stock.getPrice()).append("\n");
//        stringBuilder.append("Currency: ").append(stock.getCurrency()).append("\n");
//
//        return stringBuilder.toString();
//    }


    private String getStockAndShareHoldingInformation(Stock stock){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Stock ID: ").append(stock.getId()).append("\n");
        stringBuilder.append("Name: ").append(stock.getName()).append("\n");
        stringBuilder.append("Symbol: ").append(stock.getSymbol()).append("\n");
        stringBuilder.append("Price: ").append(stock.getPrice()).append("\n");
        stringBuilder.append("Currency: ").append(stock.getCurrency()).append("\n");

        Shareholding shareholding=customer.getShareholdings().get(stock);
        if(shareholding!=null){
            stringBuilder.append("Number of shares: ").append(shareholding.getNumberOfShares()).append("\n");
            stringBuilder.append("Average cost price per share: ").append(shareholding.getAverageCostPricePerShare()).append("\n");

            float predictedBenefit=shareholding.getNumberOfShares()*(stock.getPrice()-shareholding.getAverageCostPricePerShare());
            stringBuilder.append("Predicted benefit: ").append(predictedBenefit).append("\n");
        }

        return stringBuilder.toString();
    }


    private HomeFrame homeFrame;
    public void setHomeFrame(HomeFrame homeFrame){
        this.homeFrame=homeFrame;
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
        buyStockButton = new JButton();
        sellStockButton = new JButton();
        scrollPane1 = new JScrollPane();
        stockInfoArea = new JTextArea();

        //======== this ========
        setTitle("Stocks");
        setVisible(true);
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

        //---- buyStockButton ----
        buyStockButton.setText("Buy stock");
        contentPane.add(buyStockButton);
        buyStockButton.setBounds(540, 565, 160, buyStockButton.getPreferredSize().height);

        //---- sellStockButton ----
        sellStockButton.setText("Sell stock");
        contentPane.add(sellStockButton);
        sellStockButton.setBounds(700, 565, 160, 29);

        //======== scrollPane1 ========
        {

            //---- stockInfoArea ----
            stockInfoArea.setEditable(false);
            stockInfoArea.setBackground(UIManager.getColor("Button.background"));
            stockInfoArea.setLineWrap(true);
            stockInfoArea.setWrapStyleWord(true);
            scrollPane1.setViewportView(stockInfoArea);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(310, 315, 570, 230);

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
    private JButton buyStockButton;
    private JButton sellStockButton;
    private JScrollPane scrollPane1;
    private JTextArea stockInfoArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
