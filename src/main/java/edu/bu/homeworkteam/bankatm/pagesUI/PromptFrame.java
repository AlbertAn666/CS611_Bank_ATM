/*
 * Created by JFormDesigner on Thu Dec 16 19:18:30 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class PromptFrame extends JFrame {
    public PromptFrame() {
        initComponents();
    }

    public PromptFrame(String prompt){
        this();
        label.setText(prompt);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label = new JLabel();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label ----
        label.setText("text");
        contentPane.add(label);
        label.setBounds(45, 10, 300, 80);

        contentPane.setPreferredSize(new Dimension(430, 135));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
