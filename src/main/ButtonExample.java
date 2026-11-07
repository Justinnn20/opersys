package main;

import java.awt.event.*;
import javax.swing.*;

public class ButtonExample {
	
    public static void main(String[] args) {
        JFrame f = new JFrame("Button Example");
        f.getContentPane().setLayout(null);
        final JTextField tf = new JTextField();
        tf.setBounds(50, 50, 150, 20);
        JButton b = new JButton("Click Here"); 
        b.setBounds(50, 100, 95, 30);
        b.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent e) {
                tf.setText(""); 
            }
        });    
        f.getContentPane().add(b);
        f.getContentPane().add(tf); 
        f.setSize(400, 400);
        f.setVisible(true);
}
}