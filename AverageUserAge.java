import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AverageUserAge extends JFrame {
    private JLabel averageAgeLabel;

    public AverageUserAge() {
        setTitle("Average User Age");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 1));

        averageAgeLabel = new JLabel();
        add(averageAgeLabel);

        calculateAverageAge();
    }

    private void calculateAverageAge() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        String query = "SELECT AVG(Age) AS average_age FROM user_details";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (rs.next()) {
                double averageAge = rs.getDouble("average_age");
                averageAgeLabel.setText("Average Age of Users: " + averageAge);
            } else {
                averageAgeLabel.setText("No data available.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error calculating average age: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AverageUserAge().setVisible(true);
            }
        });
    }
}
