package edu.bu.homeworkteam.bankatm.pagesUI;

import com.mysql.cj.jdbc.SuspendableXAConnection;
import edu.bu.homeworkteam.bankatm.Serviece.ManagerService;
import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * the frame for the admin to operate with stocks
 */
public class StockMarketFrame extends JFrame {
    private int selectedStock = 0;
    ManagerStockService managerService;
    JPanel panel = new JPanel();
    JTable stockTable = new JTable();
    Vector<String> column = new Vector<>();
    JButton addButton = new JButton("Add Stock");
    JButton modifyButton = new JButton("Modify Stock");

    private void openAddStockFrame() {
        new AddStockFrame().setStockMarketFrame(this);
    }

    private void openModifyStockFrame() {
        System.out.println(selectedStock);
       new ModifyStockFrame(selectedStock).setStockMarketFrame(this);
    }

    public void refresh() {
        Vector<Vector<String>> newData = GuiManager.getInstance().getManagerStockService().getStockInfo();
        System.out.println(newData);
        DefaultTableModel model = new DefaultTableModel(newData, column);
        stockTable.setModel(model);
    }

    public StockMarketFrame(Vector<Vector<String>> data) {
        managerService = new ManagerStockService();
        column.add("Id"); column.add("Symbol"); column.add("Name");
        column.add("Currency"); column.add("Price");
        DefaultTableModel model = new DefaultTableModel(data, column);
        stockTable.setModel(model);
        stockTable.setRowSelectionAllowed(true);

        ListSelectionModel selectionModel = stockTable.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setSize(900, 600);
        JScrollPane pane = new JScrollPane(stockTable);
        panel.add(pane);
        panel.add(addButton);
        panel.add(modifyButton);
        add(panel);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String stockId = "";
                int row = stockTable.getSelectedRow();
                stockId = (String)stockTable.getValueAt(row, 0);
                selectedStock = Integer.parseInt(stockId);
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddStockFrame();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedStock != 0) {
                    openModifyStockFrame();
                } else {
                    JOptionPane.showMessageDialog(null, "Didn't select an account!",
                            "ERROR",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
    }
}
