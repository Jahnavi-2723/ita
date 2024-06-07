import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteWaterProblem extends JFrame {
    private JTextField problemIdField;
    private JButton deleteButton;

    public DeleteWaterProblem() {
        setTitle("Delete Water Related Problem");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        JLabel problemIdLabel = new JLabel("Problem ID:");
        problemIdField = new JTextField();

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRecord();
            }
        });

        add(problemIdLabel);
        add(problemIdField);
        add(deleteButton);
    }

    private void deleteRecord() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int problemId = Integer.parseInt(problemIdField.getText());

        String query = "DELETE FROM water_related_problem WHERE Problem_id = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, problemId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with the given Problem ID.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteWaterProblem().setVisible(true);
            }
        });
    }
}
