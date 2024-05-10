package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    JLabel emailL, pageL;
    JLabel userPassLabel;
    JTextField emailT;
    JPasswordField userPassword;
    JButton LoginButton;
    
    Font font = new Font("Inter", Font.PLAIN, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);



    public Login() {
        super("Login");
        this.setSize(700, 400);
        this.setLocation(400, 300);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Label for page title 
        pageL = new JLabel("Login");
        pageL.setBounds(318, 20, 64, 36);
        pageL.setFont(font24b);
        this.add(pageL);

        // Label for email 
        emailL = new JLabel("Email");
        emailL.setBounds(150, 70, 100, 25);
        emailL.setFont(font16);
        this.add(emailL);

        // Text field for email
        emailT = new JTextField();
        emailT.setBounds(150, 96, 400, 40);
        emailT.setFont(font);
        this.add(emailT);

        // Label for password 
        userPassLabel = new JLabel("Password");
        userPassLabel.setBounds(150, 163, 100, 25);
        userPassLabel.setFont(font16);
        this.add(userPassLabel);

        // For password field
        userPassword = new JPasswordField();
        userPassword.setBounds(150, 188, 400, 40);
        userPassword.setEchoChar('*');
        userPassword.setFont(font);
        this.add(userPassword);

        // For login button
        LoginButton = new JButton("Login");
        LoginButton.setBounds(318, 280, 75, 40);
        LoginButton.setFont(font16);
        LoginButton.setBackground(Color.BLACK);
        LoginButton.setForeground(Color.WHITE);
        LoginButton.addActionListener(this);
        this.add(LoginButton);

        this.setVisible(true);
    }

 
    public void actionPerformed(ActionEvent e) {
        if (LoginButton == e.getSource()) {
            String name = emailT.getText();
            String pass = String.valueOf(userPassword.getPassword());
            if (name.equals("fourbit") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login successful");
                ManagerDashboard home = new ManagerDashboard(this);
                emailT.setText(""); 
                userPassword.setText(""); 
                this.setVisible(false);
            } else {
                // JOptionPane.showMessageDialog(this, "Invalid email or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
