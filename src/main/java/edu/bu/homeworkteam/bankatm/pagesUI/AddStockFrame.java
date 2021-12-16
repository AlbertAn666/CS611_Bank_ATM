package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStockFrame extends JFrame {
    ManagerStockService managerStockService;
    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Stock symbol: ");
    JLabel label2 = new JLabel("Stock name: ");
    JLabel label3 = new JLabel("Stock price: ");
    JTextField symTextField = new JTextField(50);
    JTextField nameTextField = new JTextField(50);
    JTextField priceTextField = new JTextField(50);
    JButton addButton = new JButton("Add");

    public AddStockFrame() {
        managerStockService = new ManagerStockService();
        setLocation(1500, 400);
        setSize(900, 1100);
        panel.setLayout(null);
        label1.setBounds(300, 400, 80, 25);
        label2.setBounds(300, 450, 80, 25);
        label3.setBounds(300, 500, 80, 25);
        symTextField.setBounds(400, 400, 200, 25);
        nameTextField.setBounds(400, 450, 200, 25);
        priceTextField.setBounds(400, 500, 200, 25);
        addButton.setBounds(400, 600, 100, 25);

        panel.add(label1); panel.add(label2); panel.add(label3); panel.add(addButton);
        panel.add(symTextField); panel.add(nameTextField); panel.add(priceTextField);
        add(panel);

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
                managerStockService.addStock(sym, name, price);
                JOptionPane.showMessageDialog(null, "Successfully added",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
