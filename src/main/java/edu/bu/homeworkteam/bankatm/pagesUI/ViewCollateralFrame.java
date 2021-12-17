/*
 * Created by JFormDesigner on Thu Dec 16 22:29:47 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author gung
 */
public class ViewCollateralFrame extends JFrame {
    public ViewCollateralFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        scrollPane1 = new JScrollPane();
        list1 = new JList();
        label1 = new JLabel();
        scrollPane2 = new JScrollPane();
        textArea1 = new JTextArea();
        label2 = new JLabel();
        label3 = new JLabel();
        button1 = new JButton();
        button2 = new JButton();

        //======== this ========
        setTitle("Collateral");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(list1);
        }
        contentPane.add(scrollPane1);
        scrollPane1.setBounds(15, 35, 285, 560);

        //---- label1 ----
        label1.setText("Collateral");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(15, 10), label1.getPreferredSize()));

        //======== scrollPane2 ========
        {

            //---- textArea1 ----
            textArea1.setBackground(UIManager.getColor("CheckBox.background"));
            scrollPane2.setViewportView(textArea1);
        }
        contentPane.add(scrollPane2);
        scrollPane2.setBounds(320, 35, 475, 335);

        //---- label2 ----
        label2.setText("Collateral Information");
        contentPane.add(label2);
        label2.setBounds(320, 10, 225, 16);

        //---- label3 ----
        label3.setText("Loan summary");
        contentPane.add(label3);
        label3.setBounds(320, 460, 225, 16);

        //---- button1 ----
        button1.setText("borrow loan");
        contentPane.add(button1);
        button1.setBounds(485, 555, 150, 30);

        //---- button2 ----
        button2.setText("return loan");
        contentPane.add(button2);
        button2.setBounds(640, 555, 150, 30);

        contentPane.setPreferredSize(new Dimension(815, 630));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JScrollPane scrollPane1;
    private JList list1;
    private JLabel label1;
    private JScrollPane scrollPane2;
    private JTextArea textArea1;
    private JLabel label2;
    private JLabel label3;
    private JButton button1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
