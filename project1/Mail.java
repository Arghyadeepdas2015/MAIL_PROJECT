package project1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;
public class
Mail extends JFrame {

	private JPanel contentPane;
	private JTextField senderaddress;
	JEditorPane message;
	JEditorPane subject;
	static String email,password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		email=args[0];
		password=args[1];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mail frame = new Mail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Mail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 611);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Read Mails");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Receive().main(null);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_1.setBounds(827, 519, 122, 32);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("E-Mail");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 20, 126, 32);
		contentPane.add(lblNewLabel);
		
		senderaddress = new JTextField();
		senderaddress.setBounds(60, 52, 780, 32);
		contentPane.add(senderaddress);
		senderaddress.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubject.setBounds(10, 106, 126, 32);
		contentPane.add(lblSubject);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMessage.setBounds(21, 225, 126, 32);
		contentPane.add(lblMessage);
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String to=senderaddress.getText();
				String sub=subject.getText();
				String mess=message.getText();
				sendmail(mess,sub,to,"arghyadeepdas2018@gmail.com");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(410, 530, 96, 32);
		contentPane.add(btnNewButton);
		
		subject = new JEditorPane();
		subject.setBounds(60, 141, 780, 56);
		contentPane.add(subject);
		
		 message = new JEditorPane();
		message.setBounds(60, 259, 780, 233);
		contentPane.add(message);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\vector-colorful-abstract-background-with-3d-letters_M1svqS8d_SB_PM.jpg"));
		lblNewLabel_1.setBounds(108, 0, 851, 585);
		contentPane.add(lblNewLabel_1);
	}
	

	private static void sendmail(String message, String subject, String to, String from) {
	  
	    Properties p=new Properties();

	    // setting the properties of host
	    p.setProperty("mail.smtp.host","smtp.gmail.com");
	    p.setProperty("mail.smtp.port","465");
	    p.setProperty("mail.smtp.ssl.enable","true");
	    p.setProperty("mail.smtp.auth","true");

	    Session session=Session.getInstance(p, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(email,password);
	        }
	    });
	session.setDebug(true);
	    Message m=new MimeMessage(session);

	    try{
	           m.setFrom(new InternetAddress(from));
	           m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	           m.setSubject(subject);
	           m.setText(message);
	           Transport.send(m);


	      /*  Multipart multi=new MimeMultipart();

	        MimeBodyPart text=new MimeBodyPart();
	        text.setText(message);

	        MimeBodyPart attachment =new MimeBodyPart();
	        String path="D:\\Pictures\\Camera Roll\\IMG_20200912_215439165.jpg";
	        File file=new File(path);
	        attachment.attachFile(file);

	        multi.addBodyPart(text);
	        multi.addBodyPart(attachment);

	        message1.setContent(multi);*/

	        Transport.send(m);
	        System.out.println("Message is send");
	    }
	    catch (Exception e){

	    }
	    }
}
