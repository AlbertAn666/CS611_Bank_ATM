/*
 * Created by JFormDesigner on Thu Dec 16 22:36:58 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * frame for customer to borrow loan
 * @author gung
 */
public class BorrowLoanFrame extends JFrame {
    public BorrowLoanFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        comboBox1 = new JComboBox();
        label4 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Borrow Loan");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(125, 85, 385, 30);

        //---- label1 ----
        label1.setText("Collateral name");
        contentPane.add(label1);
        label1.setBounds(10, 85, 105, 30);

        //---- label2 ----
        label2.setText("Loan amount");
        contentPane.add(label2);
        label2.setBounds(30, 180, 85, 30);
        contentPane.add(textField2);
        textField2.setBounds(125, 180, 155, 30);

        //---- label3 ----
        label3.setText("Currency");
        contentPane.add(label3);
        label3.setBounds(35, 130, 70, 30);
        contentPane.add(comboBox1);
        comboBox1.setBounds(125, 135, 155, comboBox1.getPreferredSize().height);

        //---- label4 ----
        label4.setText("Account ID");
        contentPane.add(label4);
        label4.setBounds(30, 230, 85, 30);
        contentPane.add(textField3);
        textField3.setBounds(125, 230, 155, 30);

        //---- button1 ----
        button1.setText("Confrim");
        contentPane.add(button1);
        button1.setBounds(370, 305, 155, 35);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JComboBox comboBox1;
    private JLabel label4;
    private JTextField textField3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
