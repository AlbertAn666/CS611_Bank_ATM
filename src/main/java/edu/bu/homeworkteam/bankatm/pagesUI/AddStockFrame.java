package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;
import edu.bu.homeworkteam.bankatm.entities.Currency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * frame for admin to add stock
 */
public class AddStockFrame extends JFrame {
    ManagerStockService managerStockService;
    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Stock symbol: ");
    JLabel label2 = new JLabel("Stock name: ");
    JLabel label3 = new JLabel("Stock price: ");
    JLabel label4 = new JLabel("Stock currency: ");
    JTextField symTextField = new JTextField(50);
    JTextField nameTextField = new JTextField(50);
    JTextField priceTextField = new JTextField(50);
    JComboBox<Currency> currencyComboBox = new JComboBox<>();
    JButton addButton = new JButton("Add");
    private StockMarketFrame stockMarketFrame;

    public void setStockMarketFrame(StockMarketFrame stockMarketFrame) {
        this.stockMarketFrame = stockMarketFrame;
    }

    public AddStockFrame() {
        managerStockService = new ManagerStockService();
        setSize(400, 500);
        panel.setLayout(null);
        label1.setBounds(100, 100, 100, 25);
        label2.setBounds(100, 150, 100, 25);
        label3.setBounds(100, 200, 100, 25);
        label4.setBounds(100, 250, 100, 25);
        symTextField.setBounds(220, 100, 100, 25);
        nameTextField.setBounds(220, 150, 100, 25);
        priceTextField.setBounds(220, 200, 100, 25);
        currencyComboBox.setBounds(220, 250, 100, 25);
        addButton.setBounds(150, 350, 100, 25);
        DefaultComboBoxModel<Currency> currencyDefaultComboBoxModel =
                new DefaultComboBoxModel<>(Currency.values());
        currencyComboBox.setModel(currencyDefaultComboBoxModel);

        panel.add(label1); panel.add(label2); panel.add(label3); panel.add(addButton);
        panel.add(symTextField); panel.add(nameTextField); panel.add(priceTextField);
        panel.add(label4);panel.add(currencyComboBox);
        add(panel);
        setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sym = symTextField.getText();
                String name = nameTextField.getText();
                String priceStr = priceTextField.getText();
                if(sym.equals("") || name.equals("") || priceStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid input",
                            "Error",JOptionPane.PLAIN_MESSAGE);
                }
                float price = Float.parseFloat(priceTextField.getText());
                Currency currency = (Currency) currencyComboBox.getSelectedItem();
                GuiManager.getInstance().getManagerStockService().addStock(sym, name, price, currency);
                JOptionPane.showMessageDialog(null, "Successfully added",
                        "Success",JOptionPane.PLAIN_MESSAGE);
                stockMarketFrame.refresh();
            }
        });
    }
}
