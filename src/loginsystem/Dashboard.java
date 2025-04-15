
package loginsystem;




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Dashboard extends JFrame implements ActionListener{
    
    JPanel panel1, panel2, panel3, panel4;
    JButton ManPrison, ManGuard,back;
    JLabel prisonLabel,guardlab,title;
    ImageIcon img1,img2;
    
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem changePass,logout,exit;

    public Dashboard() {
        
        menuBar = new JMenuBar(); 
        menu = new JMenu("Menu");
        
        changePass = new JMenuItem("Change Password");
        changePass.addActionListener(this);
        logout = new JMenuItem("Logout");
        logout.addActionListener(this);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        
        menu.add(changePass);
        menu.add(logout);
        menu.add(exit);
        menuBar.add(menu);
        
        
        title = new JLabel("Prison Management System",SwingConstants.CENTER);
        title.setForeground(Color.white);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
        

        img1 = new ImageIcon(getClass().getResource("prison2.jpg"));
        Image img = img1.getImage();
        img = img.getScaledInstance(200,190, Image.SCALE_SMOOTH);
        img1 = new ImageIcon(img);
        
        img2 = new ImageIcon(getClass().getResource("guard.jpg"));
        Image im = img2.getImage();
        im = im.getScaledInstance(200, 190, Image.SCALE_SMOOTH);
        img2 = new ImageIcon(im);

        panel1 = new JPanel();
        panel1.setBounds(0, 0, 810, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());
        
        panel2 = new JPanel();
        panel2.setBounds(0,40,820,680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout
        

        prisonLabel = new JLabel(img1);
        prisonLabel.setBounds(100, 280, 200, 90); // Adjusted bounds
        

        guardlab = new JLabel(img2);
        guardlab.setBounds(100, 280, 200, 90); // Adjusted bounds
        
        panel3 = new JPanel();
        panel3.setBackground(new Color(0x5483B3));
        panel3.setBounds(50, 150, 250, 200);

        panel4 = new JPanel();
        panel4.setBackground(new Color(0x5483B3));
        panel4.setBounds(460, 150, 250, 200);

        ManPrison = new JButton("Manage Prisoners");
        ManPrison.setForeground(Color.white);
        ManPrison.setBackground(new Color(0x052659));
        ManPrison.setBounds(75, 400, 200, 50);
        ManPrison.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        ManPrison.setFocusable(false);
        ManPrison.addActionListener(this);

        ManGuard = new JButton("Manage Guards");
        ManGuard.setBackground(new Color(0x052659));
        ManGuard.setForeground(Color.white);
        ManGuard.setBounds(485, 400, 200, 50);
        ManGuard.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        ManGuard.setFocusable(false);
        ManGuard.addActionListener(this);
        
        
          panel1.add(menuBar,BorderLayout.WEST);      
          panel1.add(title,BorderLayout.CENTER);
       
        

        
        panel4.add(guardlab);
        panel3.add(prisonLabel);
        
        
        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(ManPrison);
        panel2.add(ManGuard);
        
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
        
        if(e.getSource()==ManPrison){
            this.dispose();
            this.dispose();
            ManagePrisoner manager = new ManagePrisoner();
            
        }
        if(e.getSource()==ManGuard){
            this.dispose();
            this.dispose();
            ManageGuards guard = new ManageGuards();
            
        }
        if(e.getSource()==changePass){
            
            this.dispose();
            new ChangePassword();
        }
       if(e.getSource()==logout){
           
           this.dispose();
           new LoginPage();
       }
       if(e.getSource()==exit){
           System.exit(0);
       }
   }
}
