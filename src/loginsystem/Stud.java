//package loginsystem;
//
//import java.awt.*;
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;
//import javax.swing.JTableHeader;
//import javax.swing.table.JTableHeader;
//
//public class Stud extends JFrame implements ActionListener {
//
//    private JPanel panel1, panel2;
//    private JButton ManPrison;
//    private JLabel title;
//    private ImageIcon img1; // Removed img2
//
//    private JMenuBar menuBar;
//    private JMenu menu;
//    private JMenuItem changePass, logout, exit;
//
//    public Stud() {
//        createMenuBar();
//        createPanels();
//        createTable();
//
//        this.setTitle("Prison Management System");
//        this.setSize(820, 750);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLayout(new BorderLayout());
//        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        this.setVisible(true);
//    }
//
//    private void createMenuBar() {
//        menuBar = new JMenuBar();
//        menu = new JMenu("Menu");
//
//        changePass = createMenuItem("Change Password");
//        logout = createMenuItem("Logout");
//        exit = createMenuItem("Exit");
//
//        menu.add(changePass);
//        menu.add(logout);
//        menu.add(exit);
//        menuBar.add(menu);
//    }
//
//    private JMenuItem createMenuItem(String title) {
//        JMenuItem menuItem = new JMenuItem(title);
//        menuItem.addActionListener(this);
//        return menuItem;
//    }
//
//    private void createPanels() {
//        panel1 = new JPanel();
//        panel1.setBounds(0, 0, 810, 40);
//        panel1.setBackground(new Color(0x052659));
//        panel1.setLayout(new BorderLayout());
//
//        panel2 = new JPanel();
//        panel2.setBounds(0, 40, 820, 680);
//        panel2.setBackground(new Color(0x5483B3));
//        panel2.setLayout(null); // Using absolute layout
//
//        title = new JLabel("Prison Management System", SwingConstants.CENTER);
//        title.setForeground(Color.white);
//        title.setFont(new Font("SANS_SERIF", Font.BOLD, 17));
//
//        img1 = createScaledImage("prison2.jpg"); // Keeping only one image
//
//        JLabel prisonLabel = new JLabel(img1);
//        prisonLabel.setBounds(100, 150, 200, 90); // Adjusted position
//
//        panel1.add(menuBar, BorderLayout.WEST);
//        panel1.add(title, BorderLayout.CENTER);
//
//        panel2.add(prisonLabel); // Added prison image to panel2
//
//        ManPrison = createButton("Manage Prisoners", 75, 400);
//        panel2.add(ManPrison);
//
//        this.add(panel1);
//        this.add(panel2);
//    }
//
//    private ImageIcon createScaledImage(String path) {
//        ImageIcon imgIcon = new ImageIcon(getClass().getResource(path));
//        Image img = imgIcon.getImage().getScaledInstance(200, 190, Image.SCALE_SMOOTH);
//        return new ImageIcon(img);
//    }
//
//    private JButton createButton(String text, int x, int y) {
//        JButton button = new JButton(text);
//        button.setForeground(Color.white);
//        button.setBackground(new Color(0x052659));
//        button.setBounds(x, y, 200, 50);
//        button.setFont(new Font("SANS_SERIF", Font.BOLD, 15));
//        button.setFocusable(false);
//        button.addActionListener(this);
//        return button;
//    }
//
//    private void createTable() {
//        String[][] data = {
//            {"RU1298", "Lemma Dagne", "Project", "IT"},
//            {"RU1299", "John Doe", "Research", "IT"},
//            {"RU1300", "Jane Smith", "Development", "IT"},
//            {"RU1301", "Alice Johnson", "Design", "IT"},
//            {"RU1302", "Bob Brown", "Testing", "IT"},
//            {"RU1303", "Charlie Davis", "Support", "IT"},
//            {"RU1304", "David Wilson", "Management", "IT"},
//            {"RU1305", "Eve Taylor", "Analysis", "IT"}
//        };
//        String[] columnNames = {"ID", "Name", "Contribution", "Department"};
//        DefaultTableModel model = new DefaultTableModel(data, columnNames);
//        JTable table = new JTable(model);
//
//        setAlternatingRowColors(table);
//        setHeaderColor(table, new Color(37, 45, 37), Color.white); // Customize header color
//        table.setRowHeight(30);
//
//        JScrollPane scrollPane = new JScrollPane(table);
//        scrollPane.setBounds(50, 480, 700, 150); // Set position and size for the table
//        panel2.add(scrollPane); // Add the table to panel2
//    }
//
//    private static void setAlternatingRowColors(JTable table) {
//        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
//            @Override
//            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//                if (!isSelected) {
//                    c.setBackground(row % 2 == 0 ? new Color(240, 240, 240) : table.getBackground());
//                } else {
//                    c.setBackground(table.getSelectionBackground());
//                }
//                return c;
//            }
//        });
//    }
//
//    private static void setHeaderColor(JTable table, Color background, Color foreground) {
//        JTableHeader header = table.getTableHeader();
//        header.setBackground(background);
//        header.setForeground(foreground);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == ManPrison) {
//            this.dispose();
//            new ManagePrisoner();
//        } else if (e.getSource() == changePass) {
//            this.dispose();
//            new ChangePassword();
//        } else if (e.getSource() == logout) {
//            this.dispose();
//            new LoginPage();
//        } else if (e.getSource() == exit) {
//            System.exit(0);
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new Stud());
//    }
