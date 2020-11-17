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
    public ConfigScreen(Window owner, long delayTime) {
        super(owner);
        this.CloseTime=delayTime;
        this.openTime=delayTime;
        setResizable(false);
        initComponents();
    }

    private long openTime,CloseTime;


    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public long getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(long closeTime) {
        CloseTime = closeTime;
    }

    private void btnSaveActionPerformed(ActionEvent e) {
        // TODO add your code here
        String input1, input2;
        input1=openTimeInput.getText();
        if(!input1.isEmpty()){
            setOpenTime(Long.parseLong(input1));
        }
        input2=closeTimeInput.getText();
        if(!input2.isEmpty()){
            setCloseTime(Long.parseLong(input2));
        }
        processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    private void btnCancelActionPerformed(ActionEvent e) {
        // TODO add your code here
        processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Varun Tiwari
        openTimeInputLabel = new JLabel();
        openTimeInput = new JTextField();
        btnSave = new JButton();
        btnCancel = new JButton();
        closeTimeInputLable = new JLabel();
        closeTimeInput = new JTextField();

        //======== this ========
        setBackground(Color.white);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- openTimeInputLabel ----
        openTimeInputLabel.setText("Door Open Time (in Milliseconds):");
        openTimeInputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        openTimeInputLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
        contentPane.add(openTimeInputLabel);
        openTimeInputLabel.setBounds(new Rectangle(new Point(40, 45), openTimeInputLabel.getPreferredSize()));

        //---- openTimeInput ----
        openTimeInput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        openTimeInput.setBackground(new Color(208, 223, 240));
        contentPane.add(openTimeInput);
        openTimeInput.setBounds(290, 40, 99, openTimeInput.getPreferredSize().height);

        //---- btnSave ----
        btnSave.setText("SAVE");
        btnSave.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnSave.setBackground(new Color(208, 223, 240));
        btnSave.addActionListener(e -> btnSaveActionPerformed(e));
        contentPane.add(btnSave);
        btnSave.setBounds(new Rectangle(new Point(125, 115), btnSave.getPreferredSize()));

        //---- btnCancel ----
        btnCancel.setText("CANCEL");
        btnCancel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnCancel.setBackground(new Color(208, 223, 240));
        btnCancel.addActionListener(e -> btnCancelActionPerformed(e));
        contentPane.add(btnCancel);
        btnCancel.setBounds(new Rectangle(new Point(245, 115), btnCancel.getPreferredSize()));

        //---- closeTimeInputLable ----
        closeTimeInputLable.setText("Door Close Time (in Milliseconds:");
        closeTimeInputLable.setHorizontalAlignment(SwingConstants.CENTER);
        closeTimeInputLable.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
        contentPane.add(closeTimeInputLable);
        closeTimeInputLable.setBounds(40, 80, 235, 22);

        //---- closeTimeInput ----
        closeTimeInput.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        closeTimeInput.setBackground(new Color(208, 223, 240));
        contentPane.add(closeTimeInput);
        closeTimeInput.setBounds(290, 75, 99, 32);

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
        setSize(425, 210);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Varun Tiwari
    private JLabel openTimeInputLabel;
    private JTextField openTimeInput;
    private JButton btnSave;
    private JButton btnCancel;
    private JLabel closeTimeInputLable;
    private JTextField closeTimeInput;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
