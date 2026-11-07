package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxDemo extends JPanel implements ActionListener {
    JCheckBox chinButton, glassesButton, hairButton, teethButton;
    JButton goButton = new JButton("Go!");

    public CheckBoxDemo() {
        chinButton = new JCheckBox("Chin");
        chinButton.setSelected(true);
        
        glassesButton = new JCheckBox("Glasses");
        glassesButton.setSelected(true);
        
        hairButton = new JCheckBox("Hair"); 
        hairButton.setSelected(true);
        
        teethButton = new JCheckBox("Teeth");
        teethButton.setSelected(true);
        
        goButton.addActionListener(this);
        setLayout(new GridLayout(0, 1));
        
        add(chinButton);
        add(glassesButton);
        add(hairButton);
        add(teethButton);
        add(goButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (glassesButton.isSelected()) {
            System.out.println("Glasses = true");
        } else {
            System.out.println("Glasses = false");
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CheckBoxDemo());
        frame.pack();
        frame.setVisible(true);
    }
}