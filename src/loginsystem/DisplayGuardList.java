


package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;



public class DisplayGuardList implements ActionListener{
     JButton back;
     JFrame frame;
     JLabel title;
     JPanel panel1,panel2;

    public DisplayGuardList() {

        frame = new JFrame("Prison Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 750);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xffffff));

        title = new JLabel("List of Guards");
        title.setForeground(Color.white);
        title.setBounds(330, 2, 300, 20);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 60);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(null);

        panel2 = new JPanel();
        panel2.setBounds(0, 60, 820, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout 
        
        back = new JButton("Back");
        back.setBackground(new Color(0x052659));
        back.setForeground(Color.white);
        back.setBounds(695, 12, 90, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setForeground(Color.white);
        scrollPane.setBounds(0, 0, 800, 660);
        

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM guard";
            ResultSet resultSet = statement.executeQuery(query);

            // Get metadata to create column names
            int columnCount = resultSet.getMetaData().getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = resultSet.getMetaData().getColumnName(i);
            }
            model.setColumnIdentifiers(columnNames);

            // Fetch each row from the result set
            while (resultSet.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = resultSet.getObject(i);
                }
                model.addRow(rowData);
                
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        panel1.add(title);
        panel1.add(back);
        panel2.add(scrollPane);

        frame.setLocationRelativeTo(null);
        frame.add(panel1);
        frame.add(panel2);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back){
           frame.dispose();
           new ManageGuards();
       }
    
    }
}















//package loginsystem;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.sql.*;
//
//
//
//public class DisplayGuardList implements ActionListener{
//     JButton back;
//     JFrame frame;
//     JLabel title;
//     JPanel panel1,panel2;
//
//    public DisplayGuardList() {
//
//        frame = new JFrame("Prison Management System");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(820, 750);
//        frame.setLayout(null);
//        frame.getContentPane().setBackground(new Color(0xffffff));
//
//        title = new JLabel("List of Guards");
//        title.setForeground(Color.white);
//        title.setBounds(330, 2, 300, 20);
//        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
//
//        panel1 = new JPanel();
//        panel1.setBounds(0, 0, 810, 40);
//        panel1.setBackground(new Color(0x052659));
//        panel1.setLayout(null);
//
//        panel2 = new JPanel();
//        panel2.setBounds(0, 40, 820, 680);
//        panel2.setBackground(new Color(0x5483B3));
//        panel2.setLayout(null); // Using absolute layout 
//
//        DefaultTableModel model = new DefaultTableModel();
//        JTable table = new JTable(model);
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setForeground(Color.white);
//        scrollPane.setBounds(0, 0, 800, 660);
//        panel1.add(title);
//        panel2.add(scrollPane);
//
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM guard";
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Get metadata to create column names
//            int columnCount = resultSet.getMetaData().getColumnCount();
//            String[] columnNames = new String[columnCount];
//            for (int i = 1; i <= columnCount; i++) {
//                columnNames[i - 1] = resultSet.getMetaData().getColumnName(i);
//            }
//            model.setColumnIdentifiers(columnNames);
//
//            // Fetch each row from the result set
//            while (resultSet.next()) {
//                Object[] rowData = new Object[columnCount];
//                for (int i = 1; i <= columnCount; i++) {
//                    rowData[i - 1] = resultSet.getObject(i);
//                }
//                model.addRow(rowData);
//                
//            }
//
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        frame.add(panel1);
//        frame.add(panel2);
//        frame.setVisible(true);
//
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==back){
//           frame.dispose();
//           new ManagePrisoner();
//       }
//    
//    }
//}
