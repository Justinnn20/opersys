package main;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class CountrySelector {

	private JFrame frmJcomboboxExample;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				CountrySelector window = new CountrySelector();
				window.frmJcomboboxExample.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public CountrySelector() {
		initialize();
	}

	private void initialize() {
		frmJcomboboxExample = new JFrame();
		frmJcomboboxExample.setTitle("JComboBox Example");
		frmJcomboboxExample.setBounds(100, 100, 300, 200);
		frmJcomboboxExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJcomboboxExample.getContentPane().setLayout(null);

		JLabel lblSelectCountry = new JLabel("Select a Country:");
		lblSelectCountry.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblSelectCountry.setBounds(20, 20, 120, 30);
		frmJcomboboxExample.getContentPane().add(lblSelectCountry);

		JComboBox<String> cboCountry = new JComboBox<>(new String[] {"Philippines", "Singapore", "Canada", "Australia", "USA"});
		cboCountry.setFont(new Font("Dialog", Font.PLAIN, 12));
		cboCountry.setBounds(140, 20, 120, 30);
		frmJcomboboxExample.getContentPane().add(cboCountry);

		JLabel lblResult = new JLabel("<output here>");
		lblResult.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblResult.setBounds(20, 80, 240, 30);
		frmJcomboboxExample.getContentPane().add(lblResult);

		cboCountry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedCountry = (String) cboCountry.getSelectedItem();
				lblResult.setText("Selected Country: " + selectedCountry);
			}
		});
	}
}
	