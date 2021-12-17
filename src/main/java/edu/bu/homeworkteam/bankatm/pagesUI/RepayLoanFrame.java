/*
 * Created by JFormDesigner on Thu Dec 16 22:36:58 EST 2021
 */

package edu.bu.homeworkteam.bankatm.pagesUI;

import edu.bu.homeworkteam.bankatm.Serviece.ServiceResult;
import edu.bu.homeworkteam.bankatm.entities.Currency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * the frame for customer to repay the loan
 * @author gung
 */
public class RepayLoanFrame extends JFrame {
    private ViewCollateralFrame viewCollateralFrame;
    public void setViewCollateralFrame(ViewCollateralFrame viewCollateralFrame){
        this.viewCollateralFrame =viewCollateralFrame;
    }
    public RepayLoanFrame() {
        initComponents();


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ServiceResult serviceResult=null;
                try {

                    int accountId=Integer.parseInt(accountIdField.getText());
                    int collateralId=Integer.parseInt(collateralIdField.getText());
                    int customerId = GuiManager.getInstance().getLoggedInCustomerId();

                    serviceResult=GuiManager.getInstance().getCustomerService().removeCollateral(customerId,collateralId,accountId);

                }catch (Exception e){

                }

                if(serviceResult== null){
                    new PromptFrame("Repay failed. Please check and try again.");
                }else if(serviceResult.isSuccessful()){
                    new PromptFrame("Repay successful.");
                    viewCollateralFrame.refresh();
                    dispose();
                }else {
                    new PromptFrame("Repay failed. "+serviceResult.getMessage());
                }
            }
        });
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        collateralIdField = new JTextField();
        label1 = new JLabel();
        label4 = new JLabel();
        accountIdField = new JTextField();
        button = new JButton();

        //======== this ========
        setTitle("Repay Loan");
        setVisible(true);
        var contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(collateralIdField);
        collateralIdField.setBounds(125, 85, 385, 30);

        //---- label1 ----
        label1.setText("Collateral ID");
        contentPane.add(label1);
        label1.setBounds(25, 85, 105, 30);

        //---- label4 ----
        label4.setText("Account ID");
        contentPane.add(label4);
        label4.setBounds(30, 125, 85, 30);
        contentPane.add(accountIdField);
        accountIdField.setBounds(125, 125, 385, 30);

        //---- button ----
        button.setText("Confrim");
        contentPane.add(button);
        button.setBounds(370, 305, 155, 35);

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
    private JTextField collateralIdField;
    private JLabel label1;
    private JLabel label4;
    private JTextField accountIdField;
    private JButton button;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
