
package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;



class RemoveGuard extends JFrame implements ActionListener {

    
    JLabel label1, title, subTitle;
    JPanel panel1, panel2,panel3;
    JTextField field;
    JButton find, back;

    public RemoveGuard() {
        
        label1 = new JLabel("Enter Id of Guard");
        label1.setBounds(100, 80, 600, 30);
        label1.setForeground(Color.white);
        label1.setFont(new Font(null, Font.BOLD, 15));
        
        field = new JTextField();
        field.setBounds(70, 130, 200, 30);
        field.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        
        find = new JButton("Find");
        find.setBounds(120, 180, 100, 30);
        find.setBackground(new Color(0x052659));
        find.setForeground(Color.white);
        find.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        find.setFocusable(false);
        find.addActionListener(this);
       
        back = new JButton("Back");
        back.setBackground(new Color(0x052659));
        back.setForeground(Color.white);
        back.setBounds(695, 20, 90, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        panel3 = new JPanel();
        panel3.setBounds(250, 180, 320, 350);
        panel3.setBackground(new Color(0x072E33));
        panel3.setLayout(null); // Using absolute layout
        
        panel2 = new JPanel();
        panel2.setBounds(0, 40, 820, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout 

        title = new JLabel("Prison Management System");
        title.setForeground(Color.white);
        title.setBounds(10, 1, 300, 20);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
        
        subTitle = new JLabel("Remove Guard");
        subTitle.setForeground(Color.white);
        subTitle.setBounds(310, 80, 400, 20);
        subTitle.setFont(new Font("SANS_SERIF", Font.BOLD, 19));

        panel1.add(title);
        
        panel3.add(label1);
        panel3.add(field);
        panel3.add(find);
        
        panel2.add(subTitle);
        panel2.add(panel3);
        panel2.add(back);

        this.add(panel1);
        this.add(panel2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(820, 750);
        this.setResizable(false);
        this.setTitle("Prison Management System");
        this.setLayout(new BorderLayout());
        this.setVisible(true);
    }

    private void find(int guard_Id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM guard WHERE badge_ID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, guard_Id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("badge_ID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String g  = rs.getString("gender");
                char gender = g.charAt(0);
                String role = rs.getString("role");
                
                
                showPrisonerDetails(id, name, age, gender, role);
            } else {
                JOptionPane.showMessageDialog(this, "No Guard found with ID: " + guard_Id);
                field.setText("");
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

    private void showPrisonerDetails(int id, String name, int age, char gender, String role) {
        String message = "Badge ID: " + id + "\n" + "Name: " + name + "\n" + "Age: " + age + "\n" + "Gender: " +gender + "\n" + "Role: " + role ;
        int response = JOptionPane.showConfirmDialog(this, message, "Do you want to Remove Guard ? ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            int deleteResponse = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this Guard?", "Confirm Removal", JOptionPane.YES_NO_OPTION);
            if (deleteResponse == JOptionPane.YES_OPTION) {
                removePrisoner(id);
            }
        }
    }

    private void removePrisoner(int id) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM guard WHERE badge_ID = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Guard removed successfully.");
            field.setText("");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error removing prisoner.");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == find) {
            try {
                int id = Integer.parseInt(field.getText());
                find(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID format. Please enter a valid number.");
            }
        }
        if(e.getSource()==back){
            new ManageGuards();
        }
    }
}

