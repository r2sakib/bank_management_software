package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Thread;

import entity.person.*;
import entity.account.*;
import entityList.*;
import GUI.*;
import file.*;

public class CashierDashboard extends JFrame implements ActionListener {
    JLabel pageL;
	JButton withdrawBtn, depositBtn, transferBalanceBtn, checkBalanceBtn, logoutBtn;

    Font font20 = new Font("Inter", Font.PLAIN, 20);
    Font font20b = new Font("Inter", Font.BOLD, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    Font font16b = new Font("Inter", Font.BOLD, 16);

    CustomerList customerList;
    LoginPage login;

	public CashierDashboard (CustomerList customerList, LoginPage login){
        super("Cashier Dashboard");
        this.setSize(1200, 800);
        this.setLocation(200, 10);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // LOGO
        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        this.customerList = customerList;
        this.login = login;

        // LOAD DATA
        FileIO.loadCustomerList(customerList);
        FileIO.loadAccounts(customerList);
        
        // Label for page title 
        pageL = new JLabel("Cashier Dashboard");
        pageL.setBounds(470, 30, 222, 36);
        pageL.setFont(font24b);
        this.add(pageL);
        
		// withdraw button 
		withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(310, 110, 210, 50);
        withdrawBtn.setFont(font20);
        withdrawBtn.setBackground(Color.BLACK);
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.addActionListener(this);
        this.add(withdrawBtn);

        // Deposit button
		depositBtn = new JButton("Deposit");
        depositBtn.setBounds(630, 110, 210, 50);
        depositBtn.setFont(font20);
        depositBtn.setBackground(Color.BLACK);
        depositBtn.setForeground(Color.WHITE);
        depositBtn.addActionListener(this);
        this.add(depositBtn);
		
		
		// Transfer Balance button 
		transferBalanceBtn = new JButton("Transfer Balance");
        transferBalanceBtn.setBounds(310, 190, 210, 50);
        transferBalanceBtn.setFont(font20);
        transferBalanceBtn.setBackground(Color.BLACK);
        transferBalanceBtn.setForeground(Color.WHITE);
        transferBalanceBtn.addActionListener(this);
        this.add(transferBalanceBtn);
		
		
		// Check Balance button
		checkBalanceBtn = new JButton("Check Balance");
        checkBalanceBtn.setBounds(630, 190, 210, 50);
        checkBalanceBtn.setFont(font20);
        checkBalanceBtn.setBackground(Color.BLACK);
        checkBalanceBtn.setForeground(Color.WHITE);
        checkBalanceBtn.addActionListener(this);
        this.add(checkBalanceBtn);
		
	
		// logout button
		logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(30, 30, 110, 40);
        logoutBtn.setFont(font20);
        logoutBtn.setBackground(Color.RED);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.addActionListener(this);
        this.add(logoutBtn);
		
		this.setVisible(true);	
	}

    JLabel secL, accountNumL, accountNameL, balanceL, amountL, msgL, newBalanceL, fromAccountNumL, toAccountNumL, fromAccountNameL, toAccountNameL;
    JTextField accountNumT, accountNameT, balanceT, amountT, newBalanceT, fromAccountNumT, toAccountNumT, fromAccountNameT, toAccountNameT;
    JButton WDinitiateBtn, WconfirmBtn, DconfirmBtn, exitBtn, cancelBtn, cBalanceBtn, tBalanceBtn, tInitiateBtn, tConfirmBtn;
    
    int accountNumber, fromAccountNumber, toAccountNumber;
    double amount;
    Customer customer, fromCustomer, toCustomer;
    Account account, fromAccount, toAccount;

    String pressedBtn;
	
	public void actionPerformed(ActionEvent evt) {
        try {
            if (evt.getSource() == withdrawBtn) {
                pressedBtn = "withdraw";
            }
            
            else if (evt.getSource() == depositBtn) {
                pressedBtn = "deposit";
            }
    
            if(evt.getSource() == logoutBtn){
                this.dispose();
                login.setVisible(true);
            }
    
            else if (evt.getSource() == withdrawBtn || evt.getSource() == depositBtn) {
                withdrawBtn.removeActionListener(this);
                
                if (evt.getSource() == withdrawBtn) {
                    withdrawBtn.setBackground(Color.GRAY);
    
                    // Section title
                    secL = new JLabel("WITHDRAW");
                    secL.setBounds(530, 270, 222, 36);
                    secL.setFont(font20b);
                    this.add(secL);
    
                    pressedBtn = "withdraw";
                }
                else if (evt.getSource() == depositBtn) {
                    depositBtn.setBackground(Color.GRAY);
    
                    // Section title
                    secL = new JLabel("DEPOSIT");
                    secL.setBounds(530, 270, 222, 36);
                    secL.setFont(font20b);
                    this.add(secL);
    
                    pressedBtn = "deposit";
                }
    
                // Label for account number 
                accountNumL = new JLabel("Account Number");
                accountNumL.setBounds(310, 315, 280, 25);
                accountNumL.setFont(font16b);
                this.add(accountNumL);
    
                // Text field for account number
                accountNumT = new JTextField();
                accountNumT.setBounds(310, 340, 280, 40);
                accountNumT.setFont(font20);
                this.add(accountNumT);
    
                // Label for Amount 
                amountL = new JLabel("Amount");
                amountL.setBounds(630, 315, 210, 25);
                amountL.setFont(font16b);
                this.add(amountL);
    
                // Text field for Amount
                amountT = new JTextField();
                amountT.setBounds(630, 340, 210, 40);
                amountT.setFont(font20);
                this.add(amountT);
    
                // Initiate Transfer Button
                WDinitiateBtn = new JButton("Initate");
                WDinitiateBtn.setBounds(310, 395, 255, 40);
                WDinitiateBtn.setFont(font20);
                WDinitiateBtn.setBackground(Color.BLUE);
                WDinitiateBtn.setForeground(Color.WHITE);
                WDinitiateBtn.addActionListener(this);
                this.add(WDinitiateBtn);
    
                // Cancel Button
                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(580, 395, 258, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
    
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == WDinitiateBtn) {
                this.remove(WDinitiateBtn);
                
                accountNumber = Integer.parseInt(accountNumT.getText());
                customer = customerList.getCustomerByAccountNumber(accountNumber);
                account = customer.getAccount(accountNumber);
    
                // Label for account name 
                accountNameL = new JLabel("Account Name");
                accountNameL.setBounds(310, 395, 280, 25);
                accountNameL.setFont(font16b);
                this.add(accountNameL);
                
                // Text field for account name
                accountNameT = new JTextField();
                accountNameT.setBounds(310, 420, 280, 40);
                accountNameT.setFont(font20);
                accountNameT.setText(customer.getName());
                accountNameT.setEditable(false);
                this.add(accountNameT);
    
                // Label for balance 
                balanceL = new JLabel("Balance");
                balanceL.setBounds(630, 395, 210, 25);
                balanceL.setFont(font16b);
                this.add(balanceL);
    
                String balance = String.valueOf(account.getBalance());
                
                // Text field for balance
                balanceT = new JTextField();
                balanceT.setBounds(630, 420, 210, 40);
                balanceT.setFont(font20);
                balanceT.setText(balance);
                balanceT.setEditable(false);
                this.add(balanceT);
    
    
                // Withdraw Confirm Button
                if (pressedBtn.equals("withdraw")) {
                    WconfirmBtn = new JButton("Confirm");
                    WconfirmBtn.setBounds(310, 480, 255, 40);
                    WconfirmBtn.setFont(font20);
                    WconfirmBtn.setBackground(Color.GREEN);
                    WconfirmBtn.setForeground(Color.WHITE);
                    WconfirmBtn.addActionListener(this);
                    this.add(WconfirmBtn);
                }
    
                // Deposit Confirm Button
                else if (pressedBtn.equals("deposit")) {
                    DconfirmBtn = new JButton("Confirm");
                    DconfirmBtn.setBounds(310, 480, 255, 40);
                    DconfirmBtn.setFont(font20);
                    DconfirmBtn.setBackground(Color.BLUE);
                    DconfirmBtn.setForeground(Color.WHITE);
                    DconfirmBtn.addActionListener(this);
                    this.add(DconfirmBtn);
                }
    
                cancelBtn.setBounds(580, 480, 258, 40);
                this.add(cancelBtn);
                
                this.update(getGraphics());
            }
            
            else if (evt.getSource() == WconfirmBtn || evt.getSource() == DconfirmBtn) {
                this.remove(cancelBtn);

                double amount = Double.parseDouble(amountT.getText());
                boolean success = false;
    
                if (pressedBtn == "withdraw") {
                    success = account.withdraw(amount);
                    this.remove(WconfirmBtn);
                }
                else if (pressedBtn == "deposit") {
                    success = account.deposit(amount);
                    this.remove(DconfirmBtn);
                }
    
                // Withdraw confirmation message
                if (evt.getSource() == WconfirmBtn && success) {
                    FileIO.writeAccounts(customerList);
                    // Thread.sleep(1000);

                    // Message
                    msgL = new JLabel("Withdraw successful");
                    msgL.setBounds(310, 500, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.GREEN);
                    this.add(msgL);
    
                } else if (evt.getSource() == WconfirmBtn && !success) {
                    // Message
                    msgL = new JLabel("Withdraw failed");
                    msgL.setBounds(310, 500, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.RED);
                    this.add(msgL);
                }
    
                // Deposit confirmation message
                if (evt.getSource() == DconfirmBtn && success) {
                    FileIO.writeAccounts(customerList);
                    // Thread.sleep(1000);

                    // Message
                    msgL = new JLabel("Deposit successful");
                    msgL.setBounds(310, 500, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.GREEN);
                    this.add(msgL);
    
                } else if (evt.getSource() == DconfirmBtn && !success) {
                    // Message
                    msgL = new JLabel("Deposit failed");
                    msgL.setBounds(310, 500, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.RED);
                    this.add(msgL);
                }
                
                // Label for balance 
                newBalanceL = new JLabel("New Balance");
                newBalanceL.setBounds(630, 475, 210, 25);
                newBalanceL.setFont(font16b);
                this.add(newBalanceL);
    
                // Text field for balance
                String balance = String.valueOf(account.getBalance());
    
                newBalanceT = new JTextField();
                newBalanceT.setBounds(630, 500, 210, 40);
                newBalanceT.setFont(font20);
                newBalanceT.setText(balance);
                newBalanceT.setEditable(false);
                this.add(newBalanceT);
    
                // Exit Button
                exitBtn = new JButton("Exit");
                exitBtn.setBounds(310, 550, 530, 40);
                exitBtn.setFont(font20);
                exitBtn.setBackground(Color.RED);
                exitBtn.setForeground(Color.WHITE);
                exitBtn.addActionListener(this);
                this.add(exitBtn);
                
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == transferBalanceBtn) {
                transferBalanceBtn.setBackground(Color.GRAY);
    
                // Section title
                secL = new JLabel("TRANSFER BALANCE");
                secL.setBounds(480, 270, 222, 36);
                secL.setFont(font20b);
                this.add(secL);
    
                // Label for account number 
                fromAccountNumL = new JLabel("From Account Number");
                fromAccountNumL.setBounds(310, 315, 280, 25);
                fromAccountNumL.setFont(font16b);
                this.add(fromAccountNumL);
    
                // Text field for account number
                fromAccountNumT = new JTextField();
                fromAccountNumT.setBounds(310, 340, 280, 40);
                fromAccountNumT.setFont(font20);
                this.add(fromAccountNumT);
    
                // Label for Amount 
                amountL = new JLabel("Amount");
                amountL.setBounds(630, 315, 210, 25);
                amountL.setFont(font16b);
                this.add(amountL);
    
                // Text field for Amount
                amountT = new JTextField();
                amountT.setBounds(630, 340, 210, 40);
                amountT.setFont(font20);
                this.add(amountT);
    
                // Label for to account name 
                toAccountNumL = new JLabel("To Account Number");
                toAccountNumL.setBounds(310, 395, 280, 25);
                toAccountNumL.setFont(font16b);
                this.add(toAccountNumL);
                
                // Text field for to account number
                toAccountNumT = new JTextField();
                toAccountNumT.setFont(font20);
                toAccountNumT.setBounds(310, 420, 530, 40);
                this.add(toAccountNumT);
    
                // Initiate Transfer Button
                tInitiateBtn = new JButton("Initate");
                tInitiateBtn.setFont(font20);
                tInitiateBtn.setBackground(Color.BLUE);
                tInitiateBtn.setBounds(310, 480, 255, 40);
                tInitiateBtn.setForeground(Color.WHITE);
                tInitiateBtn.addActionListener(this);
                this.add(tInitiateBtn);
    
                // Cancel Button
                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(580, 480, 258, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
    
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == tInitiateBtn) {
                this.remove(tInitiateBtn);
                this.remove(cancelBtn);
                toAccountNumL.setBounds(310, 470, 280, 25);
                toAccountNumT.setBounds(310, 495, 530, 40);
    
                fromAccountNumber = Integer.parseInt(fromAccountNumT.getText());
                toAccountNumber = Integer.parseInt(toAccountNumT.getText());
                amount = Double.parseDouble(amountT.getText());
    
                fromCustomer = customerList.getCustomerByAccountNumber(fromAccountNumber);
                fromAccount = fromCustomer.getAccount(fromAccountNumber);
    
                toCustomer = customerList.getCustomerByAccountNumber(toAccountNumber);
                toAccount = toCustomer.getAccount(toAccountNumber);
    
                // Label for from account name 
                fromAccountNameL = new JLabel("From Account Name");
                fromAccountNameL.setBounds(310, 385, 280, 25);
                fromAccountNameL.setFont(font16b);
                this.add(fromAccountNameL);
                
                // Text field for from account name
                fromAccountNameT = new JTextField();
                fromAccountNameT.setBounds(310, 410, 280, 40);
                fromAccountNameT.setFont(font20);
                fromAccountNameT.setText(fromCustomer.getName());
                fromAccountNameT.setEditable(false);
                this.add(fromAccountNameT);
    
                // Label for from account balance 
                balanceL = new JLabel("Balance");
                balanceL.setBounds(630, 385, 210, 25);
                balanceL.setFont(font16b);
                this.add(balanceL);
    
                String balance = String.valueOf(fromAccount.getBalance());
                
                // Text field for from account balance
                balanceT = new JTextField();
                balanceT.setBounds(630, 410, 210, 40);
                balanceT.setFont(font20);
                balanceT.setText(balance);
                balanceT.setEditable(false);
                this.add(balanceT);
    
                // Label for to account name 
                toAccountNameL = new JLabel("To Account Name");
                toAccountNameL.setBounds(310, 540, 530, 25);
                toAccountNameL.setFont(font16b);
                this.add(toAccountNameL);
                
                // Text field for to account name
                toAccountNameT = new JTextField();
                toAccountNameT.setBounds(310, 565, 530, 40);
                toAccountNameT.setFont(font20);
                toAccountNameT.setText(toCustomer.getName());
                toAccountNameT.setEditable(false);
                this.add(toAccountNameT);
    
                tConfirmBtn = new JButton("Confirm");
                tConfirmBtn.setBounds(310, 620, 255, 40);
                tConfirmBtn.setFont(font20);
                tConfirmBtn.setBackground(Color.GREEN);
                tConfirmBtn.setForeground(Color.WHITE);
                tConfirmBtn.addActionListener(this);
                this.add(tConfirmBtn);
    
                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(580, 620, 258, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
    
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == tConfirmBtn) {
                this.remove(tConfirmBtn);
                this.remove(cancelBtn);
    
                boolean success = fromAccount.transfer(toAccount, amount);
    
                if (success) {
                    FileIO.writeAccounts(customerList);

                    // Message
                    msgL = new JLabel("Transfer successful");
                    msgL.setBounds(310, 640, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.GREEN);
                    this.add(msgL);
    
                } else {
                    // Message
                    msgL = new JLabel("Transfer failed");
                    msgL.setBounds(310, 640, 280, 36);
                    msgL.setFont(font20b);
                    msgL.setForeground(Color.RED);
                    this.add(msgL);
                }
    
                // Label for from account balance 
                newBalanceL = new JLabel("New From Account Balance");
                newBalanceL.setBounds(630, 620, 210, 25);
                newBalanceL.setFont(font16b);
                this.add(newBalanceL);
    
                // Text field for from account balance
                String balance = String.valueOf(fromAccount.getBalance());
    
                newBalanceT = new JTextField();
                newBalanceT.setBounds(630, 645, 210, 40);
                newBalanceT.setFont(font20);
                newBalanceT.setText(balance);
                newBalanceT.setEditable(false);
                this.add(newBalanceT);
    
                // Exit Button
                exitBtn = new JButton("Exit");
                exitBtn.setBounds(310, 700, 530, 40);
                exitBtn.setFont(font20);
                exitBtn.setBackground(Color.RED);
                exitBtn.setForeground(Color.WHITE);
                exitBtn.addActionListener(this);
                this.add(exitBtn);
    
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == checkBalanceBtn) {
                checkBalanceBtn.setBackground(Color.GRAY);
    
                // Section title
                secL = new JLabel("CHECK BALANCE");
                secL.setBounds(500, 270, 222, 36);
                secL.setFont(font20b);
                this.add(secL);
    
                // Label for account number 
                accountNumL = new JLabel("Account Number");
                accountNumL.setBounds(310, 315, 530, 25);
                accountNumL.setFont(font16b);
                this.add(accountNumL);
    
                // Text field for account number
                accountNumT = new JTextField();
                accountNumT.setBounds(310, 340, 530, 40);
                accountNumT.setFont(font20);
                this.add(accountNumT);
    
                // Check balance Button
                cBalanceBtn = new JButton("Check Balance");
                cBalanceBtn.setBounds(310, 395, 255, 40);
                cBalanceBtn.setFont(font20);
                cBalanceBtn.setBackground(Color.BLUE);
                cBalanceBtn.setForeground(Color.WHITE);
                cBalanceBtn.addActionListener(this);
                this.add(cBalanceBtn);
    
                // Cancel Button
                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(580, 395, 260, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
    
                this.update(getGraphics());
            }
    
            else if (evt.getSource() == cBalanceBtn) {
                this.remove(cBalanceBtn);
                this.remove(cancelBtn);
                
                accountNumber = Integer.parseInt(accountNumT.getText());
                customer = customerList.getCustomerByAccountNumber(accountNumber);
                account = customer.getAccount(accountNumber);
    
                // Label for account name 
                accountNameL = new JLabel("Account Name");
                accountNameL.setBounds(310, 395, 280, 25);
                accountNameL.setFont(font16b);
                this.add(accountNameL);
                
                // Text field for account name
                accountNameT = new JTextField();
                accountNameT.setBounds(310, 420, 280, 40);
                accountNameT.setFont(font20);
                accountNameT.setText(customer.getName());
                accountNameT.setEditable(false);
                this.add(accountNameT);
    
                // Label for balance 
                balanceL = new JLabel("Balance");
                balanceL.setBounds(630, 395, 210, 25);
                balanceL.setFont(font16b);
                this.add(balanceL);
    
                String balance = String.valueOf(account.getBalance());
                
                // Text field for balance
                balanceT = new JTextField();
                balanceT.setBounds(630, 420, 210, 40);
                balanceT.setFont(font20);
                balanceT.setText(balance);
                balanceT.setEditable(false);
                this.add(balanceT);
    
                // Exit Button
                exitBtn = new JButton("Exit");
                exitBtn.setBounds(310, 480, 530, 40);
                exitBtn.setFont(font20);
                exitBtn.setBackground(Color.RED);
                exitBtn.setForeground(Color.WHITE);
                exitBtn.addActionListener(this);
                this.add(exitBtn);
    
                this.update(getGraphics());
            }
           
            else if (evt.getSource() == exitBtn || evt.getSource() == cancelBtn) {
                this.dispose();
                CashierDashboard cashierDashboard = new CashierDashboard(customerList, login);
            }
        }

        catch(Exception expt) {
            JOptionPane.showMessageDialog(null, expt.getMessage());
        }
    }
}