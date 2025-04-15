package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class ForgetPassword extends JFrame implements ActionListener {

    //declaration for panels, textfields, labels and button
    JPanel panel1, panel2;
    JTextField idField, ansField, passField;
    JLabel title, subTitle, idLabel, ansLabel, sec_Label, passLabel, q_Label;
    JButton find, reset, back;

    public ForgetPassword() {

        //panel1 edition
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        //panel2 edition
        panel2 = new JPanel();
        panel2.setBounds(0, 40, 820, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout 

        //title label edition
        title = new JLabel("Prison Management System");
        title.setForeground(Color.white);
        title.setBounds(10, 1, 500, 40);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));

        //subtitle label edition
        subTitle = new JLabel("Reset Password");
        subTitle.setForeground(Color.white);
        subTitle.setBounds(300, 30, 500, 40);
        subTitle.setFont(new Font("SANS_SERIF", Font.BOLD, 23));
        
        //id label edition
        idLabel = new JLabel("Enter ID:");
        idLabel.setBounds(150, 120, 100, 30);
        idLabel.setForeground(Color.white);
        idLabel.setFont(new Font(null, Font.BOLD, 15));

        //security question label 
        sec_Label = new JLabel("Security Question:");
        sec_Label.setBounds(150, 190, 250, 30);
        sec_Label.setForeground(Color.white);
        sec_Label.setFont(new Font(null, Font.BOLD, 15));

        //answer to security question label
        ansLabel = new JLabel("Answer:");
        ansLabel.setBounds(150, 260, 100, 30);
        ansLabel.setForeground(Color.white);
        ansLabel.setFont(new Font(null, Font.BOLD, 15));

        //new password label
        passLabel = new JLabel("New Password:");
        passLabel.setBounds(150, 330, 200, 30);
        passLabel.setForeground(Color.white);
        passLabel.setFont(new Font(null, Font.BOLD, 15));

        //id textfield for recieving id input
        idField = new JTextField();
        idField.setBounds(320, 120, 100, 30);

        //security question to display label
        q_Label = new JLabel();
        q_Label.setBounds(320, 190, 500, 30);
        q_Label.setForeground(Color.white);
        q_Label.setFont(new Font(null, Font.BOLD, 15));

        //text field for recieving answer 
        ansField = new JTextField();
        ansField.setBounds(320, 260, 200, 40);

        //password field for new password input
        passField = new JTextField();
        passField.setBounds(320, 330, 200, 40);

        find = new JButton("Find");
        find.setBackground(new Color(0x052659));
        find.setForeground(Color.white);
        find.setBounds(440, 120, 100, 30);
        find.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        find.setFocusable(false);
        find.addActionListener(this);

        reset = new JButton("Reset");
        reset.setBackground(new Color(0x052659));
        reset.setForeground(Color.white);
        reset.setBounds(350, 430, 180, 30);
        reset.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        reset.setFocusable(false);
        reset.addActionListener(this);

        back = new JButton("Back");
        back.setBackground(new Color(0x052659));
        back.setForeground(Color.white);
        back.setBounds(695, 20, 90, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);

        panel1.add(title);

        panel2.add(subTitle);
        panel2.add(idLabel);
        panel2.add(sec_Label);
        panel2.add(ansLabel);
        panel2.add(q_Label);
        panel2.add(passLabel);
        panel2.add(idField);
        panel2.add(ansField);
        panel2.add(passField);
        panel2.add(find);
        panel2.add(reset);
        panel2.add(back);

        this.add(panel1);
        this.add(panel2);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(820, 750);
        this.setResizable(false);
        this.setTitle("Prison Management System");
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs;

        int id;

        if (e.getSource() == find) {

            try {
                String idtxt = idField.getText();
                id = Integer.parseInt(idtxt);

                connection = DatabaseConnection.getConnection();
                String sql = "SELECT sec_question,admin_id FROM admin WHERE admin_id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String securityQuestion = rs.getString("sec_question");

                    q_Label.setText(securityQuestion);
                    id = rs.getInt("id");
                } else {

                    JOptionPane.showMessageDialog(this, "Admin with ID: " + id + " Not Found");
                    idField.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Admin ID.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error reseting password.");
            } finally {
                try {
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
        if (e.getSource() == reset) {

            String answer = ansField.getText();
            String newPass = passField.getText();

            try {
                String idtxt = idField.getText();
                id = Integer.parseInt(idtxt);
                connection = DatabaseConnection.getConnection();
                String sql = "SELECT sec_answer FROM admin WHERE admin_id = ?";
                pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {

                    String securityAnswer = rs.getString("sec_answer");

                    if (securityAnswer.equalsIgnoreCase(answer)) {
                        PreparedStatement updateStmt = connection.prepareStatement("UPDATE admin SET password = ? WHERE admin_id = ?");
                        updateStmt.setString(1, newPass);
                        updateStmt.setInt(2, id);
                        updateStmt.executeUpdate();

                        JOptionPane.showMessageDialog(this, "Password has been reset successfully!");

                        this.dispose();
                        new LoginPage();

                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect answer to the security question.");
                        ansField.setText("");
                        passField.setText("");
                    }

                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == back) {
            this.dispose();
            new LoginPage();
        }
    }

}
