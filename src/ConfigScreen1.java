import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ConfigScreen1 extends JDialog {

    private long openTime, closeTime, idleTime;
    private JTextField openTimeField, closeTimeField, idleTimeField;
    private JButton btnSave, btnCancel;

    public ConfigScreen1(Window owner, long delayTime){
        super(owner);
        openTime=delayTime;
        closeTime=delayTime;
        idleTime=delayTime;
        setResizable(false);
        setLayout(null);
        setBounds(600,200,360,326);
        initialize();
        updateData();
        addActionListeners();
    }

    private void initialize(){
        JLabel background=new JLabel("");
        background.setBounds(0,0,400,326);
        background.setIcon(new ImageIcon("images/configScreen/background.png"));

        JLabel idleLabel=new JLabel("Idle Time (in ms):");
        idleLabel.setBounds(50,52,150,23);
        idleLabel.setForeground(Color.BLACK);
        idleLabel.setFont(new Font("Corbel",Font.PLAIN,20));

        idleTimeField=new JTextField("");
        idleTimeField.setOpaque(false);
        idleTimeField.setEditable(true);
        idleTimeField.setBounds(220,48,74,31);
        idleTimeField.setFont(new Font("Corbel",Font.PLAIN,20));

        JLabel idleBackground=new JLabel("");
        idleBackground.setBounds(220,48,74,31);
        idleBackground.setIcon(new ImageIcon("images/configScreen/inputBackground.png"));

        JLabel openLabel=new JLabel("Open Time (in ms):");
        openLabel.setBounds(50,94,170,23);
        openLabel.setForeground(Color.BLACK);
        openLabel.setFont(new Font("Corbel",Font.PLAIN,20));

        openTimeField=new JTextField("");
        openTimeField.setOpaque(false);
        openTimeField.setEditable(true);
        openTimeField.setBounds(220,91,74,31);
        openTimeField.setFont(new Font("Corbel",Font.PLAIN,20));

        JLabel openBackground=new JLabel("");
        openBackground.setBounds(220,91,74,31);
        openBackground.setIcon(new ImageIcon("images/configScreen/inputBackground.png"));

        JLabel closeLabel=new JLabel("Close Time (in ms):");
        closeLabel.setBounds(50,136,170,23);
        closeLabel.setForeground(Color.BLACK);
        closeLabel.setFont(new Font("Corbel",Font.PLAIN,20));

        closeTimeField=new JTextField("");
        closeTimeField.setOpaque(false);
        closeTimeField.setEditable(true);
        closeTimeField.setBounds(220,133,74,31);
        closeTimeField.setFont(new Font("Corbel",Font.PLAIN,20));

        JLabel closeBackground=new JLabel("");
        closeBackground.setBounds(220,133,74,31);
        closeBackground.setIcon(new ImageIcon("images/configScreen/inputBackground.png"));

        btnSave=new JButton("SAVE");
        btnSave.setForeground(Color.BLACK);
        btnSave.setOpaque(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setBorderPainted(false);
        btnSave.setFont(new Font("Corbel",Font.BOLD,21));
        btnSave.setBounds(60,200,106,47);

        JLabel saveBtnBackground=new JLabel("");
        saveBtnBackground.setBounds(60,200,106,47);
        saveBtnBackground.setIcon(new ImageIcon("images/configScreen/btnBackground.png"));

        btnCancel=new JButton("CANCEL");
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setOpaque(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setFont(new Font("Corbel",Font.BOLD,21));
        btnCancel.setBounds(170,200,120,47);

        JLabel cancelBtnBackground=new JLabel("");
        cancelBtnBackground.setBounds(180,200,106,47);
        cancelBtnBackground.setIcon(new ImageIcon("images/configScreen/btnBackground.png"));

        getContentPane().add(idleLabel);
        getContentPane().add(idleTimeField);
        getContentPane().add(idleBackground);

        getContentPane().add(openLabel);
        getContentPane().add(openTimeField);
        getContentPane().add(openBackground);

        getContentPane().add(closeLabel);
        getContentPane().add(closeTimeField);
        getContentPane().add(closeBackground);

        getContentPane().add(btnSave);
        getContentPane().add(saveBtnBackground);

        getContentPane().add(btnCancel);
        getContentPane().add(cancelBtnBackground);

        getContentPane().add(background);
    }

    private void updateData(){
        openTimeField.setText(Long.toString(openTime));
        closeTimeField.setText(Long.toString(closeTime));
        idleTimeField.setText(Long.toString(idleTime));
    }

    private void addActionListeners(){
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String openInput, closeInput, idleInput;
                openInput=openTimeField.getText();
                closeInput=closeTimeField.getText();
                idleInput=idleTimeField.getText();
                if(openInput.isEmpty() || closeInput.isEmpty() || idleInput.isEmpty())
                    JOptionPane.showMessageDialog(null,"Please Fill all the Fields");
                else {
                    setOpenTime(Long.parseLong(openInput));;
                    setCloseTime(Long.parseLong(closeInput));
                    setIdleTime(Long.parseLong(idleInput));
                    updateData();
                    processWindowEvent(new WindowEvent(ConfigScreen1.this,WindowEvent.WINDOW_CLOSING));
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processWindowEvent(new WindowEvent(ConfigScreen1.this,WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public long getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(long idleTime) {
        this.idleTime = idleTime;
    }
}
