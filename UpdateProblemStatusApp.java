import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateProblemStatusApp extends JFrame {
    private JTextField problemIdField, statusField;
    private JButton updateButton;

    public UpdateProblemStatusApp() {
        setTitle("Update Problem Status");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel problemIdLabel = new JLabel("Problem ID:");
        problemIdField = new JTextField();
        JLabel statusLabel = new JLabel("Status:");
        statusField = new JTextField();

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStatus();
            }
        });

        add(problemIdLabel);
        add(problemIdField);
        add(statusLabel);
        add(statusField);
        add(new JLabel());  // empty cell
        add(updateButton);
    }

    private void updateStatus() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int problemId = Integer.parseInt(problemIdField.getText());
        String status = statusField.getText();

        String query = "UPDATE Water_Related_Problem SET Status = ? WHERE Problem_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, problemId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Status updated successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating status: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateProblemStatusApp().setVisible(true);
            }
        });
    }
}
