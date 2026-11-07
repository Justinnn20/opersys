package main;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class UserRegistration extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Components
    private JTextField txtName, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnRegister;

    // Database Connection Details
    private final String DB_URL = "jdbc:mysql://localhost:3306/UserDatabase";
    private final String USER = "root";
    private final String PASSWORD = "";

    public UserRegistration() {
        setTitle("User Registration");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(4, 2));

        // Name Row
        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(lblName);
        
        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(txtName);

        // Email Row
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(lblEmail);
        
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(txtEmail);

        // Password Row
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(lblPassword);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
        getContentPane().add(txtPassword);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnRegister.addActionListener(e -> registerUser());
        getContentPane().add(btnRegister);

        setVisible(true);
    }

    private void registerUser() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = new String(txtPassword.getPassword());

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error registering user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new UserRegistration();
    }
}