package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxTest extends JFrame {
    JComboBox combo;
    JTextField txt;
    JLabel lbl;
    Container con = getContentPane();

    public JComboBoxTest() {
        super("Sample");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] subject = {"Math", "Science", "English", "Filipino"};
        
        lbl = new JLabel("Choose a subject:");
        combo = new JComboBox(subject);
        txt = new JTextField(10);
        
        combo.setBackground(Color.BLUE);
        combo.setForeground(Color.YELLOW);
        
        con.add(lbl);
        con.add(combo);
        con.add(txt);
        con.setLayout(new FlowLayout());
        
        combo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String str = (String) combo.getSelectedItem();
                txt.setText(str);
            }
        });
    }

    public static void main(String[] args) {
        JComboBoxTest frame = new JComboBoxTest();
        frame.setSize(350, 150);
        frame.setVisible(true);
    }
}