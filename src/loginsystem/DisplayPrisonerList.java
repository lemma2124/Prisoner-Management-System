package loginsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DisplayPrisonerList extends JFrame implements ActionListener {
    private JButton backButton;
    private JLabel titleLabel;
    private JPanel headerPanel, tablePanel;
    private JTable prisonerTable;
    private DefaultTableModel tableModel;

    public DisplayPrisonerList() {
        setTitle("Prison Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 750);
        setLocationRelativeTo(null);

        createUI();
        loadData();

        // Customize table header color
        prisonerTable.getTableHeader().setBackground(new Color(0x052659)); // Dark Blue Header
        prisonerTable.getTableHeader().setForeground(Color.YELLOW); // Yellow Header Text
        prisonerTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        setResizable(false);
        setVisible(true);
    }

    private void createUI() {
        // Header Panel
        headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // Use FlowLayout for flexible positioning
        headerPanel.setBackground(new Color(0x052659));

        titleLabel = new JLabel("List of Prisoners");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
        headerPanel.add(titleLabel); // Add title to header

        // Initialize and configure the back button
        backButton = new JButton("Back");
        backButton.setBackground(new Color(0x052659));
        backButton.setForeground(Color.white);
        backButton.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        backButton.setFocusable(false);
        backButton.addActionListener(this); // Add action listener for the back button
        headerPanel.add(backButton); // Add back button to header

        // Table Panel
        tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(new Color(0x5483B3));

        tableModel = new DefaultTableModel();
        prisonerTable = new JTable(tableModel);
        prisonerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        prisonerTable.setFillsViewportHeight(true);
        prisonerTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(prisonerTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Set custom cell renderer
        prisonerTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Alternate row colors
                if (row % 2 == 0) {
                    cell.setBackground(new Color(0xE0E0E0)); // Light gray for even rows
                } else {
                    cell.setBackground(Color.WHITE); // White for odd rows
                }
                cell.setForeground(Color.BLACK); // Default text color

                // Customize age column text color
                if (column == getAgeColumnIndex()) {
                    cell.setForeground(Color.BLUE); // Set age column text in blue
                }

                // Customize end date cell background color based on value
                if (column == getEndDateColumnIndex() && value != null) {
                    try {
                        String date = value.toString();
                        LocalDate endDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        LocalDate currentDate = LocalDate.now();
                        if (endDate.isAfter(currentDate.plusYears(10))) {
                            cell.setBackground(Color.RED); // Set background color for long-term prisoners
                            cell.setForeground(Color.WHITE); // Set text color to white
                        }
                    } catch (DateTimeParseException ex) {
                        // Handle parsing exception if needed
                    }
                }
                return cell;
            }
        });

        add(headerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private int getAgeColumnIndex() {
        // Age is at column index 2
        return 2;
    }

    private int getEndDateColumnIndex() {
        // End date is at column index 7
        return 7;
    }

    private void loadData() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnection.getConnection();
            String query = "SELECT p_ID, name, age, gender, crime, Sentence_year, start_date, end_date, visitor, relationship FROM prisoner"; // Select relevant fields
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];

            // Get column names from ResultSet metadata
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnLabel(i); // Get column name using getColumnLabel()
            }

            tableModel.setColumnIdentifiers(columnNames);
            boolean hasData = false;
            while (resultSet.next()) {
                hasData = true;
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(rowData);
            }
            if (!hasData) {
                tableModel.setRowCount(0); // Ensures table headers are visible even when empty
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data from database", "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
            } catch (SQLException ignored) {}
            try {
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException ignored) {}
            try {
                if (connection != null) connection.close();
            } catch (SQLException ignored) {}
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose(); // Close the current window
            new ManagePrisoner(); // Navigate back to the ManagePrisoner screen
        }
    }
}