package main;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class ComprehensiveForm {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				ComprehensiveForm window = new ComprehensiveForm();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ComprehensiveForm() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Gender Selection with JRadioButton
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGender.setBounds(20, 20, 100, 30);
		frame.getContentPane().add(lblGender);

		JRadioButton rdoMale = new JRadioButton("Male");
		rdoMale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdoMale.setBounds(100, 20, 80, 30);
		frame.getContentPane().add(rdoMale);

		JRadioButton rdoFemale = new JRadioButton("Female");
		rdoFemale.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdoFemale.setBounds(180, 20, 80, 30);
		frame.getContentPane().add(rdoFemale);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdoMale);
		genderGroup.add(rdoFemale);

		// Hobbies Selection with JCheckBox
		JLabel lblHobbies = new JLabel("Hobbies:");
		lblHobbies.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHobbies.setBounds(20, 60, 100, 30);
		frame.getContentPane().add(lblHobbies);

		JCheckBox chkReading = new JCheckBox("Reading");
		chkReading.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkReading.setBounds(100, 60, 100, 30);
		frame.getContentPane().add(chkReading);

		JCheckBox chkTraveling = new JCheckBox("Traveling");
		chkTraveling.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkTraveling.setBounds(200, 60, 100, 30);
		frame.getContentPane().add(chkTraveling);

		JCheckBox chkGaming = new JCheckBox("Gaming");
		chkGaming.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chkGaming.setBounds(300, 60, 100, 30);
		frame.getContentPane().add(chkGaming);

		// Age Selection with JSpinner
		JLabel lblAge = new JLabel("Age:");
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAge.setBounds(20, 100, 74, 30);
		frame.getContentPane().add(lblAge);

		JSpinner spnAge = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
		spnAge.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spnAge.setBounds(100, 100, 50, 30);
		frame.getContentPane().add(spnAge);

		// Country Selection with JComboBox
		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCountry.setBounds(20, 140, 100, 30);
		frame.getContentPane().add(lblCountry);

		JComboBox<String> cboCountry = new JComboBox<>(new String[] {"Philippines", "Singapore", "Canada", "Australia", "USA"});
		cboCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cboCountry.setBounds(100, 140, 150, 30);
		frame.getContentPane().add(cboCountry);

		// Favorite Fruits Selection with JList
		JLabel lblFruits = new JLabel("Favorite Fruits:");
		lblFruits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFruits.setBounds(20, 180, 100, 30);
		frame.getContentPane().add(lblFruits);

		JList<String> lstFruits = new JList<>(new String[] {"Apple", "Banana", "Orange", "Mango", "Grape"});
		lstFruits.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lstFruits.setBounds(100, 220, 150, 100);
		lstFruits.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		frame.getContentPane().add(lstFruits);

		// Submit Button and Result Display
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSubmit.setBounds(20, 340, 100, 30);
		frame.getContentPane().add(btnSubmit);

		JTextArea txaResult = new JTextArea();
		txaResult.setText("<output here>");
		txaResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txaResult.setBounds(20, 380, 450, 109);
		txaResult.setEditable(false);
		frame.getContentPane().add(txaResult);

		// Submit Button ActionListener
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder result = new StringBuilder();

				// Gender
				if (rdoMale.isSelected()) {
					result.append("Gender: Male\n");
				} else if (rdoFemale.isSelected()) {
					result.append("Gender: Female\n");
				} else {
					result.append("Gender Not Selected\n");
				}

				// Hobbies
				result.append("Hobbies: ");
				if (chkReading.isSelected()) {
					result.append("Reading ");
				}
				if (chkTraveling.isSelected()) {
					result.append("Traveling ");
				}
				if (chkGaming.isSelected()) {
					result.append("Gaming ");
				}
				result.append("\n");

				// Age
				int age = (int) spnAge.getValue();
				result.append("Age: ").append(age).append("\n");

				// Country
				String country = (String) cboCountry.getSelectedItem();
				result.append("Country: ").append(country).append("\n");

				// Fruits
				java.util.List<String> selectedFruits = lstFruits.getSelectedValuesList();
				result.append("Favorite Fruits: ").append(String.join(", ", selectedFruits)).append("\n");

				// Display result
				txaResult.setText(result.toString());
			}
		});
	}
}