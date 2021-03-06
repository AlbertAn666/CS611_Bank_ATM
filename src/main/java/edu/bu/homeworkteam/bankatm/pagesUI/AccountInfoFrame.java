package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

/**
 * Frame to show the account information
 */
public class AccountInfoFrame extends JFrame {
    JPanel panel = new JPanel();
    JTable table = new JTable();
    Vector<String> column =  new Vector<>();

    public AccountInfoFrame(Vector<Vector<String>> data) {
        setSize(500, 200);
        column.add("Currency"); column.add("Amount");

        System.out.println(data);
        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);

        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);
        add(panel);
    }
}
