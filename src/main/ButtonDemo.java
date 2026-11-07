package main;

import java.awt.*;
import java.awt.event.*;

public class ButtonDemo extends Frame implements ActionListener {
    Button rb, gb, bb;

    public ButtonDemo() {
        setLayout(new FlowLayout());
        
        rb = new Button("Red");
        gb = new Button("Green");
        bb = new Button("Blue");
        
        rb.addActionListener(this);
        gb.addActionListener(this);
        bb.addActionListener(this);
        
        add(rb);
        add(gb);
        add(bb);
        
        setTitle("Button in Action");
        setSize(300, 350);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        System.out.println("You clicked " + str + " button");
        
        if (str.equals("Red")) setBackground(Color.red);
        else if (str.equals("Green")) setBackground(Color.green);
        else if (str.equals("Blue")) setBackground(Color.blue);
    }

    public static void main(String args[]) {
        new ButtonDemo();
    }
}