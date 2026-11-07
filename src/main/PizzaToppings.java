package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.JCheckBox;

public class PizzaToppings {
	private JFrame frmJcheckboxExample;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
		try {
			PizzaToppings window = new PizzaToppings();
			window.frmJcheckboxExample.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}); 
}
	
	public PizzaToppings() {
		initialize();
	}
	
	private void initialize() {
		frmJcheckboxExample = new JFrame();
		frmJcheckboxExample.setTitle("JCheckBox Example");
		frmJcheckboxExample.setBounds(100, 100, 400, 300);
		frmJcheckboxExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJcheckboxExample.getContentPane().setLayout(null);

		JCheckBox chkCheese = new JCheckBox("Cheese");
		chkCheese.setBounds(50, 50, 100, 30);
		chkCheese.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJcheckboxExample.getContentPane().add(chkCheese);

		JCheckBox chkPepperoni = new JCheckBox("Pepperoni");
		chkPepperoni.setBounds(50, 100, 100, 30);
		chkPepperoni.setFont(new Font("Tahoma", Font. PLAIN, 12));
		frmJcheckboxExample.getContentPane().add(chkPepperoni);

		JCheckBox chkMushrooms = new JCheckBox("Mushrooms");
		chkMushrooms.setBounds(50, 150, 100, 30);
		chkMushrooms.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJcheckboxExample.getContentPane().add(chkMushrooms);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(200, 50, 100, 30);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJcheckboxExample.getContentPane().add(btnSubmit);
		
		JLabel lblResult = new JLabel("<output here>");
		lblResult.setBounds(50, 200, 300, 30);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJcheckboxExample.getContentPane().add(lblResult);

		btnSubmit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			StringBuilder toppings = new StringBuilder("Selected Toppings: ");
			if (chkCheese.isSelected()) toppings.append("Cheese ");
			if (chkPepperoni.isSelected()) toppings.append("Pepperoni ");
			if (chkMushrooms.isSelected()) toppings.append("Mushrooms ");
			lblResult.setText(toppings.toString());
			}
		});
	}
}