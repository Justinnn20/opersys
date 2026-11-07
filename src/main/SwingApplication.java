package main;

import javax.swing.*;
import java.awt.event.*;

public class SwingApplication extends JFrame {
    private static String labelPrefix = "Number of button clicks: ";
    private int numClicks = 0;
    JLabel label = new JLabel(labelPrefix + "0 ");

    public SwingApplication(String title) {
        super(title);
        JButton button = new JButton("I'm a Swing button!");
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText(labelPrefix + ++numClicks);
            }
        });
        
        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(label);
        
        getContentPane().add(panel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingApplication("SwingApplication");
    }
}