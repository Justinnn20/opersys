package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Places extends JFrame implements ItemListener {
    static JFrame f;
    static JLabel l, l1;
    static JComboBox cb;

    public static void main(String[] args) {
        f = new JFrame("frame");
        Places pl = new Places();
        f.setLayout(new FlowLayout());
        
        String s1[] = {"Caloocan", "Makati", "Taguig", "Bulacan", "Cavite"};
        cb = new JComboBox(s1);
        cb.addItemListener(pl);
        
        l = new JLabel("Select the place you are living: ");
        l1 = new JLabel("Caloocan is selected");
        l.setForeground(Color.red);
        l1.setForeground(Color.blue);
        
        JPanel p = new JPanel();
        p.add(l);
        p.add(cb);
        p.add(l1);
        
        f.add(p);
        f.setSize(500, 500);
        f.setVisible(true);
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cb) {
            l1.setText(cb.getSelectedItem() + " is selected");
        }
    }
}