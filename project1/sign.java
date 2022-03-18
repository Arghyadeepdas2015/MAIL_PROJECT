package project1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class sign extends loggin {

	private JFrame frame;
	private JTextField n1;
	private JPasswordField n3;
	private JTextField n2;
	  static sign window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 window = new sign();
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
	public sign() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 835, 479);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		n1 = new JTextField();
		n1.setColumns(10);
		n1.setBounds(265, 129, 355, 32);
		frame.getContentPane().add(n1);
		
		n3 = new JPasswordField();
		n3.setBounds(265, 285, 355, 32);
		frame.getContentPane().add(n3);
		
		n2 = new JTextField();
		n2.setColumns(10);
		n2.setBounds(265, 204, 355, 32);
		frame.getContentPane().add(n2);
		
		JButton btnSignup = new JButton("Sign-Up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				String n=n1.getText();
				String email=n2.getText();
				String password=n3.getText();
				 
				 JOptionPane.showMessageDialog(null, "SUCCESSFULLY REGISTERED");
				 String query1 = "insert into register values('" + email + "','" + password + "','"+n+"')";
				 management().executeUpdate(query1);
				 window.frame.setVisible(false);
					new loggin().main(null);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				
			}	
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSignup.setBounds(520, 350, 113, 42);
		frame.getContentPane().add(btnSignup);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loggin().main(null);
		
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(244, 350, 98, 42);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Sign-Up");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 39));
		lblNewLabel_1.setBounds(301, 35, 167, 55);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(88, 139, 76, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEmail.setBounds(88, 213, 76, 27);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(88, 294, 128, 27);
		frame.getContentPane().add(lblPassword);
	}

}
