package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Login() {
		initialize();
	}
	
	private void initialize() {
		setTitle("LOGIN");
		setBounds(100, 100, 346, 302);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(138, 40, 45, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(49, 79, 89, 13);
		getContentPane().add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUsername.setBounds(138, 71, 136, 29);
		getContentPane().add(txtUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(138, 115, 136, 29);
		getContentPane().add(txtPassword);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(49, 119, 89, 17);
		getContentPane().add(lblNewLabel_2);
		
		JCheckBox chkShowPassword = new JCheckBox("show password");
		chkShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chkShowPassword.isSelected()) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
				}
			}
		});
		chkShowPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkShowPassword.setBounds(148, 150, 136, 21);
		getContentPane().add(chkShowPassword);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().equalsIgnoreCase("admin") && new String(txtPassword.getPassword()).equalsIgnoreCase("pass")) {	
					JOptionPane.showMessageDialog(null, "Login successful");	
				} else {
					JOptionPane.showMessageDialog(null, "Invalid username and/or password does not match");
				}
			}	
			});
	btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
	btnSubmit.setBounds(59, 177, 95, 34);
	getContentPane().add(btnSubmit);
	
	JButton btnClear = new JButton("Clear");
	btnClear.setFont(new Font("Tahoma", Font.PLAIN, 15));
	btnClear.setBounds(164, 177, 95, 34);
	getContentPane().add(btnClear);
	}
}

	
	
	