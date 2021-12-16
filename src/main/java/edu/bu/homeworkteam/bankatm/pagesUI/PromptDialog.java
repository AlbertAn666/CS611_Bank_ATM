/*
 * Created by JFormDesigner on Thu Dec 16 14:21:40 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import java.awt.*;
import javax.swing.*;

/**
 * @author unknown
 */
public class PromptDialog extends JDialog {
    public PromptDialog(Window owner) {
        super(owner);
        initComponents();
    }

    public PromptDialog(Window window, String prompt){
        this(window);
        label1.setText(prompt);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();

        //======== this ========
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("text");
        contentPane.add(label1);
        label1.setBounds(35, 15, 410, 70);

        contentPane.setPreferredSize(new Dimension(475, 135));
        setSize(475, 135);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
