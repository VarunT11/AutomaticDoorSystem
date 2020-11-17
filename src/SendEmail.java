import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.mail.*;

public class SendEmail {
    private JFrame frame;
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
        frame.setBounds(100, 100, 500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

    /* FORM COMPONENTS DEFINED DEFINED HERE*/
        JLabel lblName = new JLabel("From:");
        lblName.setBounds(65, 31, 50, 27);
        frame.getContentPane().add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(128, 28, 250, 30);
        frame.getContentPane().add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblEmailID = new JLabel("Email ID:");
        lblEmailID.setBounds(65, 68, 50, 27);
        frame.getContentPane().add(lblEmailID);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(128, 65, 250, 30);
        frame.getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblSubject = new JLabel("Subject:");
        lblSubject.setBounds(65, 115, 50, 27);
        frame.getContentPane().add(lblSubject);

        textFieldSubject = new JTextField();
        textFieldSubject.setBounds(128, 112, 250, 30);
        frame.getContentPane().add(textFieldSubject);
        textFieldSubject.setColumns(10);

        JLabel lblContent = new JLabel("Content:");
        lblContent.setBounds(65, 162, 50, 27);
        frame.getContentPane().add(lblContent);

        JTextArea textAreaContent = new JTextArea();
        textAreaContent.setBounds(126, 157, 250, 150);
        frame.getContentPane().add(textAreaContent);
        textAreaContent.setLineWrap(true);
        textAreaContent.setWrapStyleWord(true);

        JButton btnCancel = new JButton("Cancel");

        btnCancel.setBounds(312, 350, 100, 25);
        frame.getContentPane().add(btnCancel);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(65, 350, 100, 25);
        frame.getContentPane().add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(textFieldName.getText().isEmpty() || (textFieldEmail.getText().isEmpty()) || (textFieldSubject.getText().isEmpty() || (textAreaContent.getText().isEmpty())))
                    JOptionPane.showMessageDialog(null, "Data Missing");
                else {
                    JOptionPane.showMessageDialog(null, "Data Submitted");
                    emailMsgTxt = textAreaContent.getText();
                    emailSubjectTxt = textFieldSubject.getText();
                    emailFromName = textFieldName.getText();
                    emailFromUserMail = textFieldEmail.getText();
                    emailFromAddress = EmailWindow.SMTP_AUTH_USER;
                    emailMsgTxt += "\n" + emailFromName + "\n" + emailFromUserMail;
                   // Add List of Email address to who email needs to be sent to

                    String emailRecipient[] = new String[1];
                    emailRecipient[0] = "iit2019152@iiita.ac.in";
//                    emailRecipient[1] = "iit2019154@iiita.ac.in";

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

    public static void main(String args[])
    {
        EventQueue.invokeLater(() -> {
            try {
                SendEmail window = new SendEmail();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SendEmail() {
        initialize();
    }

}


