package loginsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class UpdatePrisonerInfo extends JFrame implements ActionListener {

    // Attributes for prisoner information
    private String gender = "";
    private JPanel panel1, panel2; // Panels for UI
    private JButton findButton, updateButton, backButton; // Action buttons
    private JLabel titleLabel, nameLabel, idLabel, ageLabel, genderLabel, crimeLabel, yearLabel, startLabel, endLabel, visitorLabel, relationshipLabel; // Labels for fields
    private JRadioButton maleRadioButton, femaleRadioButton; // Radio buttons for gender selection
    private ButtonGroup genderGroup; // Group for radio buttons
    private JTextField idField, nameField, ageField, crimeField, yearField, startDateField, endDateField, visitorField, relationshipField; // Input fields
    private boolean idFetched = false; // Flag to check if ID data is fetched
    private int currentPrisonerId = -1; // Stores the current prisoner ID

    // Constructor to set up the frame
    public UpdatePrisonerInfo() {
        setTitle("Prison Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 750);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        createUI(); // Method to create the user interface
        setVisible(true);
    }

    // Method to create the user interface
    private void createUI() {
        // Panel for title
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        titleLabel = new JLabel("Prison Management System");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
        panel1.add(titleLabel, BorderLayout.WEST); // Add title label to panel

        // Panel for input fields
        panel2 = new JPanel();
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute positioning

        // Input fields and labels
        idLabel = createLabel("Enter Id of Prisoner", 10, 60);
        idField = createTextField(190, 60);
        findButton = createButton("Find", 390, 60);

        nameLabel = createLabel("Full Name:", 10, 130);
        nameField = createTextField(190, 130);
        
        ageLabel = createLabel("Age:", 10, 200);
        ageField = createTextField(190, 200);
        
        genderLabel = createLabel("Gender:", 10, 270);
        maleRadioButton = createRadioButton("M", 190, 270);
        femaleRadioButton = createRadioButton("F", 260, 270);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        crimeLabel = createLabel("Crime:", 10, 340);
        crimeField = createTextField(190, 340);
        
        yearLabel = createLabel("Sentence Year:", 10, 410);
        yearField = createTextField(190, 410);
        yearField.addActionListener(e -> updateDates()); // Update dates when the year field is changed

        startLabel = createLabel("Start Date (yyyy-mm-dd):", 10, 480);
        startDateField = createTextField(190, 480);
        
        endLabel = createLabel("End Date (yyyy-mm-dd):", 10, 550);
        endDateField = createTextField(190, 550);
        
        visitorLabel = createLabel("Visitor:", 450, 100);
        visitorField = createTextField(550, 100);
        
        relationshipLabel = createLabel("Relationship:", 450, 170);
        relationshipField = createTextField(550, 170);

        // Action buttons
        updateButton = createButton("Update", 250, 610);
        backButton = createButton("Back", 695, 10);

        // Adding components to the panel
        panel2.add(idLabel);
        panel2.add(idField);
        panel2.add(findButton);
        panel2.add(nameLabel);
        panel2.add(nameField);
        panel2.add(ageLabel);
        panel2.add(ageField);
        panel2.add(genderLabel);
        panel2.add(maleRadioButton);
        panel2.add(femaleRadioButton);
        panel2.add(crimeLabel);
        panel2.add(crimeField);
        panel2.add(yearLabel);
        panel2.add(yearField);
        panel2.add(startLabel);
        panel2.add(startDateField);
        panel2.add(endLabel);
        panel2.add(endDateField);
        panel2.add(visitorLabel);
        panel2.add(visitorField);
        panel2.add(relationshipLabel);
        panel2.add(relationshipField);
        panel2.add(updateButton);
        panel2.add(backButton);

        // Adding panels to the frame
        add(panel1, BorderLayout.NORTH);
        add(panel2, BorderLayout.CENTER);

        // Initial state of text fields and buttons
        enableTextFields(false);
        updateButton.setEnabled(false);
    }

    // Helper methods to create UI components
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.white);
        label.setBounds(x, y, 300, 30);
        label.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        return label;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 190, 30);
        textField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        return textField;
    }

    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.setBackground(new Color(0x052659));
        button.setForeground(Color.white);
        button.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        button.setFocusable(false);
        button.addActionListener(this); // Add action listener to button
        return button;
    }

    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, 50, 30);
        radioButton.setFocusable(false);
        radioButton.addActionListener(e -> gender = text); // Set gender when selected
        return radioButton;
    }

    // Method to update start and end dates based on the sentence year
    private void updateDates() {
        String durationText = yearField.getText().trim();
        if (durationText.isBlank()) {
            startDateField.setText("");
            endDateField.setText("");
            return;
        }

        int duration;
        try {
            duration = Integer.parseInt(durationText);
        } catch (NumberFormatException ex) {
            startDateField.setText("");
            endDateField.setText("");
            return;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate calculatedStartDate = currentDate; // Start date is today
        LocalDate calculatedEndDate = currentDate.plusYears(duration); // End date based on duration

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        startDateField.setText(calculatedStartDate.format(dateFormatter));
        endDateField.setText(calculatedEndDate.format(dateFormatter));
    }

    // Method to find a prisoner by ID and populate fields with data
    private void find(int prisonerId) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM prisoner WHERE p_ID = ?")) {
            pstmt.setInt(1, prisonerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    loadPrisonerData(rs); // Load data into fields
                    currentPrisonerId = prisonerId;
                    idFetched = true;
                    enableTextFields(true);
                    updateButton.setEnabled(true);
                } else {
                    showError("No prisoner found with ID: " + prisonerId);
                    clearFields();
                }
            }
        } catch (SQLException ex) {
            showError("Database error: " + ex.getMessage());
            enableTextFields(false);
            updateButton.setEnabled(false);
        }
    }

    // Method to load prisoner data from ResultSet into text fields
    private void loadPrisonerData(ResultSet rs) throws SQLException {
        nameField.setText(rs.getString("name"));
        ageField.setText(String.valueOf(rs.getInt("age")));
        gender = rs.getString("gender");
        if ("M".equals(gender)) maleRadioButton.setSelected(true);
        else if ("F".equals(gender)) femaleRadioButton.setSelected(true);
        crimeField.setText(rs.getString("crime"));
        yearField.setText(String.valueOf(rs.getInt("Sentence_year")));
        startDateField.setText(rs.getString("start_date"));
        endDateField.setText(rs.getString("end_date"));
        visitorField.setText(rs.getString("visitor"));
        relationshipField.setText(rs.getString("relationship"));
    }

    // Method to update prisoner information in the database
    private void updatePrisoner() {
        String idText = idField.getText().trim();
        if (idText.isBlank()) {
            showError("Please Enter the Id");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idText);
            if (id != currentPrisonerId) {
                showError("Please first fetch data for the new Id.");
                return;
            }
        } catch (NumberFormatException ex) {
            showError("Please enter valid numerical values for Id");
            return;
        }

        // Validate input fields
        if (!validateFields()) return;

        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String crime = crimeField.getText().trim();
        String sentenceYearText = yearField.getText().trim();
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String visitor = visitorField.getText().trim();
        String relationship = relationshipField.getText().trim();

        try {
            int age = Integer.parseInt(ageText);
            int sentenceYear = Integer.parseInt(sentenceYearText);
            LocalDate start = parseDate(startDate, "start date");
            LocalDate end = parseDate(endDate, "end date");

            // Call to update database with new prisoner information
            updatePrisonerInfo(id, name, age, gender, crime, sentenceYear, start.toString(), end.toString(), visitor, relationship);
        } catch (NumberFormatException ex) {
            showError("Please enter valid numerical values for Age or Sentence Year");
        }
    }

    // Method to validate input fields
    private boolean validateFields() {
        if (nameField.getText().isBlank()) {
            showError("Please enter prisoner Name");
            return false;
        }
        if (ageField.getText().isBlank()) {
            showError("Please enter prisoner Age");
            return false;
        }
        if (crimeField.getText().isBlank()) {
            showError("Please enter the crime");
            return false;
        }
        if (yearField.getText().isBlank()) {
            showError("Please enter sentence year");
            return false;
        }
        if (startDateField.getText().isBlank()) {
            showError("Please enter start date");
            return false;
        }
        if (endDateField.getText().isBlank()) {
            showError("Please enter end date");
            return false;
        }
        if (gender.isEmpty()) {
            showError("Please select Gender");
            return false;
        }
        if (visitorField.getText().isBlank()) {
            showError("Please enter visitor contact info");
            return false;
        }
        if (relationshipField.getText().isBlank()) {
            showError("Please enter visitor relationship");
            return false;
        }
        return true; // All fields are valid
    }

    // Method to parse date strings and handle formatting errors
    private LocalDate parseDate(String dateString, String fieldName) {
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException ex) {
            showError("Please enter valid date format for " + fieldName + " (YYYY-MM-DD)");
            throw ex; // Rethrow the exception for further handling
        }
    }

    // Method to update prisoner information in the database
    private void updatePrisonerInfo(int id, String name, int age, String gender, String crime, int sentenceYear, String startDate, String endDate, String visitor, String relationship) {
        String sql = "UPDATE prisoner SET name= ?, age= ?, gender= ?, crime= ?, Sentence_year= ?, start_date= ?, end_date= ?, visitor = ?, relationship = ? WHERE p_ID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set parameters for the prepared statement
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, crime);
            statement.setInt(5, sentenceYear);
            statement.setString(6, startDate);
            statement.setString(7, endDate);
            statement.setString(8, visitor);
            statement.setString(9, relationship);
            statement.setInt(10, id);

            // Execute update and handle the result
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Prisoner info updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields(); // Clear fields after successful update
                currentPrisonerId = -1;
                idFetched = false;
                enableTextFields(false);
                updateButton.setEnabled(false);
            } else {
                showError("Failed to update prisoner information");
            }
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }

    // Method to show error messages in a dialog
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // Method to clear all input fields
    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        genderGroup.clearSelection();
        crimeField.setText("");
        yearField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        visitorField.setText("");
        relationshipField.setText("");
    }

    // Method to enable or disable text fields based on application state
    private void enableTextFields(boolean enable) {
        nameField.setEnabled(enable);
        ageField.setEnabled(enable);
        maleRadioButton.setEnabled(enable);
        femaleRadioButton.setEnabled(enable);
        crimeField.setEnabled(enable);
        yearField.setEnabled(enable);
        startDateField.setEnabled(enable);
        endDateField.setEnabled(enable);
        visitorField.setEnabled(enable);
        relationshipField.setEnabled(enable);
    }

    // Action listener for button events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findButton) {
            String idText = idField.getText();
            if (!idText.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);
                    find(id); // Call method to find prisoner by ID
                } catch (NumberFormatException ex) {
                    showError("Please enter valid numerical values for Id");
                }
            }
        }

        if (e.getSource() == updateButton) {
            if (idFetched) {
                updatePrisoner(); // Call method to update prisoner information
            } else {
                showError("Please fetch data using the Find button first.");
            }
        }

        if (e.getSource() == backButton) {
            this.dispose(); // Close current window
            new ManagePrisoner(); // Open the ManagePrisoner window (assumed to exist)
        }
    }
}