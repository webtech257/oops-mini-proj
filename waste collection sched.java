import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WasteCollectionScheduler extends JFrame {
    JTextField areaField, dateField, vehicleField;
    JComboBox<String> statusBox;
    JButton submitBtn;

    Connection conn;

    public WasteCollectionScheduler() {
        setTitle("Waste Collection Scheduler");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        areaField = new JTextField();
        dateField = new JTextField("YYYY-MM-DD");
        vehicleField = new JTextField();
        statusBox = new JComboBox<>(new String[]{"Scheduled", "Completed", "Pending"});
        submitBtn = new JButton("Add Schedule");

        add(new JLabel("Area:"));
        add(areaField);
        add(new JLabel("Collection Date:"));
        add(dateField);
        add(new JLabel("Vehicle Number:"));
        add(vehicleField);
        add(new JLabel("Status:"));
        add(statusBox);
        add(new JLabel(""));
        add(submitBtn);

        connectDB();
        submitBtn.addActionListener(e -> insertSchedule());

        setVisible(true);
    }

    void connectDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/waste_collection5", "root", "miju");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed: " + e.getMessage());
        }
    }

    void insertSchedule() {
        String area = areaField.getText();
        String date = dateField.getText();
        String vehicle = vehicleField.getText();
        String status = (String) statusBox.getSelectedItem();

        try {
            String sql = "INSERT INTO schedule (area, collection_date, vehicle_number, status) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, area);
            stmt.setString(2, date);
            stmt.setString(3, vehicle);
            stmt.setString(4, status);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Schedule added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new WasteCollectionScheduler();
    }

}
