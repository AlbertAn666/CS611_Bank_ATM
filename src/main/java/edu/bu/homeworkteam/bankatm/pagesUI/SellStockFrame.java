/*
 * Created by JFormDesigner on Thu Dec 16 22:19:44 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import java.awt.*;

/**
 * The frame for customer to sell stocks
 * @author gung
 */
public class SellStockFrame extends JFrame {
    public SellStockFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        textField1 = new JTextField();
        label1 = new JLabel();
        textField2 = new JTextField();
        label2 = new JLabel();
        button1 = new JButton();

        //======== this ========
        setTitle("Sell Stock");
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(textField1);
        textField1.setBounds(145, 75, 315, textField1.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Stock ID");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 80), label1.getPreferredSize()));
        contentPane.add(textField2);
        textField2.setBounds(145, 115, 315, 26);

        //---- label2 ----
        label2.setText("Number of shares");
        contentPane.add(label2);
        label2.setBounds(25, 120, 125, 16);

        //---- button1 ----
        button1.setText("Sell stock");
        contentPane.add(button1);
        button1.setBounds(350, 205, 107, button1.getPreferredSize().height);

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
    private JTextField textField2;
    private JLabel label2;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
