package GUI;
import javax.swing.*;

import entity.account.*;
import entity.person.*;
import entityList.*;

import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
    Font font = new Font("Inter", Font.PLAIN, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    
    JLabel emailL, pageL, userPassL;
    JTextField emailT;
    JPasswordField userPassword;
    JButton loginBtn, resetBtn;

    CustomerList customerList;
    BankerList bankerList;

    public Login(CustomerList customerList, BankerList bankerList) {

        super("Login");
        this.setSize(700, 450);
        this.setLocation(400, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        this.customerList = customerList;
        this.bankerList = bankerList;

        // Label for page title 
        pageL = new JLabel("FourBit Bank");
        pageL.setBounds(270, 20, 280, 36);
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
        userPassL = new JLabel("Password");
        userPassL.setBounds(150, 163, 100, 25);
        userPassL.setFont(font16);
        this.add(userPassL);

        // For password field
        userPassword = new JPasswordField();
        userPassword.setBounds(150, 188, 400, 40);
        userPassword.setEchoChar('*');
        userPassword.setFont(font);
        this.add(userPassword);

        // For login button
        loginBtn = new JButton("Login");
        loginBtn.setBounds(260, 250, 170, 40);
        loginBtn.setFont(font16);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(this);
        this.add(loginBtn);

        // For Resest password button
        resetBtn = new JButton("Reset Password");
        resetBtn.setBounds(260, 300, 170, 30);
        resetBtn.setFont(font16);
        resetBtn.setBackground(Color.BLUE);
        resetBtn.setForeground(Color.WHITE);
        resetBtn.addActionListener(this);
        this.add(resetBtn);

        this.setVisible(true);
    }

 
    public void actionPerformed(ActionEvent e) {
        if (loginBtn == e.getSource()) {
            String email = emailT.getText();
            String password = String.valueOf(userPassword.getPassword());
            
            if (customerList.isValid(email, password)) {
                OnlineBanking onlineBanking = new OnlineBanking(customerList, this);
                emailT.setText(""); 
                userPassword.setText(""); 
                this.setVisible(false);
            } 
            else if (bankerList.isValid(email, password).equals("Cashier")) {
                CashierDashboard cashierDashboard = new CashierDashboard(customerList, this);
                emailT.setText(""); 
                userPassword.setText(""); 
                this.setVisible(false);
            }
            else if (bankerList.isValid(email, password).equals("Manager")) {
                ManagerDashboard managerDashboard = new ManagerDashboard(customerList, this);
                emailT.setText(""); 
                userPassword.setText(""); 
                this.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(this, "Login failed");
            }
        }
    }
}
