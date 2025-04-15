
package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;


  class LoginPage implements ActionListener {

    

    ImageIcon img;
    JFrame frame = new JFrame();
    JButton LogButton = new JButton("Login");
    JButton forgButton = new JButton("Forget");
    JTextField user = new JTextField();
    JPasswordField pass = new JPasswordField();
    JLabel userLabel = new JLabel("Username: ");
    JLabel passLabel = new JLabel("Password: ");
    JLabel message = new JLabel();
    JLabel title = new JLabel("Login");
    JLabel forget = new JLabel("Forget Password");
    JPanel leftPan = new JPanel();
    
    String admin;

    public LoginPage() {
        
        
        
        
        
        img = new ImageIcon(getClass().getResource("guard.jpg"));
        Image imgg = img.getImage();
        imgg = imgg.getScaledInstance(300, 50, Image.SCALE_SMOOTH);
        img = new ImageIcon(imgg);
        
        
        userLabel.setBounds(380, 250, 100, 25);
        userLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        

        passLabel.setBounds(380, 320, 100, 25);
        passLabel.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        message.setBounds(480, 120, 200, 25);
        message.setFont(new Font("SANS SERIF", Font.ITALIC, 15));
        message.setForeground(Color.red);

        title.setBounds(530, 70, 100, 30);
        title.setFont(new Font("SANS SERIF", Font.BOLD, 25));
        title.setForeground(new Color(0x52659));
        
        forget.setBounds(400, 620, 200, 30);
        forget.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        forget.setForeground(new Color(0x52659));
        
        
        
        user.setBounds(520, 250, 170, 30);
        pass.setBounds(520, 320, 170, 30);

        LogButton.setBounds(550, 390, 100, 40);
        LogButton.setFont(new Font("SANS SERIF", Font.BOLD, 15));
        LogButton.setForeground(Color.white);
        LogButton.setBackground(new Color(0x52659));
        LogButton.setFocusable(false);
        LogButton.addActionListener(this);
        
        forgButton.setBounds(540, 620, 80, 30);
        forgButton.setFont(new Font("SANS SERIF", Font.BOLD, 12));
        forgButton.setForeground(Color.white);
        forgButton.setBackground(new Color(0x52659));
        forgButton.setFocusable(false);
        forgButton.addActionListener(this);

        leftPan.setSize(new Dimension(300,1000));
        leftPan.setBackground(new Color(0x021024));
        leftPan.setLayout(new FlowLayout());

        
        
        
        frame.add(title);
        frame.add(leftPan);
        frame.add(LogButton);
        frame.add(user);
        frame.add(pass);
        frame.add(userLabel);
        frame.add(passLabel);
        frame.add(message);
        frame.add(forget);
        frame.add(forgButton);

        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(820, 700);
        frame.setTitle("Prison Management System");
        frame.setLayout(null);
        frame.setVisible(true);
    }
    
    
    public void findAdmin(String username, String p) {
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, p);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                
                String name = rs.getString("username");
                String passw = rs.getString("password");
              if(username.equals(name) || p.equals(passw)){
                this.admin = admin;
                frame.dispose();
                Dashboard dash = new Dashboard();
                }
            } else {
                
                message.setText("Username not Found!!!");
                user.setText("");
                pass.setText("");
                
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
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LogButton) {

            String username = user.getText();
            String password = String.valueOf(pass.getPassword());
            
            findAdmin(username,password);

          }
        if(e.getSource()==forgButton){
            frame.dispose();
            new ForgetPassword();
        }

        }
    
//       public static void main(String[] args) {
//
//     
//        LoginPage logPage = new LoginPage();
//      
//        
//        
//        
//    }

    }



