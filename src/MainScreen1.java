import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen1 extends JFrame implements DataListenerInterface, RandomInputInterface, PersonInputInterface {

    private boolean automaticStart, waitingForStop;
    private JTextField currentStatusField, previousStatusField, automaticStatusField, currentInputField, numApproachedField;
    private JButton btnConfig, btnQuit, btnSendEmail, btnStart, btnStop, btnEmergOpen, btnEmergClose;
    private ConfigScreen1 configScreen;
    private DoorState currentState, previousState;
    private ControlSystem controlSystem;
    private SendEmail sendEmail;
    private RandomInputGenerator randomInputGenerator;
    private ArrayList<Person> personList;
    private int personActive, stoppingNum;

    public MainScreen1(){
        System.out.println(new Date().toString()+" : Starting Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setResizable(false);
        setBounds(200,100,1149,620);
        initialize();
        addActionListeners();

        currentState=DoorState.Closed;
        previousState=DoorState.Closed;
        currentStatusField.setText(currentState.toString());
        previousStatusField.setText(previousState.toString());

        configScreen=new ConfigScreen1(getOwner(),3000);
        controlSystem=new ControlSystem(this,configScreen.getOpenTime(),configScreen.getCloseTime());
        sendEmail=new SendEmail();
        randomInputGenerator=new RandomInputGenerator(this,5000);

        automaticStatusField.setText("OFF");
    }

    public boolean isAutomaticStart() {
        return automaticStart;
    }

    public void setAutomaticStart(boolean automaticStart) {
        this.automaticStart = automaticStart;
        if(automaticStart){
            System.out.println(new Date().toString()+" : Starting Automatic Status");
            waitingForStop=false;
            automaticStatusField.setText("ON");
            controlSystem.setDoorOpenTime(configScreen.getOpenTime());
            controlSystem.setDoorCloseTime(configScreen.getCloseTime());
            controlSystem.setDoorIdleTime(configScreen.getIdleTime());
            personList=new ArrayList<>();
            personActive=0;
            currentInputField.setText("No Input Detected");
            numApproachedField.setText("0");
            randomInputGenerator.startRandomGeneration();
        }
        else {
            System.out.println(new Date().toString()+" : Stopping Automatic Status");
            automaticStatusField.setText("OFF");
            randomInputGenerator.stopRandomGeneration();
        }
    }

    private void initialize(){
        JLabel background=new JLabel("");
        background.setBounds(0,0,1149,590);
        background.setIcon(new ImageIcon("images/mainScreen/background.png"));

        JLabel displayPanelBackground=new JLabel("");
        displayPanelBackground.setBounds(88,148,297,230);
        displayPanelBackground.setIcon(new ImageIcon("images/mainScreen/displayPanel/background.png"));

        JLabel emailQueryBackground=new JLabel("");
        emailQueryBackground.setBounds(948,453,175,118);
        emailQueryBackground.setIcon(new ImageIcon("images/mainScreen/emailQuery/background.png"));

        JLabel displayPanelHeading = new JLabel("DISPLAY PANEL");
        displayPanelHeading.setFont(new Font("Corbel",Font.PLAIN,24));
        displayPanelHeading.setBounds(150,162,189,33);

        JLabel displayPanelLabel1 = new JLabel("Current Status:");
        displayPanelLabel1.setFont(new Font("Corbel",Font.PLAIN,18));
        displayPanelLabel1.setBounds(105,204,123,21);

        JLabel displayPanelLabel2 = new JLabel("Previous Status:");
        displayPanelLabel2.setFont(new Font("Corbel",Font.PLAIN,18));
        displayPanelLabel2.setBounds(105,287,123,21);

        currentStatusField=new JTextField("");
        currentStatusField.setEditable(false);
        currentStatusField.setOpaque(false);
        currentStatusField.setFont(new Font("Corbel",Font.BOLD,18));
        currentStatusField.setBounds(103,232,263,42);

        JLabel displayPanelInputRectangle1=new JLabel("");
        displayPanelInputRectangle1.setBounds(103,232,263,42);
        displayPanelInputRectangle1.setIcon(new ImageIcon("images/mainScreen/displayPanel/statusBackground.png"));

        previousStatusField=new JTextField("");
        previousStatusField.setEditable(false);
        previousStatusField.setOpaque(false);
        previousStatusField.setFont(new Font("Corbel",Font.BOLD,18));
        previousStatusField.setBounds(103,309,263,42);

        JLabel displayPanelInputRectangle2=new JLabel("");
        displayPanelInputRectangle2.setBounds(103,309,263,42);
        displayPanelInputRectangle2.setIcon(new ImageIcon("images/mainScreen/displayPanel/statusBackground.png"));

        btnConfig=new JButton("Update Configuration");
        btnConfig.setBounds(88,476,285,43);
        btnConfig.setOpaque(false);
        btnConfig.setBorderPainted(false);
        btnConfig.setContentAreaFilled(false);
        btnConfig.setFont(new Font("Corbel",Font.BOLD,22));

        JLabel configBtnBackground=new JLabel("");
        configBtnBackground.setBounds(88,476,285,43);
        configBtnBackground.setIcon(new ImageIcon("images/mainScreen/configBtnBack.png"));

        btnQuit=new JButton("QUIT");
        btnQuit.setBounds(30,542,94,30);
        btnQuit.setOpaque(false);
        btnQuit.setBorderPainted(false);
        btnQuit.setContentAreaFilled(false);
        btnConfig.setFont(new Font("Corbel",Font.BOLD,20));

        JLabel quitBtnBackground=new JLabel("");
        quitBtnBackground.setBounds(33,546,94,30);
        quitBtnBackground.setIcon(new ImageIcon("images/mainScreen/btnQuitBack.png"));

        JLabel emailHeadLabel=new JLabel("Any Queries?");
        emailHeadLabel.setFont(new Font("Corbel",Font.PLAIN,24));
        emailHeadLabel.setBounds(966,465,157,33);

        btnSendEmail=new JButton("Email Us");
        btnSendEmail.setBounds(965,516,145,43);
        btnSendEmail.setOpaque(false);
        btnSendEmail.setBorderPainted(false);
        btnSendEmail.setContentAreaFilled(false);
        btnSendEmail.setFont(new Font("Corbel",Font.BOLD,24));

        JLabel emailBtnBack=new JLabel("");
        emailBtnBack.setBounds(965,516,145,43);
        emailBtnBack.setIcon(new ImageIcon("images/mainScreen/emailQuery/btnMailBack.png"));

        btnStart=new JButton("START");
        btnStart.setBounds(88,42,97,22);
        btnStart.setOpaque(false);
        btnStart.setBorderPainted(false);
        btnStart.setContentAreaFilled(false);
        btnStart.setFont(new Font("Corbel",Font.BOLD,20));

        JLabel startBtnBackground=new JLabel("");
        startBtnBackground.setBounds(88,39,97,29);
        startBtnBackground.setIcon(new ImageIcon("images/mainScreen/startBtnBack.png"));

        btnStop=new JButton("STOP");
        btnStop.setBounds(227,42,97,22);
        btnStop.setOpaque(false);
        btnStop.setBorderPainted(false);
        btnStop.setContentAreaFilled(false);
        btnStop.setFont(new Font("Corbel",Font.BOLD,20));

        JLabel stopBtnBackground=new JLabel("");
        stopBtnBackground.setBounds(227,39,97,29);
        stopBtnBackground.setIcon(new ImageIcon("images/mainScreen/stopBtnBack.png"));

        JLabel cc3Label1=new JLabel("CC -");
        cc3Label1.setBounds(753,32,89,53);
        cc3Label1.setFont(new Font("Corbel",Font.PLAIN,40));
        cc3Label1.setForeground(Color.BLACK);

        JLabel cc3Label2=new JLabel("3");
        cc3Label2.setBounds(830,24,24,51);
        cc3Label2.setFont(new Font("Corbel",Font.PLAIN,40));
        cc3Label2.setForeground(Color.BLACK);

        JLabel automaticStatusLabel=new JLabel("Automatic Status:");
        automaticStatusLabel.setFont(new Font("Corbel",Font.PLAIN,18));
        automaticStatusLabel.setForeground(Color.BLACK);
        automaticStatusLabel.setBounds(700,97,141,21);

        automaticStatusField=new JTextField("OFF");
        automaticStatusField.setOpaque(false);
        automaticStatusField.setEditable(false);
        automaticStatusField.setBounds(850,93,52,27);
        automaticStatusField.setFont(new Font("Corbel",Font.BOLD,18));
        automaticStatusField.setHorizontalAlignment(JTextField.CENTER);

        JLabel automaticStatusBackground=new JLabel();
        automaticStatusBackground.setBounds(850,93,52,27);
        automaticStatusBackground.setIcon(new ImageIcon("images/mainScreen/door/automaticStatusBack.png"));

        btnEmergOpen=new JButton("<html><center>Emergency<br>Open</center></html>");
        btnEmergOpen.setOpaque(false);
        btnEmergOpen.setContentAreaFilled(false);
        btnEmergOpen.setBorderPainted(false);
        btnEmergOpen.setFont(new Font("Corbel",Font.BOLD,15));
        btnEmergOpen.setForeground(Color.WHITE);
        btnEmergOpen.setBounds(1021,150,101,156);

        JLabel emergOpenBackground=new JLabel("");
        emergOpenBackground.setBounds(1021,150,101,156);
        emergOpenBackground.setIcon(new ImageIcon("images/mainScreen/door/btnEmergBack.png"));

        btnEmergClose=new JButton("<html><center>Emergency<br>Close</center></html>");
        btnEmergClose.setOpaque(false);
        btnEmergClose.setContentAreaFilled(false);
        btnEmergClose.setBorderPainted(false);
        btnEmergClose.setFont(new Font("Corbel",Font.BOLD,15));
        btnEmergClose.setForeground(Color.WHITE);
        btnEmergClose.setBounds(1021,220,101,156);

        JLabel emergCloseBackground=new JLabel("");
        emergCloseBackground.setBounds(1021,220,101,156);
        emergCloseBackground.setIcon(new ImageIcon("images/mainScreen/door/btnEmergBack.png"));

        JLabel personApproachedBackground=new JLabel("");
        personApproachedBackground.setIcon(new ImageIcon("images/mainScreen/personApproachedBack.png"));
        personApproachedBackground.setBounds(88,91,269,45);

        JLabel personApproacedLabel=new JLabel("Currently Active Persons:");
        personApproacedLabel.setBounds(96,105,210,19);
        personApproacedLabel.setFont(new Font("Corbel",Font.PLAIN,18));
        personApproacedLabel.setForeground(Color.WHITE);

        numApproachedField=new JTextField("");
        numApproachedField.setEditable(false);
        numApproachedField.setOpaque(false);
        numApproachedField.setFont(new Font("Corbel",Font.BOLD,18));
        numApproachedField.setBounds(309,101,39,26);

        JLabel personApproachedFieldBackground=new JLabel("");
        personApproachedFieldBackground.setIcon(new ImageIcon("images/mainScreen/personApproachedFieldBack.png"));
        personApproachedFieldBackground.setBounds(309,101,39,26);

        JLabel personInputBackground=new JLabel("");
        personInputBackground.setIcon(new ImageIcon("images/mainScreen/personInputBack.png"));
        personInputBackground.setBounds(88,405,275,44);

        JLabel personInputLabel=new JLabel("Person Input:");
        personInputLabel.setBounds(94,417,106,21);
        personInputLabel.setFont(new Font("Corbel",Font.PLAIN,18));
        personInputLabel.setForeground(Color.WHITE);

        currentInputField=new JTextField("");
        currentInputField.setEditable(false);
        currentInputField.setOpaque(false);
        currentInputField.setFont(new Font("Corbel",Font.BOLD,18));
        currentInputField.setBounds(200,414,157,28);

        JLabel personInputFieldBackground=new JLabel("");
        personInputFieldBackground.setIcon(new ImageIcon("images/mainScreen/personInputFieldBack.png"));
        personInputFieldBackground.setBounds(200,414,157,28);

        getContentPane().add(personApproacedLabel);
        getContentPane().add(numApproachedField);
        getContentPane().add(personApproachedFieldBackground);
        getContentPane().add(personApproachedBackground);

        getContentPane().add(personInputLabel);
        getContentPane().add(currentInputField);
        getContentPane().add(personInputFieldBackground);
        getContentPane().add(personInputBackground);

        getContentPane().add(cc3Label1);
        getContentPane().add(cc3Label2);

        getContentPane().add(automaticStatusLabel);
        getContentPane().add(automaticStatusField);
        getContentPane().add(automaticStatusBackground);

        getContentPane().add(btnEmergOpen);
        getContentPane().add(emergOpenBackground);

        getContentPane().add(btnEmergClose);
        getContentPane().add(emergCloseBackground);

        getContentPane().add(btnStart);
        getContentPane().add(startBtnBackground);
        getContentPane().add(btnStop);
        getContentPane().add(stopBtnBackground);

        getContentPane().add(displayPanelHeading);
        getContentPane().add(displayPanelLabel1);
        getContentPane().add(displayPanelLabel2);
        getContentPane().add(currentStatusField);
        getContentPane().add(displayPanelInputRectangle1);
        getContentPane().add(previousStatusField);
        getContentPane().add(displayPanelInputRectangle2);
        getContentPane().add(displayPanelBackground);

        getContentPane().add(btnConfig);
        getContentPane().add(configBtnBackground);

        getContentPane().add(btnQuit);
        getContentPane().add(quitBtnBackground);

        getContentPane().add(emailHeadLabel);
        getContentPane().add(btnSendEmail);
        getContentPane().add(emailBtnBack);
        getContentPane().add(emailQueryBackground);

        getContentPane().add(background);
    }

    private void addActionListeners(){

        btnConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isAutomaticStart()){
                    JOptionPane.showMessageDialog(null,"Please turn of the Automatic System to Update the Configuration Settings");
                }
                else {
                    configScreen.setVisible(true);
                }
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAutomaticSystem(2);
            }
        });

        btnSendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendEmail.frame.setVisible(true);
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAutomaticStart(true);
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAutomaticSystem(0);
            }
        });

        btnEmergOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(automaticStart){
                    setAutomaticStart(false);
                    controlSystem.OpenDoor();
                }
            }
        });

        btnEmergClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(automaticStart){
                    closeAutomaticSystem(1);
                }
            }
        });
    }

    private void closeAutomaticSystem(int num){
        stoppingNum=num;
        if(controlSystem.isGateClosed())
            startStopping();
        else {
            waitingForStop = true;
        }
    }

    @Override
    public void updateData(DoorState doorState) {
        previousStatusField.setText(doorState.toString());
        switch (controlSystem.getDoorState()){
            case Open -> {
                currentStatusField.setText("Open");
            }
            case CurrentlyOpening -> {
                currentStatusField.setText("Currently Opening - "+controlSystem.getOpenPercent()+"%");
            }
            case CurrentlyClosing -> {
                currentStatusField.setText("Currently Closing - "+controlSystem.getClosePercent()+"%");
            }
            case Closed -> {
                currentStatusField.setText("Closed");
            }
        }
    }

    @Override
    public void onDoorClosing() {
        if(waitingForStop)
            startStopping();
    }

    @Override
    public void onInputGenerated(PersonInput personInput) {
        switch (personInput){
            case NoInput -> {
                System.out.println(new Date().toString()+" : Person Input - "+"No Input Detected");
                if(!controlSystem.isPersonApproaching){
                    controlSystem.keepDoorIdleThenClose();
                }
            }
            case Approaching -> {
                personActive++;
                numApproachedField.setText(Integer.toString(personActive));
                int personId=personList.size();
                personList.add(new Person(MainScreen1.this,personId));
                System.out.println(new Date().toString()+" : Person Input - "+"Person "+(personId+1)+": Approaching");
                currentInputField.setText("P"+(personId+1)+": Approaching");
                controlSystem.OpenDoor();
                controlSystem.setPersonApproaching(true);
            }
        }
    }

    @Override
    public void onPersonInputGenerated(PersonInput personInput, int personID) {
        System.out.println(new Date().toString()+" : Person Input - "+"Person "+(personID+1)+": "+personInput);
        currentInputField.setText("P"+personID+": "+personInput.toString());
        if(personInput.equals(PersonInput.Crossing))
            controlSystem.OpenDoor();
        if(personInput.equals(PersonInput.Leaving))
            personActive--;
        if(personActive==0) {
            controlSystem.setPersonApproaching(false);
            controlSystem.keepDoorIdleThenClose();
        }
        numApproachedField.setText(Integer.toString(personActive));
    }

    private void startStopping(){
        waitingForStop=false;
        setAutomaticStart(false);
        if (stoppingNum == 2) {
            System.out.println(new Date().toString()+" : Closing Application");
            processWindowEvent(new WindowEvent(MainScreen1.this, WindowEvent.WINDOW_CLOSING));
        }
    }

}
