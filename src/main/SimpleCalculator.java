package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SimpleCalculator {
	public static void main(String[] args) {
		// Create JFrame
		JFrame frame = new JFrame("Simple Calculator");
		frame.setSize(368, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Create JTextFields for numbers
		JTextField txtNum1 = new JTextField();
		txtNum1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNum1.setBounds(50, 50, 100, 30);

		JTextField txtNum2 = new JTextField();
		txtNum2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtNum2.setBounds(210, 50, 100, 30);

		frame.getContentPane().add(txtNum1);
		frame.getContentPane().add(txtNum2);

		// Create JLabel for result
		JLabel lblResult = new JLabel("<output here>");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResult.setBounds(50, 150, 257, 30);
		frame.getContentPane().add(lblResult);

		// Create buttons for operations
		JButton btnAdd = new JButton("+");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(50, 100, 50, 30);
		frame.getContentPane().add(btnAdd);

		JButton btnSubtract = new JButton("-");
		btnSubtract.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSubtract.setBounds(120, 100, 50, 30);
		frame.getContentPane().add(btnSubtract);

		JButton btnMultiply = new JButton("*");
		btnMultiply.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnMultiply.setBounds(190, 100, 50, 30);
		frame.getContentPane().add(btnMultiply);

		JButton btnDivide = new JButton("/");
		btnDivide.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDivide.setBounds(260, 100, 50, 30);
		frame.getContentPane().add(btnDivide);

		// Add ActionListeners to buttons
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(txtNum1.getText());
				double num2 = Double.parseDouble(txtNum2.getText());
				lblResult.setText("Result: " + (num1 + num2));
			}
		});
		
		btnSubtract.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(txtNum1.getText());
				double num2 = Double.parseDouble(txtNum2.getText());
				lblResult.setText("Result: " + (num1 - num2));
			}
		});
		
		btnMultiply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(txtNum1.getText());
				double num2 = Double.parseDouble(txtNum2.getText());
				lblResult.setText("Result: " + (num1 * num2));
			}
		});
		
		btnDivide.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double num1 = Double.parseDouble(txtNum1.getText());
				double num2 = Double.parseDouble(txtNum2.getText());
				if (num2 != 0) {
					lblResult.setText("Result: " + (num1 / num2));
				} else {
					lblResult.setText("Error: Division by zero");
				}
			}
		});
		
		// Set frame visibility
		frame.setVisible(true);
	}
}