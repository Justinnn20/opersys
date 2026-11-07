package main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxTest1 {
    public static void main(String[] a) {
        JFrame f = new JFrame("My Combo Box");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel color = new JLabel("Colors:");
        f.getContentPane().add(color, BorderLayout.NORTH);
        
        String[] options = {"Red", "Green", "Blue"};
        MyComboBox list = new MyComboBox(options);
        list.setSelectedIndex(-1); 
        
        f.getContentPane().add(list, BorderLayout.SOUTH);
        f.pack();
        f.setVisible(true);
    }

    private static class MyComboBox extends JComboBox implements ActionListener, ItemListener {
        static int count = 0;
        public MyComboBox(String[] l) {
            super(l);
            addActionListener(this);
            addItemListener(this);
            this.setSelectedIndex(-1);
        }

        public void actionPerformed(ActionEvent e) {
            count++;
            System.out.println(count + ": Action performed");
        }

        public void itemStateChanged(ItemEvent e) {
            count++;
            System.out.println(count + ": Item state changed");
        }
    }
}