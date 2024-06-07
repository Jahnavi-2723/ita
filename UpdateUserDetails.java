import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateUserDetails extends JFrame {
    private JTextField userIdField, usernameField, dateOfResField, ageField, occupationField, locationIdField;
    private JButton updateButton;

    public UpdateUserDetails() {
        setTitle("Update User Details");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 15));

        userIdField = new JTextField();
        usernameField = new JTextField();
        dateOfResField = new JTextField();
        ageField = new JTextField();
        occupationField = new JTextField();
        locationIdField = new JTextField();

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        add(new JLabel("User ID:"));
        add(userIdField);
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Date of Registration:"));
        add(dateOfResField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Occupation:"));
        add(occupationField);
        add(new JLabel("Location ID:"));
        add(locationIdField);
        add(new JLabel());  // empty cell
        add(updateButton);
    }

    private void updateRecord() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int userId = Integer.parseInt(userIdField.getText());
        String username = usernameField.getText();
        String dateOfRes = dateOfResField.getText();
        int age = Integer.parseInt(ageField.getText());
        String occupation = occupationField.getText();
        int locationId = Integer.parseInt(locationIdField.getText());

        String query = "UPDATE user_details SET Username=?, Date_of_Res=?, Age=?, Occupation=?, Location_id=? WHERE User_id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, dateOfRes);
            stmt.setInt(3, age);
            stmt.setString(4, occupation);
            stmt.setInt(5, locationId);
            stmt.setInt(6, userId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record updated successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateUserDetails().setVisible(true);
            }
        });
    }
}
