/*
 * Created by JFormDesigner on Thu Dec 16 22:29:47 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.entities.Collateral;
import edu.bu.homeworkteam.bankatm.entities.Currency;
import edu.bu.homeworkteam.bankatm.entities.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * View all the collateral
 * @author gung
 */
public class ViewCollateralFrame extends JFrame {
    Customer customer;
    public ViewCollateralFrame() {
        initComponents();

        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openBorrowLoanFrame();
            }
        });

        repayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                openRepayLoanFrame();
            }
        });

        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList jList=(JList)listSelectionEvent.getSource();
                Collateral collateral=(Collateral)jList.getSelectedValue();
                if(collateral!=null) {
                    collateralInfoArea.setText(getCollateralInformation(collateral));
                }
            }
        });

        jList.setCellRenderer(new ListCellRenderer<Collateral>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Collateral> jList, Collateral collateral, int i, boolean isSelected, boolean b1) {
                JLabel jLabel=new JLabel();
                jLabel.setOpaque(true);
                jLabel.setText(String.valueOf(collateral.getId())+" "+collateral.getName());
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

    private void openBorrowLoanFrame(){
        new BorrowLoanFrame().setViewCollateralFrame(this);
    }

    private void openRepayLoanFrame(){
        new RepayLoanFrame().setViewCollateralFrame(this);
    }
    private String getCollateralInformation(Collateral collateral){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Collateral ID: ").append(collateral.getId()).append("\n");
        stringBuilder.append("Name: ").append(collateral.getName()).append("\n");
        stringBuilder.append("Currency: ").append(collateral.getCurrency()).append("\n");
        stringBuilder.append("Value: ").append(collateral.getValue()).append("\n");

        return stringBuilder.toString();
    }

    public void refresh(){
        int customerId=GuiManager.getInstance().getLoggedInCustomerId();
        customer= GuiManager.getInstance().getCustomerRepository().findById(customerId).get();
        System.out.println(customer.getAccounts().size());
        collateralInfoArea.setText("");
        renderJList();



    }


    private void renderJList(){
        DefaultListModel<Collateral> listModel=new DefaultListModel<>();
        List<Collateral> collateralList=GuiManager.getInstance().getCollateralRepository().getByCustomerId(customer.getId());
        Map<Currency, Float> loans= new HashMap<>();
        Map<Currency, Float> interests= new HashMap<>();
        for (Collateral collateral:collateralList
        ) {
            listModel.addElement(collateral);
            Float loan=loans.get(collateral.getCurrency());
            if(loan==null){
                loans.put(collateral.getCurrency(),collateral.getValue());
                interests.put(collateral.getCurrency(),collateral.getValue()*0.013f);
            }else{
                float newLoan=loan+collateral.getValue();
                loans.put(collateral.getCurrency(),newLoan);
                interests.put(collateral.getCurrency(),newLoan*0.013f);
            }

        }
        StringBuilder loanStringBuilder=new StringBuilder();
        jList.setModel(listModel);
        for (Map.Entry<Currency, Float> entry :
                loans.entrySet()) {
            loanStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        loanArea.setText(loanStringBuilder.toString());

        StringBuilder interestStringBuilder=new StringBuilder();
        for (Map.Entry<Currency, Float> entry :
                interests.entrySet()) {
            interestStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }

        interestArea.setText(interestStringBuilder.toString());
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        jList = new JList();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        collateralInfoArea = new JTextArea();
        label2 = new JLabel();
        borrowButton = new JButton();
        repayButton = new JButton();
        scrollPane3 = new JScrollPane();
        loanArea = new JTextArea();
        label3 = new JLabel();
        label4 = new JLabel();
        scrollPane4 = new JScrollPane();
        interestArea = new JTextArea();

        //======== this ========
        setTitle("Collateral");
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
        label1.setText("Collateral");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));

        //======== scrollPane2 ========
        {

            //---- collateralInfoArea ----
            collateralInfoArea.setBackground(UIManager.getColor("CheckBox.background"));
            scrollPane2.setViewportView(collateralInfoArea);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(320, 35, 475, 335);

        //---- label2 ----
        label2.setText("Collateral Information");
        contentPane.add(label2);
        label2.setBounds(320, 10, 225, 16);

        //---- borrowButton ----
        borrowButton.setText("borrow loan");
        contentPane.add(borrowButton);
        borrowButton.setBounds(485, 555, 150, 30);

        //---- repayButton ----
        repayButton.setText("repay loan");
        contentPane.add(repayButton);
        repayButton.setBounds(640, 555, 150, 30);

        //======== scrollPane3 ========
        {

            //---- loanArea ----
            loanArea.setEditable(false);
            loanArea.setBackground(UIManager.getColor("Button.background"));
            scrollPane3.setViewportView(loanArea);
        }
        contentPane.add(scrollPane3);
        scrollPane3.setBounds(320, 415, 240, 130);

        //---- label3 ----
        label3.setText("Loan summary");
        contentPane.add(label3);
        label3.setBounds(320, 385, 185, label3.getPreferredSize().height);

        //---- label4 ----
        label4.setText("Interest predicted summary");
        contentPane.add(label4);
        label4.setBounds(570, 385, 215, label4.getPreferredSize().height);

        //======== scrollPane4 ========
        {

            //---- interestArea ----
            interestArea.setBackground(UIManager.getColor("Button.background"));
            interestArea.setEditable(false);
            interestArea.setLineWrap(true);
            interestArea.setWrapStyleWord(true);
            scrollPane4.setViewportView(interestArea);
        }
        contentPane.add(scrollPane4);
        scrollPane4.setBounds(575, 415, 215, 130);

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
    private JTextArea collateralInfoArea;
    private JLabel label2;
    private JButton borrowButton;
    private JButton repayButton;
    private JScrollPane scrollPane3;
    private JTextArea loanArea;
    private JLabel label3;
    private JLabel label4;
    private JScrollPane scrollPane4;
    private JTextArea interestArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
