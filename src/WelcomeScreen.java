import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame {

    private JButton btnContinue;
    private MainScreen1 mainScreen;

    public static void main(String[] args){
        WelcomeScreen welcomeScreen=new WelcomeScreen();
        welcomeScreen.setVisible(true);
    }

    public WelcomeScreen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(null);
        setBounds(240,100,1180,620);
        initialize();
        addActionListeners();
        mainScreen=new MainScreen1();
    }

    private void initialize(){
        JLabel background=new JLabel("");
        background.setBounds(0,0,1166,590);
        background.setIcon(new ImageIcon("images/welcomeScreen/background.png"));

        JLabel heading=new JLabel("Welcome to Automatic Door System");
        heading.setForeground(new Color(138,217,242));
        heading.setBounds(181,222,802,65);
        heading.setFont(new Font("Corbel",Font.BOLD,50));

        btnContinue=new JButton("Continue");
        btnContinue.setOpaque(false);
        btnContinue.setBorderPainted(false);
        btnContinue.setContentAreaFilled(false);
        btnContinue.setFont(new Font("Corbel",Font.BOLD,27));
        btnContinue.setBounds(514,320,153,59);

        JLabel btnBackground=new JLabel("");
        btnBackground.setBounds(514,320,153,59);
        btnBackground.setIcon(new ImageIcon("images/welcomeScreen/btnBackground.png"));

        getContentPane().add(heading);

        getContentPane().add(btnContinue);
        getContentPane().add(btnBackground);

        getContentPane().add(background);
    }

    private void addActionListeners(){
        btnContinue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeScreen.this.setVisible(false);
                mainScreen.setVisible(true);
            }
        });
    }
}
