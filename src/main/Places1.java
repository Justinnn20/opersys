package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Places1 extends JFrame implements ItemListener {
    static JFrame f;
    static JLabel l1, l2, l3, l4;
    static JComboBox cb1, cb2;

    public static void main(String[] args) {
        f = new JFrame("frame");
        Places1 pl = new Places1();
        
        String s1[] = { "Caloocan", "Makati", "Taguig", "Bulacan", "Cavite" };
        String s2[] = { "male", "female", "others" };
        
        cb1 = new JComboBox(s1);
        cb2 = new JComboBox(s2);
        
        cb1.setSelectedIndex(3);
        cb2.setSelectedIndex(0);
        
        cb1.addItemListener(pl);
        cb2.addItemListener(pl);
        cb1.setEditable(true);
        
        l1 = new JLabel("Select your city ");
        l2 = new JLabel("Bulacan is selected");
        l3 = new JLabel("Select your gender ");
        l4 = new JLabel("Male selected");

        l1.setForeground(Color.red);
        l2.setForeground(Color.blue);
        l3.setForeground(Color.red);
        l4.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(l1); p.add(cb1); p.add(l2);
        p.add(l3); p.add(cb2); p.add(l4);
        
        f.add(p);
        f.setSize(400, 400);
        f.setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cb1) {
            l2.setText(cb1.getSelectedItem() + " selected");
        } else {
            l4.setText(cb2.getSelectedItem() + " selected");
        }
    }
}