package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class GenderSelection {
	private JFrame frmJradiobuttonExample;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GenderSelection window = new GenderSelection();
				window.frmJradiobuttonExample.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GenderSelection() {
		initialize();
	}
	
	private void initialize() {
		frmJradiobuttonExample = new JFrame();
		frmJradiobuttonExample.setTitle("JRadioButton Example");
		frmJradiobuttonExample.setBounds(100, 100, 343, 260);
		frmJradiobuttonExample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJradiobuttonExample.getContentPane().setLayout(null);

		JRadioButton rdoMale = new JRadioButton("Male");
		rdoMale.setBounds(74, 48, 100, 30);
		rdoMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJradiobuttonExample.getContentPane().add(rdoMale);

		JRadioButton rdoFemale = new JRadioButton("Female");
		rdoFemale.setBounds(174, 48, 100, 30);
		rdoFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJradiobuttonExample.getContentPane().add(rdoFemale);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdoMale);
		genderGroup.add(rdoFemale);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(124, 98, 100, 30);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJradiobuttonExample.getContentPane().add(btnSubmit);

		JLabel lblResult = new JLabel("<output here>");
		lblResult.setBounds(10, 145, 309, 30);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmJradiobuttonExample.getContentPane().add(lblResult);

		btnSubmit.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			if (rdoMale.isSelected()) {
			lblResult.setText("Selected Gender: Male");
			} else if (rdoFemale.isSelected()) {
			lblResult.setText("Selected Gender: Female");
			} else {
			lblResult.setText("No Gender Selected");
			}			
		  }
		});
	}
}