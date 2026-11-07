package main;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class FruitSelector {
	
	private JFrame frmJListExample;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				FruitSelector window = new FruitSelector();
				window.frmJListExample.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public FruitSelector() {
		initialize();
	}

	private void initialize() {
		frmJListExample = new JFrame();
		frmJListExample.setTitle("JList Example");
		frmJListExample.setBounds(100, 100, 400, 279);
		frmJListExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJListExample.getContentPane().setLayout(null);
		
		JLabel lblSelectFruits = new JLabel("Select Fruits:");
		lblSelectFruits.setBounds(20, 20, 100, 30);
		lblSelectFruits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJListExample.getContentPane().add(lblSelectFruits);
		
		JList<String> lstFruits = new JList<>(new String[] {"Apple", "Banana", "Orange", "Mango", "Grapes"});
		lstFruits.setBounds(20, 60, 150, 100);
		lstFruits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstFruits.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		frmJListExample.getContentPane().add(lstFruits);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(200, 60, 100, 30);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJListExample.getContentPane().add(btnSubmit);
		
		JLabel lblResult = new JLabel("<output here>");
		lblResult.setBounds(20, 180, 300, 30);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJListExample.getContentPane().add(lblResult);
		
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				java.util.List<String> selectedFruits = lstFruits.getSelectedValuesList();
				lblResult.setText("Selected Fruits: " + String.join(", ", selectedFruits));
			}
		});
	}
}
		



