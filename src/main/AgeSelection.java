package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class AgeSelection {
	private JFrame frmJspinnerExample;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				AgeSelection window = new AgeSelection();
				window.frmJspinnerExample.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

public AgeSelection() {
	initialize();
}

private void initialize() {
	frmJspinnerExample = new JFrame();
	frmJspinnerExample.setTitle("JSpinner Example");
	frmJspinnerExample.setBounds(100, 100, 289, 211);
	frmJspinnerExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frmJspinnerExample.getContentPane().setLayout(null);

	JLabel lblAge = new JLabel("Select Age:");
	lblAge.setBounds(30, 30, 100, 30);
	lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
	frmJspinnerExample.getContentPane().add(lblAge);

	JSpinner spnAge = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
	spnAge.setBounds(100, 30, 50, 30);
	spnAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
	frmJspinnerExample.getContentPane().add(spnAge);

	JButton btnSubmit = new JButton("Submit");
	btnSubmit.setBounds(30, 80, 100, 30);
	btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
	frmJspinnerExample.getContentPane().add(btnSubmit);

	JLabel lblResult = new JLabel("<output here>");
	lblResult.setBounds(30, 130, 200, 30);
	lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
	frmJspinnerExample.getContentPane().add(lblResult);

	btnSubmit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int age = (int) spnAge.getValue();
			lblResult.setText("Selected Age: " + age);
		}
	});
}
}
