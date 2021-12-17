/*
 * Created by JFormDesigner on Thu Dec 16 22:19:44 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceConfig;
import edu.bu.homeworkteam.bankatm.Serviece.ServiceResult;
import edu.bu.homeworkteam.bankatm.entities.Currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The frame for customer to sell stocks
 * @author gung
 */
public class SellStockFrame extends JFrame {

    private ViewStocksFrame viewStocksFrame;
    public void setViewStocksFrame(ViewStocksFrame viewStocksFrame){
        this.viewStocksFrame=viewStocksFrame;
    }


    public SellStockFrame() {
        initComponents();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ServiceResult serviceResult=null;
                try {

                    int stockId=Integer.parseInt(stockIdField.getText());
                    int numberOfShares=Integer.parseInt(numberField.getText());


                    int customerId = GuiManager.getInstance().getLoggedInCustomerId();

                    serviceResult=GuiManager.getInstance().getCustomerStockService().sellStock(customerId,stockId,numberOfShares);


                }catch (Exception e){

                }

                if(serviceResult==null){
                    new PromptFrame("Selling failed. Please check and try again.");
                }else if(serviceResult.isSuccessful()){
                    new PromptFrame( serviceResult.getMessage());
                    viewStocksFrame.refresh();
                    dispose();
                }else{
                    new PromptFrame(serviceResult.getMessage());
                }
            }
        });


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        stockIdField = new JTextField();
        label1 = new JLabel();
        numberField = new JTextField();
        label2 = new JLabel();
        button = new JButton();

        //======== this ========
        setTitle("Sell Stock");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(stockIdField);
        stockIdField.setBounds(145, 75, 315, stockIdField.getPreferredSize().height);

        //---- label1 ----
        label1.setText("Stock ID");
        contentPane.add(label1);
        label1.setBounds(new Rectangle(new Point(25, 80), label1.getPreferredSize()));
        contentPane.add(numberField);
        numberField.setBounds(145, 115, 315, 26);

        //---- label2 ----
        label2.setText("Number of shares");
        contentPane.add(label2);
        label2.setBounds(25, 120, 125, 16);

        //---- button ----
        button.setText("Sell stock");
        contentPane.add(button);
        button.setBounds(350, 205, 107, button.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }




    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JTextField stockIdField;
    private JLabel label1;
    private JTextField numberField;
    private JLabel label2;
    private JButton button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
