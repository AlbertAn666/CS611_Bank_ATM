/*
 * Created by JFormDesigner on Thu Dec 16 22:29:47 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;


import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.Customer;
import edu.bu.homeworkteam.bankatm.entities.Transaction;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * View all the transaction
 * @author gung
 */
public class ViewTransactionsFrame extends JFrame {
    Customer customer;
    public ViewTransactionsFrame() {
        initComponents();


        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList jList=(JList)listSelectionEvent.getSource();
                Transaction transaction=(Transaction)jList.getSelectedValue();
                if(transaction!=null) {
                    transactionInfoArea.setText(getTransactionInformation(transaction));
                }
            }
        });

        jList.setCellRenderer(new ListCellRenderer<Transaction>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Transaction> jList, Transaction transaction, int i, boolean isSelected, boolean b1) {
                JLabel jLabel=new JLabel();
                jLabel.setOpaque(true);
                jLabel.setText(transaction.getId() +" "+transaction.getDateTimeString());
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

    private String getTransactionInformation(Transaction transaction){

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Transaction ID: ").append(transaction.getId()).append("\n");
        stringBuilder.append("Time: ").append(transaction.getDateTimeString()).append("\n");
        stringBuilder.append("Type: ").append(transaction.getTransactionType()).append("\n");
        if(transaction.getFromAccount()!=null){
        stringBuilder.append("From Account ID ").append(transaction.getFromAccount().getId()).append("\n");
        }
        if(transaction.getToAccount()!=null) {
            stringBuilder.append("To Account ID: ").append(transaction.getToAccount().getId()).append("\n");
        }
        stringBuilder.append("Amount: ").append(transaction.getCurrency()).append(transaction.getAmount()).append("\n");
        return stringBuilder.toString();
    }

    public void refresh(){
        int customerId=GuiManager.getInstance().getLoggedInCustomerId();
        customer= GuiManager.getInstance().getCustomerRepository().findById(customerId).get();
        System.out.println(customer.getAccounts().size());
        renderJList();

    }


    private void renderJList(){
        DefaultListModel<Transaction> listModel=new DefaultListModel<>();
        List<Transaction> transactionList=GuiManager.getInstance().getTransactionRepository().getTransactionsByCustomerId(customer.getId());
        for (Transaction transaction:transactionList
        ) {
            listModel.addElement(transaction);
            System.out.println("jkksf"+transaction.getId());

        }
        jList.setModel(listModel);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        jList = new JList();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        transactionInfoArea = new JTextArea();
        label2 = new JLabel();

        //======== this ========
        setTitle("View transactions");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(jList);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 35, 285, 560);

        //---- label1 ----
        label1.setText("Transactions");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));

        //======== scrollPane2 ========
        {

            //---- transactionInfoArea ----
            transactionInfoArea.setBackground(UIManager.getColor("CheckBox.background"));
            scrollPane2.setViewportView(transactionInfoArea);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(320, 35, 475, 555);

        //---- label2 ----
        label2.setText("Transaction Information");
        contentPane.add(label2);
        label2.setBounds(320, 10, 225, 16);

        contentPane.setPreferredSize(new Dimension(815, 630));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane1;
    private JList jList;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTextArea transactionInfoArea;
    private JLabel label2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
