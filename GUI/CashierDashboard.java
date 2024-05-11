package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entity.person.*;
import entity.account.*;
import entityList.*;
import actionListener.*;

public class CashierDashboard extends JFrame implements ActionListener{
    JLabel pageL;
	JButton withdrawBtn;
	JButton depositBtn;
	JButton transferBalanceBtn;
	JButton checkBalanceBtn;
	JButton logoutBtn;

    Font font20 = new Font("Inter", Font.PLAIN, 20);
    Font font20b = new Font("Inter", Font.BOLD, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    Font font16b = new Font("Inter", Font.BOLD, 16);

    CustomerList customerList;

	public CashierDashboard (CustomerList customerList){

        super("Cashier Dashboard");
        this.setSize(1200, 700);
        this.setLocation(200, 70);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.customerList = customerList;
        
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

    JLabel secL, accountNumL, accountNameL, balanceL, amountL, msgL, newBalanceL;
    JTextField accountNumT, accountNameT, balanceT, amountT, newBalanceT;
    JButton WDinitiateBtn, WconfirmBtn, DconfirmBtn, exitBtn, cancelBtn;
    
    int accountNumber;
    Customer customer;
    Account account;

    String pressedBtn;
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawBtn) {
            pressedBtn = "withdraw";
        }
        else if (e.getSource() == depositBtn) {
            pressedBtn = "deposit";
        }
        else if (e.getSource() == transferBalanceBtn) {
            pressedBtn = "transferBalance";
        }
        else if (e.getSource() == checkBalanceBtn) {
            pressedBtn = "checkBalance";
        }


        if(e.getSource() == logoutBtn){
            this.dispose();
        }

        else if (e.getSource() == withdrawBtn || e.getSource() == depositBtn) {
            withdrawBtn.removeActionListener(this);
            
            if (e.getSource() == withdrawBtn) {
                withdrawBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("WITHDRAW");
                secL.setBounds(530, 270, 222, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "withdraw";
            }
            else if (e.getSource() == depositBtn) {
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

        else if (e.getSource() == WDinitiateBtn) {
            this.remove(WDinitiateBtn);
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


            // Withdraw Confirm Button
            if (pressedBtn.equals("withdraw")) {
                WconfirmBtn = new JButton("Confirm");
                WconfirmBtn.setBounds(310, 480, 530, 40);
                WconfirmBtn.setFont(font20);
                WconfirmBtn.setBackground(Color.RED);
                WconfirmBtn.setForeground(Color.WHITE);
                WconfirmBtn.addActionListener(this);
                this.add(WconfirmBtn);
            }

            // Deposit Confirm Button
            else if (pressedBtn.equals("deposit")) {
                DconfirmBtn = new JButton("Confirm");
                DconfirmBtn.setBounds(310, 480, 530, 40);
                DconfirmBtn.setFont(font20);
                DconfirmBtn.setBackground(Color.RED);
                DconfirmBtn.setForeground(Color.WHITE);
                DconfirmBtn.addActionListener(this);
                this.add(DconfirmBtn);
            }

            this.update(getGraphics());
        }

        else if (e.getSource() == WconfirmBtn || e.getSource() == DconfirmBtn) {
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
            if (e.getSource() == WconfirmBtn && success) {
                // Message
                msgL = new JLabel("Withdraw successful");
                msgL.setBounds(310, 500, 280, 36);
                msgL.setFont(font20b);
                msgL.setForeground(Color.GREEN);
                this.add(msgL);

            } else if (e.getSource() == WconfirmBtn && !success) {
                // Message
                msgL = new JLabel("Withdraw failed");
                msgL.setBounds(310, 500, 280, 36);
                msgL.setFont(font20b);
                msgL.setForeground(Color.RED);
                this.add(msgL);
            }


            // Deposit confirmation message
            if (e.getSource() == DconfirmBtn && success) {
                // Message
                msgL = new JLabel("Deposit successful");
                msgL.setBounds(310, 500, 280, 36);
                msgL.setFont(font20b);
                msgL.setForeground(Color.GREEN);
                this.add(msgL);

            } else if (e.getSource() == DconfirmBtn && !success) {
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
       
        else if (e.getSource() == exitBtn || e.getSource() == cancelBtn) {
            this.dispose();
            CashierDashboard cashierDashboard = new CashierDashboard(customerList);
        }

    }
	
	

}