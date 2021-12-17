package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;
import edu.bu.homeworkteam.bankatm.entities.Stock;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * the frame for the admin modifying stocks
 */
public class ModifyStockFrame extends JFrame {
    private int stockId;
    JPanel panel = new JPanel();

    JLabel label = new JLabel("Stock price: ");
    JTextField priceTextField = new JTextField(10);
    JButton modifyButton = new JButton("Modify");
    StockMarketFrame stockMarketFrame;

    public void setStockMarketFrame(StockMarketFrame stockMarketFrame) {
        this.stockMarketFrame = stockMarketFrame;
    }

    public ModifyStockFrame(int stockId) {
        this.stockId = stockId;
        setSize(200, 200);
        label.setBounds(50, 80, 50, 25);
        priceTextField.setBounds(100, 80, 50, 25);
        modifyButton.setBounds(70, 120, 60, 25);

        panel.add(label); panel.add(priceTextField);
        panel.add(modifyButton);
        add(panel);
        setVisible(true);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String priceStr = priceTextField.getText();
                if(priceStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid input",
                            "Error",JOptionPane.PLAIN_MESSAGE);
                }
                float price = Float.parseFloat(priceTextField.getText());
                GuiManager.getInstance().getManagerStockService().modifyPrice(stockId, price);
                JOptionPane.showMessageDialog(null, "Successfully modified",
                        "Success",JOptionPane.PLAIN_MESSAGE);
                stockMarketFrame.refresh();
            }
        });
    }
}
