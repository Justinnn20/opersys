package main;

import javax.swing.*;
import java.awt.*;

public class MyTabs {
    MyTabs() {
        JFrame frame = new JFrame("Tabbed Pane Example");
        JTextField text = new JTextField(10);
        
        JPanel p1 = new JPanel();
        p1.add(text);
        
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBounds(40, 20, 300, 300);
        
        tabs.add("General", p1);
        tabs.add("Display", p2);
        tabs.add("About", p3);
        
        frame.add(tabs);
        frame.setSize(400, 400);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyTabs();
    }
}