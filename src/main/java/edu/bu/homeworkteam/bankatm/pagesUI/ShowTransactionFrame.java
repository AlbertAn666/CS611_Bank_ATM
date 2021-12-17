package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ShowTransactionFrame extends JFrame {
    JScrollPane pane = new JScrollPane();
    JTable table = new JTable();
    Vector<String> column = new Vector<>();

    public ShowTransactionFrame(Vector<Vector<String>> data) {
        column.add("Id"); column.add("Time"); column.add("Type"); column.add("Source");
        column.add("Destination"); column.add("Currency"); column.add("Amount");
        System.out.println(data);

        DefaultTableModel model = new DefaultTableModel(data, column);
        table.setModel(model);

        setSize(1100, 800);
        table.setBounds(10, 10, 1080, 790);
        pane.add(table);
        add(pane);
    }
}
