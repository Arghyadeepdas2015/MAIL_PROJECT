package project1;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JEditorPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Receive {

	private JFrame frame;
	private JTable table;
	static receivemessage rm;
	JLabel one,two;
	JEditorPane three;
	static ArrayList<info> res;
	private JScrollPane scrollPane_1;
	private JLabel lblNewLabel;
	private JLabel lblSubject;
	private JLabel lblMessage;
	static JTable table2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 rm=new receivemessage();
					
					 rm.main(null);
					 res=rm.arr;
					Receive window = new Receive();
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
	public Receive() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1112, 606);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 10, 392, 559);
		frame.getContentPane().add(scrollPane);
		table2 = new JTable();
		
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=table2.getSelectedRow();
				one.setText(res.get(i).from);
				two.setText(res.get(i).subject);
				three.setText(res.get(i).message);
			}
		});
		scrollPane.setViewportView(table2);
		table2.setColumnSelectionAllowed(true);
		table2.setRowSelectionAllowed(true);
		table2.setForeground(Color.BLUE);
		table2.setFillsViewportHeight(true);
		table2.setFont(new Font("High Tower Text", Font.BOLD, 12));
		table2.setBackground(new Color(204, 255, 255));
		//table2.setColumnSelectionAllowed(true);
		table2.setCellSelectionEnabled(true);
		//action();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
			},
			new String[] {
				"All Mails", "Subject"
			}
		));
		action();
		table2.getColumnModel().getColumn(0).setPreferredWidth(50);
		table2.getColumnModel().getColumn(1).setPreferredWidth(150);
		table2.setRowHeight(22);
		
		one = new JLabel("");
		one.setFont(new Font("Tahoma", Font.PLAIN, 15));
		one.setBackground(new Color(255, 255, 102));
		one.setBounds(510, 24, 578, 46);
		frame.getContentPane().add(one);
		
		 two = new JLabel("");
		 two.setFont(new Font("Tahoma", Font.PLAIN, 15));
		two.setBackground(new Color(255, 255, 255));
		two.setBounds(520, 89, 570, 51);
		frame.getContentPane().add(two);
		 
		 scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(435, 196, 653, 334);
		 frame.getContentPane().add(scrollPane_1);
		
		 three = new JEditorPane();
		 scrollPane_1.setViewportView(three);
		 
		 lblNewLabel = new JLabel("From");
		 lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
		 lblNewLabel.setBounds(435, 36, 78, 29);
		 frame.getContentPane().add(lblNewLabel);
		 
		 lblSubject = new JLabel("Subject");
		 lblSubject.setFont(new Font("Tahoma", Font.ITALIC, 16));
		 lblSubject.setBounds(435, 101, 78, 29);
		 frame.getContentPane().add(lblSubject);
		 
		 lblMessage = new JLabel("Message");
		 lblMessage.setFont(new Font("Tahoma", Font.ITALIC, 16));
		 lblMessage.setBounds(435, 165, 78, 29);
		 frame.getContentPane().add(lblMessage);

	}
	public static void action() {
		DefaultTableModel def=(DefaultTableModel)table2.getModel();
		def.setRowCount(0);
		
		int n=res.size();
		for(int i=n-1;i>=n-30;i--)
		{
			def.addRow(new Object[] {new String(res.get(i).from),new String(res.get(i).subject)});
		}
	}
}

