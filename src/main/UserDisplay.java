package main;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserDisplay extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tblUsers;
    private DefaultTableModel tableModel;

    // Database Connection Details
    private final String DB_URL = "jdbc:mysql://localhost:3306/UserDatabase";
    private final String USER = "root";
    private final String PASSWORD = "";

    public UserDisplay() {
        setTitle("User Data");
        setSize(600, 174);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Table Setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        tblUsers = new JTable(tableModel);
        getContentPane().add(new JScrollPane(tblUsers), BorderLayout.CENTER);

        fetchUserData();

        setVisible(true);
    }

    private void fetchUserData() {
        String query = "SELECT * FROM Users";
        
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                tableModel.addRow(new Object[]{id, name, email});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new UserDisplay();
    }
}