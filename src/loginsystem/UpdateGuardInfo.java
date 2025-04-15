package loginsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateGuardInfo extends JFrame implements ActionListener {

    // Attributes for guard information
    private String gender = "";
    private JPanel panel1, panel2;
    private JButton findButton, updateButton, backButton;
    private JLabel titleLabel, nameLabel, idLabel, ageLabel, genderLabel, roleLabel;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private JTextField idField, nameField, ageField, roleField;

    public UpdateGuardInfo() {
        // Initialize panels
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBounds(0, 40, 820, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout

        // Radio buttons for gender selection
        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(230, 290, 50, 30);
        maleRadioButton.setFocusable(false);
        maleRadioButton.addActionListener(e -> gender = "M");

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(290, 290, 50, 30);
        femaleRadioButton.setFocusable(false);
        femaleRadioButton.addActionListener(e -> gender = "F");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // Input fields
        idField = new JTextField();
        idField.setBounds(230, 80, 190, 30);

        nameField = new JTextField();
        nameField.setBounds(230, 150, 190, 40);
        nameField.setFont(new Font("SANS_SERIF", Font.PLAIN, 15));

        ageField = new JTextField();
        ageField.setBounds(230, 220, 190, 40);
        ageField.setFont(new Font("SANS_SERIF", Font.PLAIN, 15));

        roleField = new JTextField();
        roleField.setBounds(230, 360, 190, 40);
        roleField.setFont(new Font("SANS_SERIF", Font.PLAIN, 15));

        // Labels for input fields
        idLabel = createLabel("Enter Id of Guard", 60, 80);
        nameLabel = createLabel("Full Name:", 60, 150);
        ageLabel = createLabel("Age:", 60, 220);
        genderLabel = createLabel("Gender:", 60, 290);
        roleLabel = createLabel("Role:", 60, 360);

        // Title label
        titleLabel = new JLabel("Prison Management System");
        titleLabel.setForeground(Color.white);
        titleLabel.setBounds(10, 1, 500, 40);
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 17));

        // Action buttons
        updateButton = createButton("Update", 240, 440);
        backButton = createButton("Back", 695, 10);
        findButton = createButton("Find", 450, 80);

        // Adding components to panels
        panel1.add(titleLabel);
        panel2.add(idField);
        panel2.add(idLabel);
        panel2.add(nameField);
        panel2.add(ageField);
        panel2.add(maleRadioButton);
        panel2.add(femaleRadioButton);
        panel2.add(roleField);
        panel2.add(nameLabel);
        panel2.add(ageLabel);
        panel2.add(genderLabel);
        panel2.add(roleLabel);
        panel2.add(backButton);
        panel2.add(updateButton);
        panel2.add(findButton);

        // Adding panels to the frame
        this.add(panel1);
        this.add(panel2);

        // Frame settings
        this.setTitle("Prison Management System");
        this.setSize(820, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    // Helper method to create labels
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        return label;
    }

    // Helper method to create buttons
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0x052659));
        button.setForeground(Color.white);
        button.setBounds(x, y, 100, 30);
        button.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        button.setFocusable(false);
        button.addActionListener(this);
        return button;
    }

    // Method to find a guard by badge ID
    private void find(int badgeId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM guard WHERE badge_ID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, badgeId);
            rs = pstmt.executeQuery();

            // Check if guard exists
            if (rs.next()) {
                // Populate fields with guard information
                populateFields(rs);
            } else {
                JOptionPane.showMessageDialog(this, "No GUARD found with ID: " + badgeId);
                idField.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(rs, pstmt, connection);
        }
    }

    // Method to populate fields with guard information
    private void populateFields(ResultSet rs) throws SQLException {
        nameField.setText(rs.getString("name"));
        ageField.setText(String.valueOf(rs.getInt("age")));
        gender = rs.getString("gender");
        if ("M".equals(gender)) maleRadioButton.setSelected(true);
        else if ("F".equals(gender)) femaleRadioButton.setSelected(true);
        roleField.setText(rs.getString("role"));
    }

    // Method to close database resources
    private void closeResources(ResultSet rs, PreparedStatement pstmt, Connection connection) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (connection != null) connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Method to update guard information
    public void updateGuard(int id, String name, int age, String gender, String role) {
        String sql = "UPDATE guard SET name = ?, age = ?, gender = ?, role = ? WHERE badge_ID = ?";
        
        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, role);
            statement.setInt(5, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "GUARD INFO UPDATED SUCCESSFULLY");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "GUARD INFO NOT UPDATED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to clear input fields
    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        gender = "";
        roleField.setText("");
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
    }

    // Action listener for button events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            String idText = idField.getText();
            try {
                int id = Integer.parseInt(idText);
                find(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numerical values for Id");
            }
        }

        if (e.getSource() == updateButton) {
            String idText = idField.getText();
            try {
                int id = Integer.parseInt(idText);
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String role = roleField.getText().trim();

                if (name.isEmpty() || ageText.isEmpty() || role.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill the form correctly");
                } else {
                    int age = Integer.parseInt(ageText);
                    updateGuard(id, name, age, gender, role);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numerical values for Age");
            }
        }

        if (e.getSource() == backButton) {
            this.dispose();
            new ManageGuards(); // Navigate back to the ManageGuards screen
        }
    }
}