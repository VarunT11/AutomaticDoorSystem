import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
/*
 * Created by JFormDesigner on Sun Nov 01 14:41:28 IST 2020
 */



/**
 * @author Varun Tiwari
 */
public class MainScreen extends JFrame {

    private boolean automaticStart;
    private final float defaultDelay=2;
    private ConfigScreen configScreen;
    private DoorState currentState, previousState;
    private ImagePanel imagePanel;

    public boolean isAutomaticStart() {
        return automaticStart;
    }

    public void setAutomaticStart(boolean automaticStart) {
        this.automaticStart = automaticStart;
        if(automaticStart)
            automaticStatus.setText("ON");
        else
            automaticStatus.setText("OFF");
    }

    public MainScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        imagePanel=new ImagePanel();
        initComponents();
        currentState=DoorState.Closed;
        previousState=DoorState.Closed;
        configScreen=new ConfigScreen(getOwner(),defaultDelay);
        addPersonInput();
        updateDetailsPanel();
        setAutomaticStart(false);
    }

    private void updateDetailsPanel(){
        currentStatus.setText(currentState.toString());
        previousStatus.setText(previousState.toString());
    }

    private void addPersonInput(){
        for(PersonInput input:PersonInput.values())
            inputPerson.addItem(input);
    }

    private void btnQuitActionPerformed(ActionEvent e) {
        // TODO add your code here
        processWindowEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
    }

    private void inputPersonItemStateChanged(ItemEvent e) {
        // TODO add your code here

    }

    private void btnStartActionPerformed(ActionEvent e) {
        // TODO add your code here
        setAutomaticStart(true);
    }

    private void btnOffActionPerformed(ActionEvent e) {
        // TODO add your code here
        setAutomaticStart(false);
    }

    private void btnChangeConfigActionPerformed(ActionEvent e) {
        // TODO add your code here
        configScreen.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Varun Tiwari
        mainLabel = new JLabel();
        btnStart = new JButton();
        btnOff = new JButton();
        btnChangeConfig = new JButton();
        displayPanel = new JPanel();
        displayLabel = new JLabel();
        currentLabel = new JLabel();
        currentStatus = new JTextField();
        previousLabel = new JLabel();
        previousStatus = new JTextField();
        btnEmergOpen = new JButton();
        btnEmergClose = new JButton();
        btnQuit = new JButton();
        inputLabel = new JLabel();
        inputPerson = new JComboBox();
        statusLabel = new JLabel();
        automaticStatus = new JTextField();

        //======== this ========
        setForeground(new Color(238, 238, 238));
        var contentPane = getContentPane();

        //---- mainLabel ----
        mainLabel.setText("Automatic Door System");
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));

        //---- btnStart ----
        btnStart.setText("START");
        btnStart.setBackground(new Color(208, 223, 240));
        btnStart.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnStart.addActionListener(e -> btnStartActionPerformed(e));

        //---- btnOff ----
        btnOff.setText("OFF");
        btnOff.setBackground(new Color(208, 223, 240));
        btnOff.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnOff.addActionListener(e -> btnOffActionPerformed(e));

        //---- btnChangeConfig ----
        btnChangeConfig.setText("Change Configuration");
        btnChangeConfig.setBackground(new Color(208, 223, 240));
        btnChangeConfig.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnChangeConfig.addActionListener(e -> btnChangeConfigActionPerformed(e));

        //======== displayPanel ========
        {
            displayPanel.setBackground(Color.white);

            //---- displayLabel ----
            displayLabel.setText("DISPLAY PANEL");
            displayLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));
            displayLabel.setHorizontalAlignment(SwingConstants.CENTER);

            //---- currentLabel ----
            currentLabel.setText("Current Status :");
            currentLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));

            //---- currentStatus ----
            currentStatus.setBackground(new Color(208, 223, 240));
            currentStatus.setEditable(false);
            currentStatus.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));

            //---- previousLabel ----
            previousLabel.setText("Previous Status:");
            previousLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));

            //---- previousStatus ----
            previousStatus.setBackground(new Color(208, 223, 240));
            previousStatus.setEditable(false);
            previousStatus.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));

            GroupLayout displayPanelLayout = new GroupLayout(displayPanel);
            displayPanel.setLayout(displayPanelLayout);
            displayPanelLayout.setHorizontalGroup(
                displayPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(displayPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(previousLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(displayLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentStatus)
                            .addComponent(previousStatus, GroupLayout.Alignment.LEADING)
                            .addComponent(currentLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
            );
            displayPanelLayout.setVerticalGroup(
                displayPanelLayout.createParallelGroup()
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(displayLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(previousLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(previousStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
            );
        }

        //---- btnEmergOpen ----
        btnEmergOpen.setText("Emergency Open");
        btnEmergOpen.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
        btnEmergOpen.setBackground(new Color(208, 223, 240));

        //---- btnEmergClose ----
        btnEmergClose.setText("Emergency Close");
        btnEmergClose.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
        btnEmergClose.setBackground(new Color(208, 223, 240));

        //---- btnQuit ----
        btnQuit.setText("Quit");
        btnQuit.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        btnQuit.setBackground(new Color(208, 223, 240));
        btnQuit.addActionListener(e -> btnQuitActionPerformed(e));

        //---- inputLabel ----
        inputLabel.setText("Person Input:");
        inputLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 18));

        //---- inputPerson ----
        inputPerson.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
        inputPerson.setBackground(new Color(208, 223, 240));
        inputPerson.addItemListener(e -> inputPersonItemStateChanged(e));

        //---- statusLabel ----
        statusLabel.setText("Automatic Status");
        statusLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));

        //---- automaticStatus ----
        automaticStatus.setFont(new Font("Tempus Sans ITC", Font.BOLD, 16));
        automaticStatus.setBackground(new Color(208, 223, 240));
        automaticStatus.setEditable(false);
        automaticStatus.setHorizontalAlignment(SwingConstants.CENTER);

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(mainLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnQuit))
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addComponent(statusLabel)
                                        .addComponent(btnChangeConfig))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(btnStart)
                                            .addGap(39, 39, 39)
                                            .addComponent(btnOff)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(inputLabel)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(inputPerson))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                            .addComponent(automaticStatus, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                                            .addGap(319, 319, 319)))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(btnEmergOpen)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnEmergClose))
                                .addComponent(displayPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(mainLabel)
                    .addGap(18, 18, 18)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnStart)
                                .addComponent(btnOff))
                            .addGap(18, 18, 18)
                            .addComponent(statusLabel)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(automaticStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChangeConfig))
                        .addComponent(displayPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(inputLabel)
                        .addComponent(inputPerson, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEmergOpen)
                        .addComponent(btnEmergClose))
                    .addGap(18, 18, 18)
                    .addComponent(btnQuit)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Varun Tiwari
    private JLabel mainLabel;
    private JButton btnStart;
    private JButton btnOff;
    private JButton btnChangeConfig;
    private JPanel displayPanel;
    private JLabel displayLabel;
    private JLabel currentLabel;
    private JTextField currentStatus;
    private JLabel previousLabel;
    private JTextField previousStatus;
    private JButton btnEmergOpen;
    private JButton btnEmergClose;
    private JButton btnQuit;
    private JLabel inputLabel;
    private JComboBox inputPerson;
    private JLabel statusLabel;
    private JTextField automaticStatus;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
