
package loginsystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class AddGuard extends JFrame implements ActionListener {

    String Gender = "";

    JButton Add, back;
    JLabel title, nam, ag, gender, role;
    JPanel panel1, panel2;
    JTextField Name, Age, Role;
    JRadioButton rad1, rad2;

    public AddGuard() {
        

        rad1 = new JRadioButton("M");
        rad1.setBounds(220, 240, 50, 30);
        rad1.setFocusable(false);
        rad1.addActionListener(e -> Gender = "M");

        rad2 = new JRadioButton("F");
        rad2.setBounds(280, 240, 50, 30);
        rad2.setFocusable(false);
        rad2.addActionListener(e -> Gender = "F");

        ButtonGroup group = new ButtonGroup();
        group.add(rad1);
        group.add(rad2);

        Name = new JTextField();
        Name.setBounds(220, 100, 190, 40);
        Name.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        Age = new JTextField();
        Age.setBounds(220, 170, 190, 40);
        Age.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        

        Role = new JTextField();
        Role.setBounds(220, 310, 190, 40);
        Role.setFont(new Font("SANS SERIF", Font.PLAIN, 15));

        

        Add = new JButton("ADD");
        Add.setBackground(new Color(0x052659));
        Add.setForeground(Color.white);
        Add.setBounds(315, 590, 100, 40);
        Add.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        Add.setFocusable(false);
        Add.addActionListener(this);

        back = new JButton("Back");
        back.setBackground(new Color(0x052659));
        back.setForeground(Color.white);
        back.setBounds(695, 20, 90, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);

        nam = new JLabel("Full Name:");
        nam.setForeground(Color.white);
        nam.setBounds(40, 100, 100, 25);
        nam.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        ag = new JLabel("Age:");
        ag.setForeground(Color.white);
        ag.setBounds(40, 170, 100, 25);
        ag.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        gender = new JLabel("Gender:");
        gender.setForeground(Color.white);
        gender.setBounds(40, 240, 100, 25);
        gender.setFont(new Font("SANS SERIF", Font.BOLD, 15));

        role = new JLabel("Role:");
        role.setForeground(Color.white);
        role.setBounds(40, 310, 100, 25);
        role.setFont(new Font("SANS SERIF", Font.BOLD, 15));


        title = new JLabel("Prison Management System");
        title.setForeground(Color.white);
        title.setBounds(10, 1, 300, 20);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 920, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());

        panel2 = new JPanel();
        panel2.setBounds(0, 40, 920, 680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout

        panel1.add(title);

        panel2.add(Name);
        panel2.add(Age);
        panel2.add(rad1);
        panel2.add(rad2);
        panel2.add(Role);
        

        panel2.add(nam);
        panel2.add(ag);
        panel2.add(gender);
        panel2.add(role);
        panel2.add(Add);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Add) {

            String name = Name.getText();
            String ageText = Age.getText();
            String G_role = Role.getText();
            

            JOptionPane pane = new JOptionPane();
            pane.setBounds(200, 200, 400, 100);
            pane.setFocusable(false);

            if (name.isBlank() || ageText.isBlank() || G_role.isBlank() ||  this.Gender.isEmpty()) {

                pane.showMessageDialog(this, "Please Fill the form correctly");

            } else {
                try {
                    int age = Integer.parseInt(ageText);
                    
                    AddGuard(name, age, this.Gender, G_role);
                } catch (NumberFormatException ex) {
                    pane.showMessageDialog(this, "Please enter valid numerical values for Age");
                }
            }

        }

        if (e.getSource() == back) {
            this.dispose();
            
            new ManageGuards();
        }

    }

    public void AddGuard(String name, int age, String gender, String role) {
        String sql = "INSERT INTO guard (name, age, gender, role) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, role);
            

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane pane = new JOptionPane();
                pane.showMessageDialog(this, "NEW GUARD ADDED SUCCESSFULLY");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

