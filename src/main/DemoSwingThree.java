package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

	public class DemoSwingThree extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;
		private JTextField txtName;
		private JButton btnTest, btnClose;

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DemoSwingThree window = new DemoSwingThree();
						window.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

	public DemoSwingThree() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		initialize();
	}
		
	private void initialize() {
		setBounds(100, 100, 450, 411);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Enter your name:");
		lblNewLabel.setBounds(133, 47, 204, 39);
		lblNewLabel.setFont(new Font("Tahoma", Font. PLAIN, 20));
		getContentPane().add(lblNewLabel);

		txtName = new JTextField();
		txtName.setBounds(133, 98, 204, 39);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtName.setColumns(10);
		getContentPane().add(txtName);

		btnTest = new JButton("TEST");
		btnTest.setBounds(77, 159, 157, 39);
		btnTest.setFont(new Font("Tahoma", Font. PLAIN, 20));
		getContentPane().add(btnTest);

		btnClose = new JButton("CLOSE");
		btnClose.setBounds(244, 159, 157, 39);
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(btnClose);

		btnTest.addActionListener(this);
		btnClose.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	// TODO Auto-generated method stub
	if(e.getSource() == btnTest) {
		JOptionPane.showMessageDialog(null, "Hello " + txtName.getText());
	}

	else if(e.getSource() == btnClose) {
		JOptionPane.showMessageDialog(null, "Bye " + txtName.getText());
		dispose();
		}
	}
}