package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoSwingOne {
	private JFrame frame;
	private JTextField txtName;
	private JButton btnClose;
	
	public static void main(String[]args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoSwingOne window = new DemoSwingOne();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public DemoSwingOne() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame("Sample Demo: Swing");
		frame.setTitle("UMak");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(118, 100, 204, 39);
		frame.getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setBounds(118, 100, 204, 39);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Hello " + txtName.getText());
			}
		});
		
		btnTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTest.setBounds(62, 161, 157, 39);
		frame.getContentPane().add(btnTest);
		
		btnClose = new JButton("CLOSE");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Bye " + txtName.getText());
				frame.dispose();
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnClose.setBounds(229, 161, 157, 39);
		frame.getContentPane().add(btnClose);
	}
}
