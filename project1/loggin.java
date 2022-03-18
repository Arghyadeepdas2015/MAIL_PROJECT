package project1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.sql.*;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loggin {

	private JFrame frame;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnSignup;
	private JPasswordField passwordField;
	 static loggin window;
	 private JLabel lblNewLabel_1;
	 private JLabel lblEmail;
	 private JLabel lblPassword;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new loggin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public loggin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 784, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(86, 249, 132, 27);
		frame.getContentPane().add(lblPassword);
		
		lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(86, 153, 76, 27);
		frame.getContentPane().add(lblEmail);
		
		lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 39));
		lblNewLabel_1.setBounds(308, 46, 132, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(228, 240, 355, 32);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(228, 144, 355, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String password = passwordField.getText();
				try {
					 String query = "select * from register order by username";
				        ResultSet rs = management().executeQuery(query);
		while (rs.next()) {
			if (rs.getString(1).contains(name) && rs.getString(2).contains(password)){
	              
				
       		 window.frame.setVisible(false);
       		 String s[] =new String[2];
       		 s[0]=name;
       		 s[1]=password;
				new Mail().main(s);  
				return;
			               }
			            	 
		}	 
			            
					
		
							JOptionPane.showMessageDialog(null, "INVALID USER NAME OR PASSWORD");
							
						
					}
				catch (Exception e1) {
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(244, 319, 98, 42);
		
		frame.getContentPane().add(btnNewButton);
		
		btnSignup = new JButton("Sign-Up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 window.frame.setVisible(false);
					new sign().main(null);
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSignup.setBounds(487, 319, 113, 42);
		frame.getContentPane().add(btnSignup);
	}
	 public static java.sql.Statement management() throws Exception {
		 
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mail", "root", "root");
	        java.sql.Statement st = con.createStatement();
	        return st;
	    }

}
