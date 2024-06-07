import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DisplayDistributionHistory extends JFrame {
    private JTextArea resultArea;
    private JButton queryButton;

    public DisplayDistributionHistory() {
        setTitle("Display Distribution History");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        queryButton = new JButton("Query");
        queryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                queryData();
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(queryButton, BorderLayout.SOUTH);
    }

    private void queryData() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        String query = "SELECT * FROM distribution_history ORDER BY water_supplied DESC";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            StringBuilder results = new StringBuilder();
            results.append("History ID\tUser ID\tZONE ID\tWater_supplied\n");
            results.append("----------------------------------------------------\n");
            while (rs.next()) {
                int historyId = rs.getInt("History_id");
                int userId = rs.getInt("User_id");
                int locationId = rs.getInt("zone_id");
                
                double water_supplied = rs.getDouble("water_supplied");
                results.append(historyId).append("\t").append(userId).append("\t").append(locationId).append("\t").append(water_supplied).append("\n");
            }
            resultArea.setText(results.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error querying data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DisplayDistributionHistory().setVisible(true);
            }
        });
    }
}
