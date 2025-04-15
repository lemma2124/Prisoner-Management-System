
package loginsystem;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class ManageGuards extends JFrame implements ActionListener{

   
    
    JButton add,remove,search,update,display,back;
    JLabel title,subTitle,addLabel,repLabel,sepLabel,upLabel,dipLabel;
    JPanel panel1,panel2;
    ImageIcon adimg,rimg,simg,upimg,dimg;
    
     public ManageGuards(){
        
        
        adimg = new ImageIcon(getClass().getResource("addp.jpg"));
        Image img1 = adimg.getImage();
        img1 = img1.getScaledInstance(100,90, Image.SCALE_SMOOTH);
        adimg = new ImageIcon(img1);
        
        rimg = new ImageIcon(getClass().getResource("remp.jpg"));
        Image img2 = rimg.getImage();
        img2 = img2.getScaledInstance(100,90, Image.SCALE_SMOOTH);
        rimg = new ImageIcon(img2);
        
        simg = new ImageIcon(getClass().getResource("serp.jpg"));
        Image img3 = simg.getImage();
        img3 = img3.getScaledInstance(100,90, Image.SCALE_SMOOTH);
        simg = new ImageIcon(img3);
        
        upimg = new ImageIcon(getClass().getResource("updp.jpg"));
        Image img4 = upimg.getImage();
        img4 = img4.getScaledInstance(100,90, Image.SCALE_SMOOTH);
        upimg = new ImageIcon(img4);
        
        dimg = new ImageIcon(getClass().getResource("disp.jpg"));
        Image img5 = dimg.getImage();
        img5 = img5.getScaledInstance(100,90, Image.SCALE_SMOOTH);
        dimg = new ImageIcon(img5);
        
        
        addLabel = new JLabel(adimg);
        addLabel.setBounds(50, 150, 80, 80);
        
        repLabel = new JLabel(rimg);
        repLabel.setBounds(50, 270, 80, 80);
        
        sepLabel = new JLabel(simg);
        sepLabel.setBounds(50, 390, 80, 80);
        
        upLabel = new JLabel(upimg);
        upLabel.setBounds(450, 150, 80, 80);
        
        dipLabel = new JLabel(dimg);
        dipLabel.setBounds(450, 290, 80, 80);
        
        title = new JLabel("Prison Management System");
        title.setForeground(Color.white);
        title.setBounds(10, 1, 300, 20);
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
        
        subTitle = new JLabel("Manage Guards");
        subTitle.setForeground(Color.white);
        subTitle.setBounds(320, 50, 300, 30);
        subTitle.setFont(new Font("SANS_SERIF", Font.BOLD, 22));
        
        add = new JButton("ADD");
        add.setBackground(new Color(0x052659));
        add.setForeground(Color.white);
        add.setBounds(215, 180, 100, 50);
        add.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        add.setFocusable(false);
        add.addActionListener(this);
        
        remove = new JButton("Remove");
        remove.setBackground(new Color(0x052659));
        remove.setForeground(Color.white);
        remove.setBounds(215, 300, 100, 50);
        remove.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        remove.setFocusable(false);
        remove.addActionListener(this);
        
        search = new JButton("Search");
        search.setBackground(new Color(0x052659));
        search.setForeground(Color.white);
        search.setBounds(215, 420, 100, 50);
        search.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        search.setFocusable(false);
        search.addActionListener(this);
        
        update = new JButton("Update");
        update.setBackground(new Color(0x052659));
        update.setForeground(Color.white);
        update.setBounds(610, 180, 100, 50);
        update.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        update.setFocusable(false);
        update.addActionListener(this);
        
        display = new JButton("Display");
        display.setBackground(new Color(0x052659));
        display.setForeground(Color.white);
        display.setBounds(610, 300, 100, 50);
        display.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        display.setFocusable(false);
        display.addActionListener(this);
        
        back = new JButton("Back");
        back.setBackground(new Color(0x052659));
        back.setForeground(Color.white);
        back.setBounds(695, 20, 90, 40);
        back.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
        back.setFocusable(false);
        back.addActionListener(this);
        
        
        panel1 = new JPanel();
        panel1.setBounds(0, 0, 920, 40);
        panel1.setBackground(new Color(0x052659));
        panel1.setLayout(new BorderLayout());
        
        panel2 = new JPanel();
        panel2.setBounds(0,40,920,680);
        panel2.setBackground(new Color(0x5483B3));
        panel2.setLayout(null); // Using absolute layout
        
        
        
        panel1.add(title, BorderLayout.WEST);
        panel2.add(back);
        panel2.add(subTitle);
        panel2.add(addLabel);
        panel2.add(repLabel);
        panel2.add(sepLabel);
        panel2.add(upLabel);
        panel2.add(dipLabel);
        panel2.add(add);
        panel2.add(remove);
        panel2.add(search);
        panel2.add(update);
        panel2.add(display);
        
        
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
      
        if(e.getSource()==add){
            this.dispose();
            new AddGuard();
        }
        if(e.getSource()==remove){
            this.dispose();
            new RemoveGuard();
        }
        
        if(e.getSource()==update){
          
           
            this.dispose();
            new UpdateGuardInfo();
           }
        
      
        if(e.getSource()==back){
              this.dispose();
//              this.dispose();
              Dashboard dashboard = new Dashboard();
        }
        if(e.getSource()==display){
            this.dispose();
            new DisplayGuardList();
        }
        if(e.getSource()==search){
            this.dispose();
            new SearchGuard();
        }
        
    
    }  
    
    
}


