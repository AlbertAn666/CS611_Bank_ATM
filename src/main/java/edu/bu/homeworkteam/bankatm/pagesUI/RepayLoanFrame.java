/*
 * Created by JFormDesigner on Thu Dec 16 22:36:58 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import java.awt.*;

/**
 * the frame for customer to repay the loan
 * @author gung
 */
public class RepayLoanFrame extends JFrame {
    public RepayLoanFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        textField1 = new JTextField();
        label1 = new JLabel();
        label4 = new JLabel();
        textField3 = new JTextField();
        button1 = new JButton();

        //======== this ========
        setTitle("Repay Loan");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(125, 85, 385, 30);

        //---- label1 ----
        label1.setText("Collateral ID");
        contentPane.add(label1);
        label1.setBounds(15, 85, 105, 30);

        //---- label4 ----
        label4.setText("Account ID");
        contentPane.add(label4);
        label4.setBounds(30, 125, 85, 30);
        contentPane.add(textField3);
        textField3.setBounds(125, 125, 385, 30);

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
    private JLabel label4;
    private JTextField textField3;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
