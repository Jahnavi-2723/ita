import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WaterProblemApp extends JFrame {
    private JTextField problemIdField, descriptionField, locationIdField, severityLevelField, statusField, userIdField;
    private JButton insertButton;

    public WaterProblemApp() {
        setTitle("Water Related Problems");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel problemIdLabel = new JLabel("Problem ID:");
        problemIdField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel locationIdLabel = new JLabel("Location ID:");
        locationIdField = new JTextField();
        JLabel severityLevelLabel = new JLabel("Severity Level:");
        severityLevelField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        statusField = new JTextField();
        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();

        insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertRecord();
            }
        });

        add(problemIdLabel);
        add(problemIdField);
        add(descriptionLabel);
        add(descriptionField);
        add(locationIdLabel);
        add(locationIdField);
        add(severityLevelLabel);
        add(severityLevelField);
        add(statusLabel);
        add(statusField);
        add(userIdLabel);
        add(userIdField);
        add(new JLabel());  // empty cell
        add(insertButton);
    }

    private void insertRecord() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int problemId = Integer.parseInt(problemIdField.getText());
        String description = descriptionField.getText();
        int locationId = Integer.parseInt(locationIdField.getText());
        String severityLevel = severityLevelField.getText();
        String status = statusField.getText();
        int userId = Integer.parseInt(userIdField.getText());

        String query = "INSERT INTO Water_Related_Problem (Problem_id, Descript, Location_id, Severity_Level, Status, User_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, problemId);
            stmt.setString(2, description);
            stmt.setInt(3, locationId);
            stmt.setString(4, severityLevel);
            stmt.setString(5, status);
            stmt.setInt(6, userId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record inserted successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error inserting record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WaterProblemApp().setVisible(true);
            }
        });
    }
}
