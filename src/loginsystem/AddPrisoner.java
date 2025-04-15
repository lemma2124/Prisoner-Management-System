package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class AddPrisoner extends JFrame implements ActionListener {

    private String gender = "";
    private JButton addButton, backButton;
    private JLabel titleLabel, nameLabel, ageLabel, genderLabel, crimeLabel, yearLabel, startLabel, endLabel, visitorLabel, rshipLabel;
    private JPanel panel1, panel2;
    private JTextField nameField, ageField, crimeField, yearField, startDateField, endDateField, contactField, rlshipField;
    private JRadioButton maleRadioButton, femaleRadioButton;
    private ButtonGroup genderGroup;

    public AddPrisoner() {
        setTitle("Prison Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 750);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createUI();
        setVisible(true);
    }

    private void createUI() {
        panel1 = new JPanel();
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout

        // Title
        titleLabel = new JLabel("Prison Management System");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
        panel1.add(titleLabel, BorderLayout.WEST);

        // Create form components
        createFormComponents();

        // Add components to panels
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
    }

    private void createFormComponents() {
        // Full Name
        nameLabel = new JLabel("Full Name:");
        nameLabel.setForeground(Color.white);
        nameLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        nameLabel.setBounds(10, 100, 100, 25);
        panel2.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(190, 100, 190, 40);
        nameField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        panel2.add(nameField);

        // Age
        ageLabel = new JLabel("Age:");
        ageLabel.setForeground(Color.white);
        ageLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        ageLabel.setBounds(10, 170, 100, 25);
        panel2.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(190, 170, 190, 40);
        ageField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        panel2.add(ageField);

        // Gender
        genderLabel = new JLabel("Gender:");
        genderLabel.setForeground(Color.white);
        genderLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        genderLabel.setBounds(10, 240, 100, 25);
        panel2.add(genderLabel);

        maleRadioButton = new JRadioButton("M");
        maleRadioButton.setBounds(190, 240, 50, 30);
        maleRadioButton.setFocusable(false);
        maleRadioButton.addActionListener(e -> gender = "M");

        femaleRadioButton = new JRadioButton("F");
        femaleRadioButton.setBounds(270, 240, 50, 30);
        femaleRadioButton.setFocusable(false);
        femaleRadioButton.addActionListener(e -> gender = "F");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        panel2.add(maleRadioButton);
        panel2.add(femaleRadioButton);

        // Crime
        crimeLabel = new JLabel("Crime:");
        crimeLabel.setForeground(Color.white);
        crimeLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        crimeLabel.setBounds(10, 310, 100, 25);
        panel2.add(crimeLabel);

        crimeField = new JTextField();
        crimeField.setBounds(190, 310, 190, 40);
        crimeField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        panel2.add(crimeField);

        // Sentence Duration
        yearLabel = new JLabel("Sentence Duration [years]:");
        yearLabel.setForeground(Color.white);
        yearLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        yearLabel.setBounds(10, 380, 200, 25);
        panel2.add(yearLabel);

        yearField = new JTextField();
        yearField.setBounds(190, 380, 190, 40);
        yearField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        yearField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDates();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDates();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used
            }
        });

        panel2.add(yearField);

        // Start Date
        startLabel = new JLabel("Start Date:[yyyy-mm-dd]");
        startLabel.setForeground(Color.white);
        startLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        startLabel.setBounds(10, 450, 200, 25);
        panel2.add(startLabel);

        startDateField = new JTextField();
        startDateField.setBounds(190, 450, 190, 40);
        startDateField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        startDateField.setEditable(false);
        panel2.add(startDateField);

        // End Date
        endLabel = new JLabel("End Date:[yyyy-mm-dd]");
        endLabel.setForeground(Color.white);
        endLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        endLabel.setBounds(10, 520, 200, 25);
        panel2.add(endLabel);

        endDateField = new JTextField();
        endDateField.setBounds(190, 520, 190, 40);
        endDateField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        endDateField.setEditable(false);
        panel2.add(endDateField);

        // Visitor
        visitorLabel = new JLabel("Visitor:");
        visitorLabel.setForeground(Color.white);
        visitorLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        visitorLabel.setBounds(450, 100, 200, 25);
        panel2.add(visitorLabel);

        contactField = new JTextField();
        contactField.setBounds(550, 100, 190, 40);
        contactField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        panel2.add(contactField);

        // Relationship
        rshipLabel = new JLabel("Relationship:");
        rshipLabel.setForeground(Color.white);
        rshipLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        rshipLabel.setBounds(450, 170, 200, 25);
        panel2.add(rshipLabel);

        rlshipField = new JTextField();
        rlshipField.setBounds(550, 170, 190, 40);
        rlshipField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        panel2.add(rlshipField);

        // Add Button
        addButton = new JButton("ADD");
        addButton.setBackground(new Color(0x052659));
        addButton.setForeground(Color.white);
        addButton.setBounds(315, 590, 180, 40);
        addButton.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        addButton.setFocusable(false);
        addButton.addActionListener(this);
        panel2.add(addButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBackground(new Color(0x052659));
        backButton.setForeground(Color.white);
        backButton.setBounds(695, 20, 90, 40);
        backButton.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        backButton.setFocusable(false);
        backButton.addActionListener(this);
        panel2.add(backButton);
    }

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
        LocalDate calculatedStartDate = currentDate;
        LocalDate calculatedEndDate = currentDate.plusYears(duration);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        startDateField.setText(calculatedStartDate.format(dateFormatter));
        endDateField.setText(calculatedEndDate.format(dateFormatter));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addPrisonerData();
        }

        if (e.getSource() == backButton) {
            this.dispose();
            new ManagePrisoner();
        }
    }

    private void addPrisonerData() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String crime = crimeField.getText().trim();
        String sen_yearTxt = yearField.getText().trim();
        String s_date = startDateField.getText().trim();
        String e_date = endDateField.getText().trim();
        String cont = contactField.getText().trim();
        String rlship = rlshipField.getText().trim();

        if (name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter prisoner Name", "Validation Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocus();
            return; 
        }
        if (ageText.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter prisoner Age", "Validation Error", JOptionPane.ERROR_MESSAGE);
            ageField.requestFocus();
            return; 
        }
        if (crime.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter the crime", "Validation Error", JOptionPane.ERROR_MESSAGE);
            crimeField.requestFocus();
            return;
        }
        if (sen_yearTxt.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter sentence duration", "Validation Error", JOptionPane.ERROR_MESSAGE);
            yearField.requestFocus();
            return;
        }

        if (gender.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select Gender", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (cont.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter the contact info of the visitor", "Validation Error", JOptionPane.ERROR_MESSAGE);
            contactField.requestFocus();
            return;
        }
        if (rlship.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter the relationship of the visitor with the prisoner", "Validation Error", JOptionPane.ERROR_MESSAGE);
            rlshipField.requestFocus();
            return;
        }

        int age;
        int sen_year;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numerical values for Age", "Validation Error", JOptionPane.ERROR_MESSAGE);
            ageField.requestFocus();
            return;
        }

        try {
            sen_year = Integer.parseInt(sen_yearTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numerical values for Sentence duration", "Validation Error", JOptionPane.ERROR_MESSAGE);
            yearField.requestFocus();
            return;
        }

        addPrisoner(name, age, gender, crime, sen_year, s_date, e_date, cont, rlship);
    }

    private void addPrisoner(String name, int age, String gender, String crime, int year, String start_date, String end_date, String contact, String rship) {
        String sql = "INSERT INTO prisoner (name, age, gender, crime, Sentence_year, start_date, end_date, visitor, relationship) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, crime);
            statement.setInt(5, year);
            statement.setString(6, start_date);
            statement.setString(7, end_date);
            statement.setString(8, contact);
            statement.setString(9, rship);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "NEW PRISONER ADDED SUCCESSFULLY", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to insert data", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        genderGroup.clearSelection();
        crimeField.setText("");
        yearField.setText("");
        startDateField.setText("");
        endDateField.setText("");
        contactField.setText("");
        rlshipField.setText("");
    }
}