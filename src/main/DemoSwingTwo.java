package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DemoSwingTwo extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				DemoSwingTwo window = new DemoSwingTwo();
				window.setVisible(true);
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
	}

public DemoSwingTwo () {
	initialize();
}

private void initialize() {
	new JFrame();
	setBounds(100, 100, 539, 309);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	getContentPane().setLayout(null);
	
	JLabel lblNewLabel = new JLabel("Enter your Name:");
	lblNewLabel.setBounds(152, 48, 204, 39);
	lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
	getContentPane().add(lblNewLabel);
	
	JButton btnTest = new JButton("TEST");
	btnTest.setBounds(96, 160, 157, 39);
	btnTest.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Hello"+txtName.getText());
		}
	});
	
	btnTest.setFont(new Font("Tahoma", Font.PLAIN, 20));
	getContentPane().add(btnTest);

	JButton btnClose = new JButton("CLOSE");
	btnClose.setBounds(263, 160, 157, 39);
	btnClose.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Bye " + txtName.getText());
			dispose();

	}
});

	btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
	getContentPane().add(btnClose);

	txtName = new JTextField();
	txtName.setBounds(152, 99, 204, 39);
	txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
	txtName.setColumns(10);
	getContentPane().add(txtName);

	}
}