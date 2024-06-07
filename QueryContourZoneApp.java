import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QueryContourZoneApp extends JFrame {
    private JTextArea resultArea;
    private JButton queryButton;

    public QueryContourZoneApp() {
        setTitle("Query Contour Zone");
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

        String query = "SELECT * FROM contour_zone";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            StringBuilder results = new StringBuilder();
            results.append("Zone ID\tLocation ID\tDescription\n");
            results.append("----------------------------------------------------\n");
            while (rs.next()) {
                int zoneId = rs.getInt("zone_id");
                int locationId = rs.getInt("location_id");
                String descript = rs.getString("descript");
                results.append(zoneId).append("\t").append(locationId).append("\t").append(descript).append("\n");
            }
            resultArea.setText(results.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error querying data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QueryContourZoneApp().setVisible(true);
            }
        });
    }
}
