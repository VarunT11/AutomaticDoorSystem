import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.mail.*;

public class SendEmail {
    public JFrame frame;
    private JTextField textFieldName;
    private JTextField textFieldSubject;
    private JTextField textFieldEmail;

    private static String emailMsgTxt = "";
    private static String emailSubjectTxt = "";
    private static String emailFromAddress = "";
    private static String emailFromUserMail = "";
    private static String emailFromName = "";

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(400, 100, 880, 655);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

    /* FORM COMPONENTS DEFINED DEFINED HERE*/

        JLabel headingLabel=new JLabel("If you have any queries, write an email to us");
        headingLabel.setBounds(190,64,500,33);
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Corbel",Font.BOLD,24));
        frame.getContentPane().add(headingLabel);

        JLabel lblName = new JLabel("From:");
        lblName.setBounds(186, 188, 64, 33);
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("Corbel",Font.BOLD,24));
        frame.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(303, 181, 375, 45);
        textFieldName.setBackground(Color.getColor("#C4C4C4"));
        textFieldName.setFont(new Font("Corbel",Font.PLAIN,20));
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblEmailID = new JLabel("Email ID:");
        lblEmailID.setBounds(186, 263, 94, 33);
        lblEmailID.setForeground(Color.WHITE);
        lblEmailID.setFont(new Font("Corbel",Font.BOLD,24));
        frame.getContentPane().add(lblEmailID);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(303, 256, 375, 45);
        textFieldEmail.setBackground(Color.getColor("#C4C4C4"));
        textFieldEmail.setFont(new Font("Corbel",Font.PLAIN,20));
        frame.getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblSubject = new JLabel("Subject:");
        lblSubject.setBounds(186, 337, 89, 33);
        lblSubject.setForeground(Color.WHITE);
        lblSubject.setFont(new Font("Corbel",Font.BOLD,24));
        frame.getContentPane().add(lblSubject);

        textFieldSubject = new JTextField();
        textFieldSubject.setBounds(303, 330, 375, 45);
        textFieldSubject.setBackground(Color.getColor("#C4C4C4"));
        textFieldSubject.setFont(new Font("Corbel",Font.PLAIN,20));
        frame.getContentPane().add(textFieldSubject);
        textFieldSubject.setColumns(10);

        JLabel lblContent = new JLabel("Content:");
        lblContent.setBounds(186, 404, 95, 33);
        lblContent.setForeground(Color.WHITE);
        lblContent.setFont(new Font("Corbel",Font.BOLD,24));
        frame.getContentPane().add(lblContent);

        JTextArea textAreaContent = new JTextArea();
        textAreaContent.setBounds(303, 404, 375, 112);
        textAreaContent.setBackground(Color.getColor("#C4C4C4"));
        textAreaContent.setFont(new Font("Corbel",Font.PLAIN,20));
        frame.getContentPane().add(textAreaContent);
        textAreaContent.setLineWrap(true);
        textAreaContent.setWrapStyleWord(true);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(186, 545, 126, 47);
        btnSubmit.setOpaque(false);
        btnSubmit.setBorderPainted(false);
        btnSubmit.setContentAreaFilled(false);
        btnSubmit.setFont(new Font("Corbel",Font.BOLD,24));
        btnSubmit.setForeground(Color.BLACK);
        frame.getContentPane().add(btnSubmit);

        JLabel submitBtnBackground=new JLabel("");
        submitBtnBackground.setBounds(189,545,126,47);
        submitBtnBackground.setIcon(new ImageIcon("images/mainScreen/emailWindow/btnBackground.png"));
        frame.getContentPane().add(submitBtnBackground);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(560, 545, 126, 47);
        btnCancel.setOpaque(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setContentAreaFilled(false);
        btnCancel.setFont(new Font("Corbel",Font.BOLD,24));
        btnCancel.setForeground(Color.BLACK);
        frame.getContentPane().add(btnCancel);

        JLabel cancelBtnBackground=new JLabel("");
        cancelBtnBackground.setBounds(560,545,126,47);
        cancelBtnBackground.setIcon(new ImageIcon("images/mainScreen/emailWindow/btnBackground.png"));
        frame.getContentPane().add(cancelBtnBackground);


        JLabel background=new JLabel("");
        background.setBounds(0,0,880,655);
        background.setIcon(new ImageIcon("images/mainScreen/emailWindow/background.png"));
        frame.getContentPane().add(background);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textFieldName.getText().isEmpty() || (textFieldEmail.getText().isEmpty()) || (textFieldSubject.getText().isEmpty() || (textAreaContent.getText().isEmpty())))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    JOptionPane.showMessageDialog(null, "Data Submitted");
                    emailMsgTxt = textAreaContent.getText();
                    textAreaContent.setText("");
                    emailSubjectTxt = textFieldSubject.getText();
                    textFieldSubject.setText("");
                    emailFromName = textFieldName.getText();
                    textFieldName.setText("");
                    emailFromUserMail = textFieldEmail.getText();
                    textFieldEmail.setText("");

                    emailFromAddress = EmailWindow.SMTP_AUTH_USER;
                    emailMsgTxt += "\n" + emailFromName + "\n" + emailFromUserMail;
                   // Add List of Email address to who email needs to be sent to

                    String[] emailRecipient = new String[1];
//                    emailRecipient[0] = "iit2019141@iiita.ac.in";
//                    emailRecipient[1] = "iit2019149@iiita.ac.in";
//                    emailRecipient[2] = "iit2019152@iiita.ac.in";
                    emailRecipient[0] = "iit2019154@iiita.ac.in";
//                    emailRecipient[4] = "iit2019178@iiita.ac.in";
//                    emailRecipient[5] = "mit2019119@iiita.ac.in";

                    EmailWindow smtpMailSender = new EmailWindow();
                    try {
                        smtpMailSender.postMail(emailRecipient, emailSubjectTxt, emailMsgTxt, emailFromAddress);
                        System.out.println("Successfully Sent mail to All Users");
                    } catch (MessagingException ex) {
//                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                          System.out.println("Error! " + ex);
                    }
                    frame.dispose();
                }
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btnCancel.addActionListener(e -> {
            frame.dispose();
        });

    }

    public SendEmail() {
        initialize();
    }

}


