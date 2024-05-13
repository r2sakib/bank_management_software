package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import entity.person.*;
import entity.account.*;
import entityList.*;
import GUI.*;
import file.*;

public class CustomerDashboard extends JFrame implements ActionListener{
    JLabel pageL, label;

    Font font20 = new Font("Inter", Font.PLAIN, 20);
    Font font20b = new Font("Inter", Font.BOLD, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    Font font16b = new Font("Inter", Font.BOLD, 16);

    String customerNID;
    Customer customer;
    ArrayList<Account> accounts;
    CustomerList customerList;
    LoginPage loginPage;

    JButton fundTransferBtn, logoutBtn;

	public CustomerDashboard (CustomerList customerList, String customerNID) {
        super("Customer Dashboard");
        this.setSize(1000, 800);
        this.setLocation(300, 10);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // SET ICON
        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        // LOAD DATA
        FileIO.loadCustomerList(customerList);
		FileIO.loadAccounts(customerList);

        this.customerList = customerList;
        this.customerNID = customerNID;
        this.customer = customerList.getCustomerByNid(customerNID);
        this.accounts = customer.getAccounts();

        this.loginPage = loginPage;

        int vgap = 80;
        int hgapL = 100;
        int hgapR = 450;
        int width = 300;
        int height = 36;
        
        // Label for page title 
        pageL = new JLabel("Customer Dashboard");
        pageL.setBounds(370, 30, width, height);
        pageL.setFont(font24b);
        this.add(pageL);

        // Label for welcome 
        label = new JLabel("Welcome, " + customer.getName());
        label.setBounds(hgapL, vgap, width, height);
        label.setFont(font20b);
        this.add(label);

        vgap += 50;
        // Label for accounts
        label = new JLabel("Accounts");
        label.setBounds(hgapL, vgap, width, height);
        label.setFont(font20b);
        this.add(label);


        for (int i = 0; i < accounts.size(); i++) {

            if (i == 0) {
                vgap += 40;
                label = new JLabel("Savings Accounts");
                label.setBounds(hgapL+30, vgap, width, height);
                label.setFont(font16b);
                this.add(label);

                label = new JLabel("Balance");
                label.setBounds(hgapR, vgap, width, height);
                label.setFont(font16b);
                this.add(label);
            }

            if (accounts.get(i) instanceof Savings) {

                String accountNum = String.valueOf(accounts.get(i).getAccountNumber());
                String balance = String.valueOf(accounts.get(i).getBalance());

                vgap += 30;
                label = new JLabel(accountNum);
                label.setBounds(hgapL+30, vgap, width, height);
                label.setFont(font16);
                this.add(label);

                label = new JLabel(balance);
                label.setBounds(hgapR, vgap, width, height);
                label.setFont(font16);
                this.add(label);
            }
        }

        for (int i = 0; i < accounts.size(); i++) {

            if (i == 0) {
                vgap += 40;
                label = new JLabel("Fixed Deposit Accounts");
                label.setBounds(hgapL+30, vgap, width, height);
                label.setFont(font16b);
                this.add(label);

                label = new JLabel("Balance");
                label.setBounds(hgapR, vgap, width, height);
                label.setFont(font16b);
                this.add(label);
            }
        
            if (accounts.get(i) instanceof FixedDeposit) {

                String accountNum = String.valueOf(accounts.get(i).getAccountNumber());
                String balance = String.valueOf(accounts.get(i).getBalance());

                vgap += 30;
                label = new JLabel(accountNum);
                label.setBounds(hgapL+30, vgap, width, height);
                label.setFont(font16);
                this.add(label);

                label = new JLabel(balance);
                label.setBounds(hgapR, vgap, width, height);
                label.setFont(font16);
                this.add(label);
            }
        }

        fundTransferBtn = new JButton("Fund Transfer");
        fundTransferBtn.setBounds(400, 380, 200, 40);
        fundTransferBtn.setFont(font20);
        fundTransferBtn.setBackground(Color.BLACK);
        fundTransferBtn.setForeground(Color.WHITE);
        fundTransferBtn.addActionListener(this);
        this.add(fundTransferBtn);

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

    public String[] getAccountNumAsString() {
        ArrayList <String> accountNums = new ArrayList<String>();

        for (int j = 0; j < accounts.size(); j++) {
            if (accounts.get(j) instanceof Savings) {
                accountNums.add(String.valueOf(accounts.get(j).getAccountNumber()));
            }
        }
        String [] accountNumsArr = new String[accountNums.size()];
        accountNumsArr[0] = "";

        return accountNums.toArray(accountNumsArr);
    }


    JTextField amountT, toAccountNumT, toAccountNameT;
    JButton initiateBtn, cancelBtn, confirmBtn;
    JComboBox accountSelectCB;

    public void actionPerformed(ActionEvent evt) {
    try {
        if(evt.getSource() == logoutBtn){
            this.dispose();
            loginPage.setVisible(true);
        }

        int vgap = 440;
        int hgapL = 200;
        int hgapR = 500;
        int width = 250;

        if (evt.getSource() == fundTransferBtn) {
            // JOB TITLE 
            label = new JLabel("Select Account");
            label.setBounds(hgapL, vgap, width, 25);
            label.setFont(font16b);
            this.add(label);

            accountSelectCB = new JComboBox<>(getAccountNumAsString());
            accountSelectCB.setBounds(hgapL, vgap+25, width, 40);
            accountSelectCB.setFont(font16);
            this.add(accountSelectCB);

            // AMOUNT 
            label = new JLabel("Amount");
            label.setBounds(hgapR, vgap, width, 25);
            label.setFont(font16b);
            this.add(label);

            amountT = new JTextField();
            amountT.setBounds(hgapR, vgap+25, width, 40);
            amountT.setFont(font16);
            this.add(amountT);

            vgap += 80;

            // TO ACCOUNT 
            label = new JLabel("To Account Number");
            label.setBounds(hgapL, vgap, width, 25);
            label.setFont(font16b);
            this.add(label);
            
            toAccountNumT = new JTextField();
            toAccountNumT.setBounds(hgapL, vgap+25, width, 40);
            toAccountNumT.setFont(font16);
            this.add(toAccountNumT);
            
            label = new JLabel("To Account Name");
            label.setBounds(hgapR, vgap, width, 25);
            label.setFont(font16b);
            this.add(label);
            
            toAccountNameT = new JTextField();
            toAccountNameT.setBounds(hgapR, vgap+25, width, 40);
            toAccountNameT.setFont(font16);
            toAccountNameT.setEditable(false);
            this.add(toAccountNameT);

            vgap += 80;
            // INITIATE BUTTON
            initiateBtn = new JButton("Initate");
            initiateBtn.setBounds(hgapL, vgap, width, 40);
            initiateBtn.setFont(font20);
            initiateBtn.setBackground(Color.BLUE);
            initiateBtn.setForeground(Color.WHITE);
            initiateBtn.addActionListener(this);
            this.add(initiateBtn);

            // CANCEL BUTTON
            cancelBtn = new JButton("Cancel");
            cancelBtn.setBounds(hgapR, vgap, width, 40);
            cancelBtn.setFont(font20);
            cancelBtn.setBackground(Color.RED);
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.addActionListener(this);
            this.add(cancelBtn);
            

            this.update(getGraphics());
        }

        else if (evt.getSource() == initiateBtn) {
            this.remove(initiateBtn);
            
            int toAccountNum = Integer.parseInt(toAccountNumT.getText());
            Customer toCustomer = customerList.getCustomerByAccountNumber(toAccountNum);
            toAccountNameT.setText(toCustomer.getName());

            vgap += 160;

            confirmBtn = new JButton("Confirm");
            confirmBtn.setBounds(hgapL, vgap, width, 40);
            confirmBtn.setFont(font20);
            confirmBtn.setBackground(Color.BLUE);
            confirmBtn.setForeground(Color.WHITE);
            confirmBtn.addActionListener(this);
            this.add(confirmBtn);

            this.update(getGraphics());
        }

        else if (evt.getSource() == confirmBtn) {
            int accountNum = Integer.parseInt(accountSelectCB.getSelectedItem().toString());
            Account fromAccount = customer.getAccount(accountNum);
            double amount = Double.parseDouble(amountT.getText());
            int toAccountNum = Integer.parseInt(toAccountNumT.getText());
            Account toAccount = customerList.getAccount(toAccountNum);
            
            if (fromAccount.getBalance() < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } 
            else if (toAccount == null) {
                JOptionPane.showMessageDialog(this, "Account not found", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                boolean success = fromAccount.transfer(toAccount, amount);;
                FileIO.writeAccounts(customerList);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Transfer Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Transfer Failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }


            this.dispose();
            new CustomerDashboard(customerList, customerNID);
        }

        else if (evt.getSource() == cancelBtn) {
            this.dispose();
            new CustomerDashboard(customerList, customerNID);
        }
    }
    catch (Exception expt) {
        JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        System.out.println("Error [CustomerDashboard: actionListener] " + "\n\n" + expt + "\n\n");
    }
}
}

