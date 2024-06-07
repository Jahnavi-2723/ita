import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LocationInfo extends JFrame {
    private JTextField longitudeField, latitudeField;
    private JButton searchButton;
    private JLabel pincodeLabel, areaLabel, locationIdLabel;

    public LocationInfo() {
        setTitle("Location Information");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel longitudeLabel = new JLabel("Longitude:");
        longitudeField = new JTextField();
        JLabel latitudeLabel = new JLabel("Latitude:");
        latitudeField = new JTextField();

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchLocationInfo();
            }
        });

        pincodeLabel = new JLabel("Pincode:");
        areaLabel = new JLabel("Area:");
        locationIdLabel = new JLabel("Location ID:");

        add(longitudeLabel);
        add(longitudeField);
        add(new JLabel());
        add(latitudeLabel);
        add(latitudeField);
        add(new JLabel());  // empty cell
        add(searchButton);
        add(new JLabel()); add(new JLabel());
        add(pincodeLabel);
        add(areaLabel);
        add(locationIdLabel);
    }

    private void searchLocationInfo() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        double longitude = Double.parseDouble(longitudeField.getText());
        double latitude = Double.parseDouble(latitudeField.getText());

        String query = "SELECT pincode, area, location_id FROM location WHERE longitude = ? AND latitude = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setDouble(1, longitude);
            stmt.setDouble(2, latitude);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String pincode = rs.getString("pincode");
                String area = rs.getString("area");
                int locationId = rs.getInt("location_id");
                pincodeLabel.setText("Pincode: " + pincode);
                areaLabel.setText("Area: " + area);
                locationIdLabel.setText("Location ID: " + locationId);
            } else {
                JOptionPane.showMessageDialog(this, "No data found for the given longitude and latitude.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching location info: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LocationInfo().setVisible(true);
            }
        });
    }
}
