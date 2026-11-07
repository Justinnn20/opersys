package main;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class Places3 extends JFrame implements ItemListener, ActionListener {
    static JFrame f;
    static JLabel l, l1;
    static JComboBox cb1;
    static JTextField tf;

    public static void main(String[] args) {
        f = new JFrame("frame");
        Places3 s = new Places3();
        f.setLayout(new FlowLayout());

        String s1[] = { "Caloocan", "Makati", "Taguig", "Bulacan", "Cavite" };
        cb1 = new JComboBox(s1);
        tf = new JTextField(16);
        
        JButton b1 = new JButton("ADD");
        JButton b2 = new JButton("REMOVE");

        b1.addActionListener(s);
        b2.addActionListener(s);
        cb1.addItemListener(s);

        l = new JLabel("Select your city ");
        l1 = new JLabel("Caloocan is selected");
        l.setForeground(Color.red);
        l1.setForeground(Color.blue);

        JPanel p = new JPanel();
        p.add(l); p.add(cb1); p.add(l1);
        p.add(tf); p.add(b1); p.add(b2);

        f.add(p);
        f.setSize(700, 200);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("ADD")) {
            cb1.addItem(tf.getText());
        } else {
            cb1.removeItem(tf.getText());
        }
    }

    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cb1) {
            l1.setText(cb1.getSelectedItem() + " selected");
        }
    }
}