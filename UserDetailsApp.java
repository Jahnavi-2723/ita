import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserDetailsApp extends JFrame {
    private JTextField userIdField, usernameField, dateOfResField, ageField, occupationField, locationIdField;
    private JButton insertButton;

    public UserDetailsApp() {
        setTitle("User Details");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        userIdField = new JTextField();
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel dateOfResLabel = new JLabel("Date of Registration:");
        dateOfResField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();
        JLabel occupationLabel = new JLabel("Occupation:");
        occupationField = new JTextField();
        JLabel locationIdLabel = new JLabel("Location ID:");
        locationIdField = new JTextField();

        insertButton = new JButton("Insert");
        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertRecord();
            }
        });

        add(userIdLabel);
        add(userIdField);
        add(usernameLabel);
        add(usernameField);
        add(dateOfResLabel);
        add(dateOfResField);
        add(ageLabel);
        add(ageField);
        add(occupationLabel);
        add(occupationField);
        add(locationIdLabel);
        add(locationIdField);
        add(new JLabel());  // empty cell
        add(insertButton);
    }

    private void insertRecord() {
        String url = "jdbc:mysql://localhost:3306/mybase";
        String user = "root";
        String password = "Pandu_dbms27";

        int userId = Integer.parseInt(userIdField.getText());
        String username = usernameField.getText();
        String dateOfRes = dateOfResField.getText();
        int age = Integer.parseInt(ageField.getText());
        String occupation = occupationField.getText();
        int locationId = Integer.parseInt(locationIdField.getText());

        String query = "INSERT INTO user_details (User_id, Username, Date_of_Res, Age, Occupation, Location_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            stmt.setString(2, username);
            stmt.setString(3, dateOfRes);
            stmt.setInt(4, age);
            stmt.setString(5, occupation);
            stmt.setInt(6, locationId);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record inserted successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error inserting record: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserDetailsApp().setVisible(true);
            }
        });
    }
}
