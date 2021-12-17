package edu.bu.homeworkteam.bankatm.pagesUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class TransactionSelectFrame extends JFrame {
    JPanel panel = new JPanel();
    JButton todayTransaction = new JButton("Check Today Transaction");
    JLabel label1 = new JLabel("yyyy: ");
    JLabel label2 = new JLabel("MM: ");
    JLabel label3 = new JLabel("dd: ");
    JTextField yTextField = new JTextField();
    JTextField mTextField = new JTextField();
    JTextField dTextField = new JTextField();
    JButton dateTransaction = new JButton("Check Transaction");

    public TransactionSelectFrame() {
        setSize(800, 500);
        add(panel);
        panel.setLayout(null);

        todayTransaction.setBounds(100, 100, 600, 25);
        label1.setBounds(100, 200, 50, 25);
        yTextField.setBounds(150, 200, 80, 25);
        label2.setBounds(250, 200, 50, 25);
        mTextField.setBounds(300, 200, 80, 25);
        label3.setBounds(400, 200, 50, 25);
        dTextField.setBounds(450, 200, 80, 25);
        dateTransaction.setBounds(550, 200, 150, 25);
        panel.add(todayTransaction);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(yTextField);
        panel.add(mTextField);
        panel.add(dTextField);
        panel.add(dateTransaction);
        panel.add(dateTransaction);
        panel.add(dateTransaction);

        todayTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<String>> data = GuiManager.getInstance().getManagerService().checkUpTodayTransactions();
                ShowTransactionFrame showTransactionFrame = new ShowTransactionFrame(data);
                showTransactionFrame.setVisible(true);
            }
        });

        dateTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = yTextField.getText() + "-" + mTextField.getText() + "-" + dTextField.getText();
                Vector<Vector<String>> data = GuiManager.getInstance().getManagerService().checkUpDailyTransactions(date);
                ShowTransactionFrame showTransactionFrame = new ShowTransactionFrame(data);
                showTransactionFrame.setVisible(true);
            }
        });
    }

}
