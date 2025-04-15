
package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;




public class SearchGuard extends JFrame implements ActionListener{

    JPanel panel1,panel2,panel3;
    JLabel label1,title;
    JButton find,back;
    JTextField field;
    
    
    public SearchGuard(){
        
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());
        
        panel2 = new JPanel();
        panel2.setBounds(0,40,820,680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout
        
        panel3 = new JPanel();
        panel3.setBounds(210, 130, 320, 350);
        panel3.setBackground(new Color(0x072E33));
        panel3.setLayout(null); // Using absolute layout
        
        title = new JLabel("Search Guard");
        title.setForeground(Color.white);
        title.setBounds(270, 40, 500, 40);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 23));
        
        label1 = new JLabel("Enter Id of Guard");
        label1.setBounds(100, 80, 600, 30);
        label1.setForeground(Color.white);
        label1.setFont(new Font(null, Font.BOLD, 15));
        
        field = new JTextField();
        field.setBounds(60, 130, 200, 30);
        field.setFont(new Font("SANS SERIF", Font.PLAIN, 15));
        
        find = new JButton("Search");
        find.setBounds(105, 180, 100, 30);
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
        
        panel3.add(label1);
        panel3.add(field);
        panel3.add(find);
        
        panel2.add(title);
        panel2.add(panel3);
        panel2.add(back);
        
        this.add(panel1);
        this.add(panel2);
        
        this.setTitle("Prison Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(820, 750);
        this.setResizable(true);
        this.setVisible(true);
        
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
    
    
    private void find(int guardId) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM guard WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, guardId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String g  = rs.getString("gender");
                char gender = g.charAt(0);
                String role = rs.getString("role");
                
                showPrisonerDetails(id, name, age, gender, role);
            } else {
                JOptionPane.showMessageDialog(this, "No Guard found with ID: " + guardId);
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
        String message = "Guard ID: " + id + "\n" + "Name: " + name + "\n" + "Age: " + age + "\n" + "Gender: " +gender + "\n" + "Role: " +role ;
        int response = JOptionPane.showConfirmDialog(this, message, "Guard Found", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
       
        if (response==JOptionPane.OK_OPTION) {
           field.setText("");
        }
    }
    
    
}
