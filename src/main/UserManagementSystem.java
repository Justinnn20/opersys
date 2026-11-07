package main;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserManagementSystem extends JFrame {
    private static final long serialVersionUID = 1L;
    
    // Components
    private JTextField txtUserID, txtName, txtEmail;
    private JPasswordField txtPassword;
    private JTable tblUsers;
    private DefaultTableModel tableModel;

    // Database Connection
    private final String DB_URL = "jdbc:mysql://localhost:3306/UserDatabase";
    private final String USER = "root";
    private final String PASSWORD = "";

    public UserManagementSystem() {
        setTitle("User Management System");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));

        // --- Top Panel: Input Fields ---
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("User ID:"));
        txtUserID = new JTextField();
        inputPanel.add(txtUserID);

        inputPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        inputPanel.add(txtName);

        inputPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        inputPanel.add(new JLabel("Password:"));
        txtPassword = new JPasswordField();
        inputPanel.add(txtPassword);

        getContentPane().add(inputPanel, BorderLayout.NORTH);

        // --- Center Panel: Table ---
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        tblUsers = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tblUsers), BorderLayout.CENTER);

        // --- Bottom Panel: Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton btnRegister = new JButton("Register");
        btnRegister.addActionListener(e -> registerUser());
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(e -> updateUser());
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> deleteUser());
        
        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> refreshTable());

        buttonPanel.add(btnRegister);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Initial Load
        refreshTable();
        setVisible(true);
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, email FROM Users")) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("email")});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUser() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "INSERT INTO Users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtName.getText());
            pstmt.setString(2, txtEmail.getText());
            pstmt.setString(3, new String(txtPassword.getPassword()));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registered!");
            refreshTable();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void updateUser() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "UPDATE Users SET name = ?, email = ? WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtName.getText());
            pstmt.setString(2, txtEmail.getText());
            pstmt.setInt(3, Integer.parseInt(txtUserID.getText()));
            if (pstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Updated!");
                refreshTable();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void deleteUser() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String sql = "DELETE FROM Users WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(txtUserID.getText()));
            if (pstmt.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(this, "Deleted!");
                refreshTable();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        new UserManagementSystem();
    }
}