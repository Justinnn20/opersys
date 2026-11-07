package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PointOfSales extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	
    JLabel lblSelectItem, lblQuantity, lblTotal;
    JComboBox<String> choiceItems; // Using JComboBox instead of Choice
    JSpinner spinnerQuantity;
    JButton btnAdd, btnRemove, btnCalculate;
    JTable table;
    DefaultTableModel tableModel;

   
    String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Grapes", "Watermelon"};
    double[] prices = {25.00, 10.00, 20.00, 50.00, 100.00, 75.00};

    public PointOfSales() {
        
        setTitle("Point of Sales (POS) System");
        setSize(700, 500);
        setLayout(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        Container pane = getContentPane();
        pane.setLayout(null);

        lblSelectItem = new JLabel("Select Item:");
        lblSelectItem.setBounds(50, 50, 100, 30);
        pane.add(lblSelectItem);

        choiceItems = new JComboBox<>(fruits);
        choiceItems.setBounds(160, 50, 150, 30);
        pane.add(choiceItems);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(50, 90, 100, 30);
        pane.add(lblQuantity);

        spinnerQuantity = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerQuantity.setBounds(160, 90, 120, 30);
        JComponent editor = spinnerQuantity.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setEditable(true);
        }
        pane.add(spinnerQuantity);

        btnAdd = new JButton("Add Item");
        btnAdd.setBounds(50, 140, 100, 30);
        btnAdd.addActionListener(this);
        pane.add(btnAdd);

        btnRemove = new JButton("Remove Item");
        btnRemove.setBounds(160, 140, 110, 30);
        btnRemove.addActionListener(this);
        pane.add(btnRemove);

        btnCalculate = new JButton("Calculate Total");
        btnCalculate.setBounds(280, 140, 120, 30);
        btnCalculate.addActionListener(this);
        pane.add(btnCalculate);

        String[] columns = {"Item", "Unit Price", "Quantity", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 180, 600, 200);
        pane.add(scrollPane);
        
        lblTotal = new JLabel("Total: Php 0.0");
        lblTotal.setBounds(50, 390, 200, 30);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        pane.add(lblTotal);
        
        pane.revalidate();
        pane.repaint();
        
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addItem();
        } else if (e.getSource() == btnRemove) {
            removeItem();
        } else if (e.getSource() == btnCalculate) {
            calculateTotal();
        }
    }

    private void addItem() {
        String selectedFruit = (String) choiceItems.getSelectedItem();
        int quantity = (int) spinnerQuantity.getValue();
        double unitPrice = 0;
       
        for (int i = 0; i < fruits.length; i++) {
            if (fruits[i].equals(selectedFruit)) {
                unitPrice = prices[i];
                break;
            }
        }

        double totalPrice = unitPrice * quantity;

        Object[] row = {selectedFruit, unitPrice, quantity, totalPrice};
        tableModel.addRow(row);
    }

    private void removeItem() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.");
        }
    }

    private void calculateTotal() {
        double grandTotal = 0;
 
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            grandTotal += (double) tableModel.getValueAt(i, 3);
        }
        lblTotal.setText("Total: Php " + grandTotal);
    }

    public static void main(String[] args) {
        new PointOfSales();
    }
}