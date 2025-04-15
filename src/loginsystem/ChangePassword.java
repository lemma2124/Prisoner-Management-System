package loginsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class ChangePassword extends JFrame implements ActionListener {

    JPanel panel1, panel2, panel3, panel4;
    JButton change, back;
    JLabel title, idLabel, oldLabel, newLabel;
    JTextField idField, oldField, newField;

    public ChangePassword() {

        title = new JLabel("Change Password");
        title.setBounds(280, 50, 400, 50);
        title.setForeground(Color.white);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 23));

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBounds(0, 40, 820, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout

        idLabel = new JLabel("Username:");
        idLabel.setForeground(Color.white);
        idLabel.setBounds(140, 170, 200, 25);
        idLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        oldLabel = new JLabel("Old Password:");
        oldLabel.setForeground(Color.white);
        oldLabel.setBounds(140, 230, 200, 25);
        oldLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        newLabel = new JLabel("New Password:");
        newLabel.setForeground(Color.white);
        newLabel.setBounds(140, 290, 200, 25);
        newLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        idField = new JTextField();
        idField.setBounds(300, 170, 190, 40);
        idField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        oldField = new JTextField();
        oldField.setBounds(300, 230, 190, 40);
        oldField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        newField = new JTextField();
        newField.setBounds(300, 290, 190, 40);
        newField.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        change = new JButton("Change");
        change.setForeground(Color.white);
        change.setBackground(new Color(0x052659));
        change.setBounds(300, 390, 180, 50);
        change.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        change.setFocusable(false);
        change.addActionListener(this);

        back = new JButton("Back");
        back.setForeground(Color.white);
        back.setBackground(new Color(0x052659));
        back.setBounds(695, 10, 100, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);

        panel2.add(title);
        panel2.add(idLabel);
        panel2.add(oldLabel);
        panel2.add(newLabel);
        panel2.add(idField);
        panel2.add(oldField);
        panel2.add(newField);
        panel2.add(change);
        panel2.add(back);

        this.add(panel1);
        this.add(panel2);

        this.setTitle("Prison Management System");
        this.setSize(820, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            String idtxt = idField.getText();
            String oldPassword = oldField.getText();
            String newPassword = newField.getText();

            try {
                //int id = Integer.parseInt(idtxt);
                checkInput(idtxt, oldPassword, newPassword);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid ID number.");
            }
        }
        if (e.getSource() == back) {
            this.dispose();
            new Dashboard();
        }

    }

    public void checkInput(String user, String oldpass, String newpass) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT id,username,password FROM admin WHERE username = ? AND password = ?";
            pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, user);
            pstmt.setString(2, oldpass);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String passw = rs.getString("password");
                if (user.equals(username) || oldpass.equals(passw)) {

                    changePass(id,username, newpass);

                }
            } else {

                JOptionPane.showMessageDialog(this, "Invalid Input Please enter the correct admin ID and Old Password.");
                idField.setText("");
                oldField.setText("");
                newField.setText("");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void changePass(int id,String user, String newPassword) {
        String sql = "UPDATE admin SET password = '" + newPassword + "' where  id =" + id;
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {

                JOptionPane.showMessageDialog(this, "Password Changed SUCCESSFULLY");
                idField.setText("");
                oldField.setText("");
                newField.setText("");

            } else {
                JOptionPane.showMessageDialog(this, "Password not CHANGED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
