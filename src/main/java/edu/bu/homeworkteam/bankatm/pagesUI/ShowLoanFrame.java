package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ShowLoanFrame extends JFrame{
    JTable table = new JTable();
    Vector<String> column = new Vector<>();

    public ShowLoanFrame(Vector<Vector<String>> data) {
        column.add("Currency"); column.add("Amount");

        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);

        setSize(300, 200);
        JScrollPane pane = new JScrollPane(table);
        add(pane);
    }
}
