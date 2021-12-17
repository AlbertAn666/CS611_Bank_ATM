package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ManagerStockService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyStockFrame extends JFrame {
    ManagerStockService managerStockService;
    JPanel panel = new JPanel();
    JLabel label1 = new JLabel("Stock symbol: ");
    JLabel label3 = new JLabel("Stock price: ");
    JTextField symTextField = new JTextField(50);
    JTextField priceTextField = new JTextField(50);
    JButton modifyButton = new JButton("Modify");

    public ModifyStockFrame() {
        managerStockService = new ManagerStockService();
        setSize(900, 1100);
        panel.setLayout(null);
        label1.setBounds(300, 400, 80, 25);
        label3.setBounds(300, 500, 80, 25);
        symTextField.setBounds(400, 400, 200, 25);
        priceTextField.setBounds(400, 500, 200, 25);
        modifyButton.setBounds(400, 600, 100, 25);

        panel.add(label1); panel.add(label3); panel.add(modifyButton);
        panel.add(symTextField); panel.add(priceTextField);
        add(panel);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sym = symTextField.getText();
                String priceStr = priceTextField.getText();
                if(sym.equals("") || priceStr.equals("")) {
                    JOptionPane.showMessageDialog(null, "Invalid input",
                            "Error",JOptionPane.PLAIN_MESSAGE);
                }
                float price = Float.parseFloat(priceTextField.getText());
                GuiManager.getInstance().getManagerStockService().modifyPrice(sym, price);
                JOptionPane.showMessageDialog(null, "Successfully modified",
                        "Success",JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}
