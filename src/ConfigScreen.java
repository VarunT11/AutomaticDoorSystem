import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.logging.Handler;
import javax.swing.*;
/*
 * Created by JFormDesigner on Mon Nov 02 09:37:55 IST 2020
 */



/**
 * @author Varun Tiwari
 */
public class ConfigScreen extends JDialog {
    public ConfigScreen(Window owner, float delayTime) {
        super(owner);
        this.delayTime=delayTime;
        setResizable(false);
        initComponents();
        delayInput.setText(Float.toString(delayTime));
    }

    private float delayTime;

    public float getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(float delayTime) {
        this.delayTime = delayTime;
    }

    private void btnSaveActionPerformed(ActionEvent e) {
        // TODO add your code here
        String inputString=delayInput.getText();
        if(!inputString.isEmpty()){
            float data=Float.parseFloat(inputString);
            if(!Float.isNaN(data)){
                setDelayTime(data);
                processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
            }
        }
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        // TODO add your code here
        processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Varun Tiwari
        inputLabel = new JLabel();
        delayInput = new JTextField();
        btnSave = new JButton();
        btnCancel = new JButton();

        //======== this ========
        setBackground(Color.white);
        var contentPane = getContentPane();

        //---- inputLabel ----
        inputLabel.setText("Delay Time:");
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));

        //---- delayInput ----
        delayInput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        delayInput.setBackground(new Color(208, 223, 240));

        //---- btnSave ----
        btnSave.setText("SAVE");
        btnSave.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnSave.setBackground(new Color(208, 223, 240));
        btnSave.addActionListener(e -> btnSaveActionPerformed(e));

        //---- btnCancel ----
        btnCancel.setText("CANCEL");
        btnCancel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnCancel.setBackground(new Color(208, 223, 240));
        btnCancel.addActionListener(e -> btnCancelActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addComponent(inputLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(delayInput, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.LEADING, contentPaneLayout.createSequentialGroup()
                            .addComponent(btnSave)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnCancel)))
                    .addGap(40, 40, 40))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(inputLabel)
                        .addComponent(delayInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSave)
                        .addComponent(btnCancel))
                    .addGap(40, 40, 40))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Varun Tiwari
    private JLabel inputLabel;
    private JTextField delayInput;
    private JButton btnSave;
    private JButton btnCancel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
