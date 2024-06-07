import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SumEstimatedWaterDemand extends JFrame {
    private JTextField zoneId1Field, zoneId2Field;
    private JButton calculateButton;
    private JLabel resultLabel;

    public SumEstimatedWaterDemand() {
        setTitle("Sum of Estimated Water Demand");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel zoneId1Label = new JLabel("Zone ID 1:");
        zoneId1Field = new JTextField();
        JLabel zoneId2Label = new JLabel("Zone ID 2:");
        zoneId2Field = new JTextField();

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateSum();
            }
        });

        resultLabel = new JLabel();

        add(zoneId1Label);
        add(zoneId1Field);
        add(zoneId2Label);
        add(zoneId2Field);
        add(new JLabel());  // empty cell
        add(calculateButton);
        add(new JLabel());  // empty cell
        add(resultLabel);
    }

    private void calculateSum() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int zoneId1 = Integer.parseInt(zoneId1Field.getText());
        int zoneId2 = Integer.parseInt(zoneId2Field.getText());

        String query = "SELECT SUM(estimated_water_demand) AS total_demand FROM water_distribution_plan WHERE zone_id IN (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, zoneId1);
            stmt.setInt(2, zoneId2);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double totalDemand = rs.getDouble("total_demand");
                resultLabel.setText("Total Estimated Water Demand: " + totalDemand);
            } else {
                resultLabel.setText("No data found for the given zone IDs.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error calculating sum: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SumEstimatedWaterDemand().setVisible(true);
            }
        });
    }
}
