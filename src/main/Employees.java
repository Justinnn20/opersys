package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Employees extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String []args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				try {		
					Employees window = new Employees();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Employees() {
		setTitle("TRANSACTION");
		initialize();
	}
	
	private void initialize() {
		setTitle("TRANSACTION");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Age");
		model.addColumn("Position");

		model.addRow(new Object[] {"101", "Darrel", "23", "Developer"});
		model.addRow(new Object[] {"102", "Raine", "18", "Designer"});
		model.addRow(new Object[] {"103", "Railynne", "20", "QA Tester"});
		model.addRow(new Object[] {"104", "Dorie", "25", "Supervisor"});
		model.addRow(new Object[] {"105", "Roel", "30", "Manager"});
		getContentPane().setLayout(null);
		
		JTable tblEmployee = new JTable(model);

		JScrollPane sp = new JScrollPane(tblEmployee);
		sp.setBounds(0, 0, 586, 402);
		getContentPane().add(sp);
	}
}