package GUI;
import javax.swing.*;

import entity.account.*;
import entity.person.*;
import entityList.*;
import file.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class ResetPassword extends JFrame implements ActionListener {
    Font font = new Font("Inter", Font.PLAIN, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    
    JLabel emailL, pageL, oldUserPassL, newUserPassL;
    JTextField emailT;
    JPasswordField userPassword, oldUserPassword, newUserPassword;
    JButton resetBtn, cancelBtn;

    LoginPage loginPage;

    public ResetPassword(LoginPage loginPage) {

        super("Reset Password");
        this.setSize(700, 450);
        this.setLocation(400, 200);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        this.loginPage = loginPage;

        // Label for page title 
        pageL = new JLabel("Reset Password");
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

        // Label for old password 
        oldUserPassL = new JLabel("Old Password");
        oldUserPassL.setBounds(150, 150, 100, 25);
        oldUserPassL.setFont(font16);
        this.add(oldUserPassL);

        // For old password field
        oldUserPassword = new JPasswordField();
        oldUserPassword.setBounds(150, 175, 400, 40);
        oldUserPassword.setEchoChar('*');
        oldUserPassword.setFont(font);
        this.add(oldUserPassword);

        // Label for new password 
        newUserPassL = new JLabel("New Password");
        newUserPassL.setBounds(150, 230, 200, 25);
        newUserPassL.setFont(font16);
        this.add(newUserPassL);

        // For new password field
        newUserPassword = new JPasswordField();
        newUserPassword.setBounds(150, 255, 400, 40);
        newUserPassword.setEchoChar('*');
        newUserPassword.setFont(font);
        this.add(newUserPassword);


        // For reset button
        resetBtn = new JButton("Reset Password");
        resetBtn.setBounds(150, 310, 190, 40);
        resetBtn.setFont(font16);
        resetBtn.setBackground(Color.BLUE);
        resetBtn.setForeground(Color.WHITE);
        resetBtn.addActionListener(this);
        this.add(resetBtn);

        // For reset button
        cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(360, 310, 190, 40);
        cancelBtn.setFont(font16);
        cancelBtn.setBackground(Color.RED);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.addActionListener(this);
        this.add(cancelBtn);

        this.setVisible(true);
    }

 
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == resetBtn) {
            String email = emailT.getText();
            String oldPassword = String.valueOf(oldUserPassword.getPassword());
            String newPassword = String.valueOf(newUserPassword.getPassword());
            
            Customer customer = loginPage.customerList.getCustomerByEmail(email);
            Banker banker = loginPage.bankerList.getBankerByEmail(email);
            
            boolean success = false;
            if (customer != null && loginPage.customerList.isValid(email, oldPassword)) {
                customer.setPassword(newPassword);
                FileIO.writeCustomerList(loginPage.customerList);
                success = true;
                
            }
            else if (banker != null && loginPage.bankerList.isValid(email, oldPassword)) {
                banker.setPassword(newPassword);
                FileIO.writeBankerList(loginPage.bankerList);
                success = true;
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Password reset successful");
                
                emailT.setText(""); 
                oldUserPassword.setText(""); 
                newUserPassword.setText(""); 

                this.setVisible(false);
                loginPage.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid email or password");
            }
        } 
        else if (evt.getSource() == cancelBtn) {
            this.setVisible(false);
            loginPage.setVisible(true);
        }
    }
}
