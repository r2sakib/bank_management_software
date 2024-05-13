package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entity.person.*;
import entity.account.*;
import entityList.*;
import GUI.*;
import file.*;

public class ManagerDashboard extends JFrame implements ActionListener {
    JLabel pageL;
	JButton createCustomerBtn, removeCustomerBtn, createAccountBtn, removeAccountBtn, logoutBtn;
    JButton createBankerBtn, removeBankerBtn, editCustomerInfoBtn, editBankerInfoBtn;

    Font font20 = new Font("Inter", Font.PLAIN, 20);
    Font font20b = new Font("Inter", Font.BOLD, 20);
    Font font24b = new Font("Inter", Font.BOLD, 24);
    Font font16 = new Font("Inter", Font.PLAIN, 16);
    Font font16b = new Font("Inter", Font.BOLD, 16);

    CustomerList customerList;
    BankerList bankerList;
    LoginPage loginPage;

	public ManagerDashboard (CustomerList customerList, BankerList bankerList, LoginPage loginPage){
        super("Manager Dashboard");
        this.setSize(1200, 800);
        this.setLocation(200, 10);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // SET ICON
        ImageIcon img = new ImageIcon("./resources/logo.png");
        this.setIconImage(img.getImage());

        // LOAD DATA
        FileIO.loadCustomerList(customerList);
		FileIO.loadAccounts(customerList);
		FileIO.loadBankerList(bankerList);

        this.customerList = customerList;
        this.bankerList = bankerList;
        this.loginPage = loginPage;
        
        // Label for page title 
        pageL = new JLabel("Manager Dashboard");
        pageL.setBounds(470, 30, 300, 36);
        pageL.setFont(font24b);
        this.add(pageL);
        
		// Create Customer button 
		createCustomerBtn = new JButton("Create Customer");
        createCustomerBtn.setBounds(100, 120, 210, 50);
        createCustomerBtn.setFont(font20);
        createCustomerBtn.setBackground(Color.BLACK);
        createCustomerBtn.setForeground(Color.WHITE);
        createCustomerBtn.addActionListener(this);
        this.add(createCustomerBtn);

        // Remove Customer button
		removeCustomerBtn = new JButton("Remove Customer");
        removeCustomerBtn.setBounds(355, 120, 210, 50);
        removeCustomerBtn.setFont(font20);
        removeCustomerBtn.setBackground(Color.BLACK);
        removeCustomerBtn.setForeground(Color.WHITE);
        removeCustomerBtn.addActionListener(this);
        this.add(removeCustomerBtn);
		
		// Create Account button 
		createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(610, 120, 210, 50);
        createAccountBtn.setFont(font20);
        createAccountBtn.setBackground(Color.BLACK);
        createAccountBtn.setForeground(Color.WHITE);
        createAccountBtn.addActionListener(this);
        this.add(createAccountBtn);
		
		// Remove Account button
		removeAccountBtn = new JButton("Remove Account");
        removeAccountBtn.setBounds(870, 120, 210, 50);
        removeAccountBtn.setFont(font20);
        removeAccountBtn.setBackground(Color.BLACK);
        removeAccountBtn.setForeground(Color.WHITE);
        removeAccountBtn.addActionListener(this);
        this.add(removeAccountBtn);
		
        // Create Banker button 
		createBankerBtn = new JButton("Create Banker");
        createBankerBtn.setBounds(100, 210, 210, 50);
        createBankerBtn.setFont(font20);
        createBankerBtn.setBackground(Color.BLACK);
        createBankerBtn.setForeground(Color.WHITE);
        createBankerBtn.addActionListener(this);
        this.add(createBankerBtn);

        // Remove Banker button
		removeBankerBtn = new JButton("Remove Banker");
        removeBankerBtn.setBounds(355, 210, 210, 50);
        removeBankerBtn.setFont(font20);
        removeBankerBtn.setBackground(Color.BLACK);
        removeBankerBtn.setForeground(Color.WHITE);
        removeBankerBtn.addActionListener(this);
        this.add(removeBankerBtn);
		
		// Edit Customer Info button 
		editCustomerInfoBtn = new JButton("Edit Customer Info");
        editCustomerInfoBtn.setBounds(610, 210, 210, 50);
        editCustomerInfoBtn.setFont(font20);
        editCustomerInfoBtn.setBackground(Color.BLACK);
        editCustomerInfoBtn.setForeground(Color.WHITE);
        editCustomerInfoBtn.addActionListener(this);
        this.add(editCustomerInfoBtn);
		
		// Edit Banker Info button
		editBankerInfoBtn = new JButton("Edit Banker Info");
        editBankerInfoBtn.setBounds(870, 210, 210, 50);
        editBankerInfoBtn.setFont(font20);
        editBankerInfoBtn.setBackground(Color.BLACK);
        editBankerInfoBtn.setForeground(Color.WHITE);
        editBankerInfoBtn.addActionListener(this);
        this.add(editBankerInfoBtn);

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

    private void removeAllActionListeners() {
        createCustomerBtn.removeActionListener(this);
        removeCustomerBtn.removeActionListener(this);
        createAccountBtn.removeActionListener(this);
        removeAccountBtn.removeActionListener(this);
        createBankerBtn.removeActionListener(this);
        removeBankerBtn.removeActionListener(this);
        editCustomerInfoBtn.removeActionListener(this);
        editBankerInfoBtn.removeActionListener(this);
    }


    JLabel secL, nameL, nidL, birthYearL, addressL, mobileNumberL, emailL, jobTitleL, accountNumL, accountTypeL, tenureYearL, interestRateL;
    JTextField nameT, nidT, birthYearT, addressT, mobileNumberT, emailT, jobTitleT, accountNumT, tenureYearT, interestRateT;
    JButton CCBconfirmBtn, cancelBtn, RCBconfimBtn, RCBinitiateBtn, ECBinitiatieBtn, ECBconfirmBtn;  // CCB: Create Customer, Banker; RCB: Remove Customer Banker
    JButton createAccountInitiateBtn, createAccountConfirmBtn, removeAccountInitiateBtn, removeAccountConfirmBtn; 
    JComboBox jobTitleCB, accountTypeCB;

    String pressedBtn;
    boolean isByNid = true;
	
	public void actionPerformed(ActionEvent evt) {


        // LOGOUT
        if(evt.getSource() == logoutBtn){
            this.dispose();
            loginPage.setVisible(true);
        }

        // CREATE CUSTOMER OR BANKER

        else if (evt.getSource() == createCustomerBtn || evt.getSource() == createBankerBtn) {
            try {
                createCustomerBtn.removeActionListener(this);
                
                if (evt.getSource() == createCustomerBtn) {
                    createCustomerBtn.setBackground(Color.GRAY);

                    // Section title
                    secL = new JLabel("CREATE NEW CUSTOMER");
                    secL.setBounds(450, 290, 300, 36);
                    secL.setFont(font20b);
                    this.add(secL);

                    pressedBtn = "createCustomer";
                }
                else if (evt.getSource() == createBankerBtn) {
                    createBankerBtn.setBackground(Color.GRAY);

                    // Section title
                    secL = new JLabel("CREATE NEW BANKER");
                    secL.setBounds(470, 290, 300, 36);
                    secL.setFont(font20b);
                    this.add(secL);

                    pressedBtn = "createBanker";
                }
                int vgap = 350;
                int hgapL = 200;
                int hgapR = 650;
                int width = 350;

                // NAME 
                nameL = new JLabel("Name");
                nameL.setBounds(hgapL, vgap, width, 25);
                nameL.setFont(font16b);
                this.add(nameL);

                nameT = new JTextField();
                nameT.setBounds(hgapL, vgap+25, width, 40);
                nameT.setFont(font20);
                this.add(nameT);

                // NID 
                nidL = new JLabel("NID Number");
                nidL.setBounds(hgapR, vgap, width, 25);
                nidL.setFont(font16b);
                this.add(nidL);

                nidT = new JTextField();
                nidT.setBounds(hgapR, vgap+25, width, 40);
                nidT.setFont(font20);
                this.add(nidT);

                vgap += 80;

                // BIRTH YEAR 
                birthYearL = new JLabel("Birth Year");
                birthYearL.setBounds(hgapL, vgap, width, 25);
                birthYearL.setFont(font16b);
                this.add(birthYearL);

                birthYearT = new JTextField();
                birthYearT.setBounds(hgapL, vgap+25, width, 40);
                birthYearT.setFont(font20);
                this.add(birthYearT);

                // MOBILE NUMBER 
                mobileNumberL = new JLabel("Mobile Number");
                mobileNumberL.setBounds(hgapR, vgap, width, 25);
                mobileNumberL.setFont(font16b);
                this.add(mobileNumberL);

                mobileNumberT = new JTextField();
                mobileNumberT.setBounds(hgapR, vgap+25, width, 40);
                mobileNumberT.setFont(font20);
                this.add(mobileNumberT);

                vgap += 80;

                // ADDRESS
                addressL = new JLabel("Address");
                addressL.setBounds(hgapL, vgap, width, 25);
                addressL.setFont(font16b);
                this.add(addressL);

                addressT = new JTextField();
                addressT.setBounds(hgapL, vgap+25, width, 40);
                addressT.setFont(font20);
                this.add(addressT);

                // EMAIL
                emailL = new JLabel("Email");
                emailL.setBounds(hgapR, vgap, width, 25);
                emailL.setFont(font16b);
                this.add(emailL);

                emailT = new JTextField();
                emailT.setBounds(hgapR, vgap+25, width, 40);
                emailT.setFont(font20);
                this.add(emailT);

                if (evt.getSource() == createBankerBtn) {
                    vgap += 80;

                    // JOB TITLE 
                    jobTitleL = new JLabel("Job Title");
                    jobTitleL.setBounds(hgapL, vgap, width, 25);
                    jobTitleL.setFont(font16b);
                    this.add(jobTitleL);

                    // JOB TITLE COMBO BOX
                    jobTitleCB = new JComboBox<>(new String[]{"Cashier", "Manager"});
                    jobTitleCB.setBounds(hgapL, vgap+25, width, 40);
                    jobTitleCB.setFont(font20);
                    jobTitleCB.setBackground(Color.WHITE);
                    this.add(jobTitleCB);
                }

                vgap += 80;
                CCBconfirmBtn = new JButton("Confirm");
                CCBconfirmBtn.setBounds(hgapL, vgap, width, 40);
                CCBconfirmBtn.setFont(font20);
                CCBconfirmBtn.setBackground(Color.BLUE);
                CCBconfirmBtn.setForeground(Color.WHITE);
                CCBconfirmBtn.addActionListener(this);
                this.add(CCBconfirmBtn);

                cancelBtn = new JButton("Cancel");
                cancelBtn.setBounds(hgapR, vgap, width, 40);
                cancelBtn.setFont(font20);
                cancelBtn.setBackground(Color.RED);
                cancelBtn.setForeground(Color.WHITE);
                cancelBtn.addActionListener(this);
                this.add(cancelBtn);
                
                this.update(getGraphics());
            }
            catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
                System.out.println("Error [ManagerDashboard: createCustomerBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        else if (evt.getSource() == CCBconfirmBtn) {
            try {
                if (pressedBtn == "createCustomer") {
                    String name = nameT.getText();
                    String nid = nidT.getText();
                    int birthYear = Integer.parseInt(birthYearT.getText());
                    String address = addressT.getText();
                    String mobileNumber = mobileNumberT.getText();
                    String email = emailT.getText();

                    Customer customer = new Customer(name, nid, birthYear, address, mobileNumber, email, "1234");
                    customerList.addCustomer(customer);
                    FileIO.writeCustomerList(customerList);

                    nameT.setText("");
                    nidT.setText("");
                    birthYearT.setText("");
                    addressT.setText("");
                    mobileNumberT.setText("");
                    emailT.setText("");
                    cancelBtn.setText("Exit");
                }
                else if (pressedBtn == "createBanker") {
                    String name = nameT.getText();
                    String nid = nidT.getText();
                    int birthYear = Integer.parseInt(birthYearT.getText());
                    String address = addressT.getText();
                    String mobileNumber = mobileNumberT.getText();
                    String email = emailT.getText();
                    String jobTitle = jobTitleCB.getSelectedItem().toString();

                    Banker banker = new Banker(name, nid, birthYear, address, mobileNumber, email, "1234", jobTitle);
                    bankerList.addBanker(banker);
                    FileIO.writeBankerList(bankerList);
                    
                    nameT.setText("");
                    nidT.setText("");
                    birthYearT.setText("");
                    addressT.setText("");
                    mobileNumberT.setText("");
                    emailT.setText("");
                    cancelBtn.setText("Exit");
                }

                JOptionPane.showMessageDialog(this, "Success.");                
            }
            catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error. Invalid Input.");
                System.out.println("Error [ManagerDashboard: CCBconfirmBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        // REMOVE CUSTOMER OR BANKER

        else if (evt.getSource() == removeCustomerBtn || evt.getSource() == removeBankerBtn) {
            removeCustomerBtn.removeActionListener(this);
            
            if (evt.getSource() == removeCustomerBtn) {
                removeCustomerBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("REMOVE CUSTOMER");
                secL.setBounds(480, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "removeCustomer";
            }
            else if (evt.getSource() == removeBankerBtn) {
                removeBankerBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("REMOVE BANKER");
                secL.setBounds(490, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "removeBanker";
            }
            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            // NID 
            nidL = new JLabel("NID Number or Email");
            nidL.setBounds(hgapL, vgap, width, 25);
            nidL.setFont(font16b);
            this.add(nidL);

            nidT = new JTextField();
            nidT.setBounds(hgapL, vgap+25, width, 40);
            nidT.setFont(font20);
            this.add(nidT);

            // NAME 
            nameL = new JLabel("Name");
            nameL.setBounds(hgapR, vgap, width, 25);
            nameL.setFont(font16b);
            this.add(nameL);

            nameT = new JTextField();
            nameT.setBounds(hgapR, vgap+25, width, 40);
            nameT.setFont(font20);
            nameT.setEditable(false);
            this.add(nameT);

            // Initiate nameT
            vgap += 100;
            RCBinitiateBtn = new JButton("Initiate");
            RCBinitiateBtn.setBounds(hgapL, vgap, width, 40);
            RCBinitiateBtn.setFont(font20);
            RCBinitiateBtn.setBackground(Color.BLUE);
            RCBinitiateBtn.setForeground(Color.WHITE);
            RCBinitiateBtn.addActionListener(this);
            this.add(RCBinitiateBtn);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setBounds(hgapR, vgap, width, 40);
            cancelBtn.setFont(font20);
            cancelBtn.setBackground(Color.RED);
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.addActionListener(this);
            this.add(cancelBtn);

            this.update(getGraphics());

        }

        else if (evt.getSource() == RCBinitiateBtn) {
            int vgap = 350;
            int hgapL = 230;
            int width = 300;

            String nidEmailT = nidT.getText();
            if (pressedBtn == "removeCustomer") {
                Customer customerByNid = customerList.getCustomerByNid(nidEmailT);
                Customer customerByEmail = customerList.getCustomerByEmail(nidEmailT);

                if (customerByNid == null && customerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                    nidT.setText("");
                    return;
                }
                else if (customerByNid != null) {
                    nameT.setText(customerByNid.getName());
                }
                else if (customerByEmail != null) {
                    nameT.setText(customerByEmail.getName());
                    isByNid = false;
                }
            }

            else if (pressedBtn == "removeBanker") {
                Banker bankerByNid = bankerList.getBankerByNid(nidEmailT);
                Banker bankerByEmail = bankerList.getBankerByEmail(nidEmailT);

                if (bankerByNid == null && bankerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Banker not found");
                    nidT.setText("");
                    return;
                }
                else if (bankerByNid != null) {
                    nameT.setText(bankerByNid.getName());
                }
                else if (bankerByEmail != null) {
                    nameT.setText(bankerByEmail.getName());
                    isByNid = false;
                }
            }

            this.remove(RCBinitiateBtn);
            vgap += 100;

            // Confirm BUTTON
            RCBconfimBtn = new JButton("Confirm");
            RCBconfimBtn.setBounds(hgapL, vgap, width, 40);
            RCBconfimBtn.setFont(font20);
            RCBconfimBtn.setBackground(Color.BLUE);
            RCBconfimBtn.setForeground(Color.WHITE);
            RCBconfimBtn.addActionListener(this);
            this.add(RCBconfimBtn);

            this.update(getGraphics());
        }

        else if (evt.getSource() == RCBconfimBtn) {
            try {
                if (pressedBtn == "removeCustomer") {
                    String nidEmail = nidT.getText();

                    boolean success = false;
                    if (isByNid) {
                        success = customerList.removeCustomerByNid(nidEmail);
                    }
                    else {
                        success = customerList.removeCustomerByEmail(nidEmail);
                    }

                    if (success) {
                        FileIO.writeCustomerList(customerList);
                        FileIO.writeAccounts(customerList);

                        JOptionPane.showMessageDialog(this, "Customer removed successfully");
                        nidT.setText("");
                        nameT.setText("");
                        cancelBtn.setText("Exit");
                        return;
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Customer not found");
                    }
                }
                
                else if (pressedBtn == "removeBanker") {
                    String nidEmail = nidT.getText();
                    
                    boolean success = false;
                    if (isByNid) {
                        success = bankerList.removeBankerByNid(nidEmail);
                    }
                    else {
                        success = bankerList.removeBankerByEmail(nidEmail);
                    }

                    if (success) {
                        FileIO.writeBankerList(bankerList);
                        
                        JOptionPane.showMessageDialog(this, "Banker removed successfully");
                        nidT.setText("");
                        nameT.setText("");
                        return;
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "Banker not found");
                    }
                }
            } catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
                System.out.println("Error [ManagerDashboard: RCBconfirmBtn] " + "\n\n" + expt + "\n\n");
                
            }


        }

        // CREATE ACCOUNT

        else if (evt.getSource() == createAccountBtn) {
            createAccountBtn.setBackground(Color.GRAY);

            // Section title
            secL = new JLabel("CREATE NEW ACCOUNT");
            secL.setBounds(490, 290, 300, 36);
            secL.setFont(font20b);
            this.add(secL);

            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            // NID 
            nidL = new JLabel("Account Holder NID or Email");
            nidL.setBounds(hgapL, vgap, width, 25);
            nidL.setFont(font16b);
            this.add(nidL);

            nidT = new JTextField();
            nidT.setBounds(hgapL, vgap+25, width, 40);
            nidT.setFont(font20);
            this.add(nidT);

            // NAME 
            nameL = new JLabel("Account Holder Name");
            nameL.setBounds(hgapR, vgap, width, 25);
            nameL.setFont(font16b);
            this.add(nameL);

            nameT = new JTextField();
            nameT.setBounds(hgapR, vgap+25, width, 40);
            nameT.setFont(font20);
            nameT.setEditable(false);
            this.add(nameT);

            vgap += 80;

            // ACCOUNT NUMBER 
            accountNumL = new JLabel("Account Number");
            accountNumL.setBounds(hgapL, vgap, width, 25);
            accountNumL.setFont(font16b);
            this.add(accountNumL);

            accountNumT = new JTextField();
            accountNumT.setBounds(hgapL, vgap+25, width, 40);
            accountNumT.setFont(font20);
            accountNumT.setEditable(false);
            this.add(accountNumT);

            // ACCOUNT TYPE 
            accountTypeL = new JLabel("Account Type");
            accountTypeL.setBounds(hgapR, vgap, width, 25);
            accountTypeL.setFont(font16b);
            this.add(accountTypeL);

            accountTypeCB = new JComboBox<>(new String[]{"Saving", "Fixed Deposit"});
            accountTypeCB.setBounds(hgapR, vgap+25, width, 40);
            accountTypeCB.setFont(font20);
            accountTypeCB.setBackground(Color.WHITE);
            this.add(accountTypeCB);

            // Initiate nameT
            vgap += 100;
            createAccountInitiateBtn = new JButton("Initiate");
            createAccountInitiateBtn.setBounds(hgapL, vgap, width, 40);
            createAccountInitiateBtn.setFont(font20);
            createAccountInitiateBtn.setBackground(Color.BLUE);
            createAccountInitiateBtn.setForeground(Color.WHITE);
            createAccountInitiateBtn.addActionListener(this);
            this.add(createAccountInitiateBtn);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setBounds(hgapR, vgap, width, 40);
            cancelBtn.setFont(font20);
            cancelBtn.setBackground(Color.RED);
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.addActionListener(this);
            this.add(cancelBtn);

            this.update(getGraphics());
        }

        else if (evt.getSource() == createAccountInitiateBtn) {
            int vgap = 430;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            String nidEmailT = nidT.getText();
            Customer customerByNid = customerList.getCustomerByNid(nidEmailT);
            Customer customerByEmail = customerList.getCustomerByEmail(nidEmailT);

            int accountNumber = customerList.getNewAccountNumber();
            accountNumT.setText(Integer.toString(accountNumber));

            if (customerByNid == null && customerByEmail == null) {
                JOptionPane.showMessageDialog(this, "Customer not found");
                nidT.setText("");
                return;
            }
            else if (customerByNid != null) {
                nameT.setText(customerByNid.getName());
            }
            else if (customerByEmail != null) {
                nameT.setText(customerByEmail.getName());
            }

            // INTEREST RATE
            vgap += 80;

            interestRateL = new JLabel("Interest Rate");
            interestRateL.setBounds(hgapL, vgap, width, 25);
            interestRateL.setFont(font16b);
            this.add(interestRateL);

            interestRateT = new JTextField();
            interestRateT.setBounds(hgapL, vgap+25, width, 40);
            interestRateT.setFont(font20);
            this.add(interestRateT);

            // TENURE YEAR
            if (accountTypeCB.getSelectedItem().toString().equals("Fixed Deposit")) {

                tenureYearL = new JLabel("Tenure Year");
                tenureYearL.setBounds(hgapR, vgap, width, 25);
                tenureYearL.setFont(font16b);
                this.add(tenureYearL);

                tenureYearT = new JTextField();
                tenureYearT.setBounds(hgapR, vgap+25, width, 40);
                tenureYearT.setFont(font20);
                this.add(tenureYearT);
            }
            
            this.remove(createAccountInitiateBtn);
            vgap += 100;

            // CONFIRM BUTTON
            createAccountConfirmBtn = new JButton("Confirm");
            createAccountConfirmBtn.setBounds(hgapL, vgap, width, 40);
            createAccountConfirmBtn.setFont(font20);
            createAccountConfirmBtn.setBackground(Color.BLUE);
            createAccountConfirmBtn.setForeground(Color.WHITE);
            createAccountConfirmBtn.addActionListener(this);
            this.add(createAccountConfirmBtn);

            cancelBtn.setBounds(hgapR, vgap, width, 40);

            this.update(getGraphics());
        }

        else if (evt.getSource() == createAccountConfirmBtn) {
            try {
                String nidEmail = nidT.getText();
                String accountNumber = accountNumT.getText();
                String accountType = accountTypeCB.getSelectedItem().toString();

                Customer customerByNid = customerList.getCustomerByNid(nidEmail);
                Customer customerByEmail = customerList.getCustomerByEmail(nidEmail);

                if (customerByNid == null && customerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                    nidT.setText("");
                    return;
                }

                Account account = null;

                if (accountType.equals("Saving")) {
                    account = new Savings(Integer.parseInt(accountNumber), 0, Float.parseFloat(interestRateT.getText()));
                    if (customerByNid != null) {
                        customerByNid.addAccount(account);
                    }
                    else if (customerByEmail != null) {
                        customerByEmail.addAccount(account);
                    }
                }
                else if (accountType.equals("Fixed Deposit")) {
                    account = new FixedDeposit(Integer.parseInt(accountNumber), 0, Float.parseFloat(interestRateT.getText()), Integer.parseInt(tenureYearT.getText()));
                    if (customerByNid != null) {
                        customerByNid.addAccount(account);
                    }
                    else if (customerByEmail != null) {
                        customerByEmail.addAccount(account);
                    }
                }

                FileIO.writeAccounts(customerList);

                JOptionPane.showMessageDialog(this, "Account created successfully");

                this.dispose();
                new ManagerDashboard(customerList, bankerList, loginPage);

            } catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
                System.out.println("Error [ManagerDashboard: createAccountConfirmBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        // REMOVE ACCOUNT

        else if (evt.getSource() == removeAccountBtn) {
            removeAccountBtn.setBackground(Color.GRAY);

            // Section title
            secL = new JLabel("REMOVE ACCOUNT");
            secL.setBounds(490, 290, 300, 36);
            secL.setFont(font20b);
            this.add(secL);

            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            // ACCOUNT NUMBER 
            accountNumL = new JLabel("Account Number");
            accountNumL.setBounds(hgapL, vgap, width, 25);
            accountNumL.setFont(font16b);
            this.add(accountNumL);

            accountNumT = new JTextField();
            accountNumT.setBounds(hgapL, vgap+25, width, 40);
            accountNumT.setFont(font20);
            this.add(accountNumT);

            // NAME 
            nameL = new JLabel("Account Holder Name");
            nameL.setBounds(hgapR, vgap, width, 25);
            nameL.setFont(font16b);
            this.add(nameL);

            nameT = new JTextField();
            nameT.setBounds(hgapR, vgap+25, width, 40);
            nameT.setFont(font20);
            nameT.setEditable(false);
            this.add(nameT);

            // Initiate nameT
            vgap += 100;
            removeAccountInitiateBtn = new JButton("Initiate");
            removeAccountInitiateBtn.setBounds(hgapL, vgap, width, 40);
            removeAccountInitiateBtn.setFont(font20);
            removeAccountInitiateBtn.setBackground(Color.BLUE);
            removeAccountInitiateBtn.setForeground(Color.WHITE);
            removeAccountInitiateBtn.addActionListener(this);
            this.add(removeAccountInitiateBtn);

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

        else if (evt.getSource() == removeAccountInitiateBtn) {
            int vgap = 350;
            int hgapL = 230;
            int width = 300;

            String accountNumber = accountNumT.getText();
            Account account = customerList.getAccount(Integer.parseInt(accountNumber));
            Customer customer = customerList.getCustomerByAccountNumber(Integer.parseInt(accountNumber));

            if (account == null) {
                JOptionPane.showMessageDialog(this, "Account not found");
                accountNumT.setText("");
                return;
            }
            else {
                nameT.setText(customer.getName());
            }

            this.remove(removeAccountInitiateBtn);
            vgap += 100;

            // CONFIRM BUTTON
            removeAccountConfirmBtn = new JButton("Confirm");
            removeAccountConfirmBtn.setBounds(hgapL, vgap, width, 40);
            removeAccountConfirmBtn.setFont(font20);
            removeAccountConfirmBtn.setBackground(Color.BLUE);
            removeAccountConfirmBtn.setForeground(Color.WHITE);
            removeAccountConfirmBtn.addActionListener(this);
            this.add(removeAccountConfirmBtn);

            this.update(getGraphics());
        } 

        else if (evt.getSource() == removeAccountConfirmBtn) {
            try {
                String accountNumber = accountNumT.getText();
                Account account = customerList.getAccount(Integer.parseInt(accountNumber));
                Customer customer = customerList.getCustomerByAccountNumber(Integer.parseInt(accountNumber));

                if (account == null) {
                    JOptionPane.showMessageDialog(this, "Account not found");
                    accountNumT.setText("");
                    return;
                }
                else {
                    customer.removeAccount(Integer.parseInt(accountNumber));

                    customerList.clearAccounts();
                    FileIO.writeAccounts(customerList);

                    JOptionPane.showMessageDialog(this, "Account removed successfully");
                    accountNumT.setText("");
                    nameT.setText("");
                    cancelBtn.setText("Exit");
                }
            } catch (Exception expt) {
                JOptionPane.showMessageDialog(this, "Error.");
                System.out.println("Error [ManagerDashboard: removeAccountConfirmBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        // EDIT CUSTOMER OR BANKER INFO

        else if (evt.getSource() == editCustomerInfoBtn || evt.getSource() == editBankerInfoBtn) {
            removeAllActionListeners();
            
            if (evt.getSource() == editCustomerInfoBtn) {
                editCustomerInfoBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("EDIT CUSTOMER INFO");
                secL.setBounds(490, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "editCustomerInfo";
            }
            else if (evt.getSource() == editBankerInfoBtn) {
                editBankerInfoBtn.setBackground(Color.GRAY);

                // Section title
                secL = new JLabel("EDIT BANKER INFO");
                secL.setBounds(490, 290, 300, 36);
                secL.setFont(font20b);
                this.add(secL);

                pressedBtn = "editBankerInfo";
            }
            int vgap = 350;
            int hgapL = 230;
            int hgapR = 680;
            int width = 300;

            // NID 
            nidL = new JLabel("NID Number or Email");
            nidL.setBounds(hgapL, vgap, width, 25);
            nidL.setFont(font16b);
            this.add(nidL);

            nidT = new JTextField();
            nidT.setBounds(hgapL, vgap+25, width, 40);
            nidT.setFont(font20);
            this.add(nidT);

            // NAME 
            nameL = new JLabel("Name");
            nameL.setBounds(hgapR, vgap, width, 25);
            nameL.setFont(font16b);
            this.add(nameL);

            nameT = new JTextField();
            nameT.setBounds(hgapR, vgap+25, width, 40);
            nameT.setFont(font20);
            this.add(nameT);

            vgap += 80;

            // BIRTH YEAR 
            birthYearL = new JLabel("Birth Year");
            birthYearL.setBounds(hgapL, vgap, width, 25);
            birthYearL.setFont(font16b);
            this.add(birthYearL);

            birthYearT = new JTextField();
            birthYearT.setBounds(hgapL, vgap+25, width, 40);
            birthYearT.setFont(font20);
            this.add(birthYearT);

            // MOBILE NUMBER 
            mobileNumberL = new JLabel("Mobile Number");
            mobileNumberL.setBounds(hgapR, vgap, width, 25);
            mobileNumberL.setFont(font16b);
            this.add(mobileNumberL);

            mobileNumberT = new JTextField();
            mobileNumberT.setBounds(hgapR, vgap+25, width, 40);
            mobileNumberT.setFont(font20);
            this.add(mobileNumberT);

            vgap += 80;

            // ADDRESS
            addressL = new JLabel("Address");
            addressL.setBounds(hgapL, vgap, width, 25);
            addressL.setFont(font16b);
            this.add(addressL);

            addressT = new JTextField();
            addressT.setBounds(hgapL, vgap+25, width, 40);
            addressT.setFont(font20);
            this.add(addressT);

            // EMAIL
            emailL = new JLabel("Email");
            emailL.setBounds(hgapR, vgap, width, 25);
            emailL.setFont(font16b);
            this.add(emailL);

            emailT = new JTextField();
            emailT.setBounds(hgapR, vgap+25, width, 40);
            emailT.setFont(font20);
            this.add(emailT);

            if (evt.getSource() == editBankerInfoBtn) {
                vgap += 80;

                // JOB TITLE 
                jobTitleL = new JLabel("Job Title");
                jobTitleL.setBounds(hgapL, vgap, width, 25);
                jobTitleL.setFont(font16b);
                this.add(jobTitleL);

                jobTitleCB = new JComboBox<>(new String[] {"", "Cashier", "Manager"});
                jobTitleCB.setBounds(hgapL, vgap+25, width, 40);
                jobTitleCB.setFont(font20);
                this.add(jobTitleCB);
            }

            // SET ALL FIELD TO NON-EDITABLE
            nameT.setEditable(false);
            birthYearT.setEditable(false);
            mobileNumberT.setEditable(false);
            addressT.setEditable(false);
            emailT.setEditable(false);

            // Initiate
            vgap += 100;
            ECBinitiatieBtn = new JButton("Initiate");
            ECBinitiatieBtn.setBounds(hgapL, vgap, width, 40);
            ECBinitiatieBtn.setFont(font20);
            ECBinitiatieBtn.setBackground(Color.BLUE);
            ECBinitiatieBtn.setForeground(Color.WHITE);
            ECBinitiatieBtn.addActionListener(this);
            this.add(ECBinitiatieBtn);

            cancelBtn = new JButton("Cancel");
            cancelBtn.setBounds(hgapR, vgap, width, 40);
            cancelBtn.setFont(font20);
            cancelBtn.setBackground(Color.RED);
            cancelBtn.setForeground(Color.WHITE);
            cancelBtn.addActionListener(this);
            this.add(cancelBtn);

            this.update(getGraphics());

        }

        else if (evt.getSource() == ECBinitiatieBtn) {
            this.remove(ECBinitiatieBtn);

            int vgap = 350;
            int hgapL = 230;
            int width = 300;

            String nidT = this.nidT.getText();
            Customer customerByNid = customerList.getCustomerByNid(nidT);
            Customer customerByEmail = customerList.getCustomerByEmail(nidT);

            Banker bankerByNid = bankerList.getBankerByNid(nidT);
            Banker bankerByEmail = bankerList.getBankerByEmail(nidT);

            //SET ALL FIELD TO EDITABLE
            nameT.setEditable(true);
            birthYearT.setEditable(true);
            mobileNumberT.setEditable(true);
            addressT.setEditable(true);
            emailT.setEditable(true);

            if (pressedBtn == "editCustomerInfo") {
                if (customerByNid == null && customerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Customer not found");
                    this.nidT.setText("");
                    return;
                }
                else if (customerByNid != null) {
                    nameT.setText(customerByNid.getName());
                    birthYearT.setText(Integer.toString(customerByNid.getBirthYear()));
                    mobileNumberT.setText(customerByNid.getMobileNumber());
                    addressT.setText(customerByNid.getAddress());
                    emailT.setText(customerByNid.getEmail());
                }
                else if (customerByEmail != null) {
                    nameT.setText(customerByEmail.getName());
                    birthYearT.setText(Integer.toString(customerByEmail.getBirthYear()));
                    mobileNumberT.setText(customerByEmail.getMobileNumber());
                    addressT.setText(customerByEmail.getAddress());
                    emailT.setText(customerByEmail.getEmail());
                }
            }
            else if (pressedBtn == "editBankerInfo") {
                if (bankerByNid == null && bankerByEmail == null) {
                    JOptionPane.showMessageDialog(this, "Banker not found");
                    this.nidT.setText("");
                    return;
                }
                else if (bankerByNid != null) {
                    nameT.setText(bankerByNid.getName());
                    birthYearT.setText(Integer.toString(bankerByNid.getBirthYear()));
                    mobileNumberT.setText(bankerByNid.getMobileNumber());
                    addressT.setText(bankerByNid.getAddress());
                    emailT.setText(bankerByNid.getEmail());
                    jobTitleCB.setSelectedItem(bankerByNid.getJobTitle());
                }
                else if (bankerByEmail != null) {
                    nameT.setText(bankerByEmail.getName());
                    birthYearT.setText(Integer.toString(bankerByEmail.getBirthYear()));
                    mobileNumberT.setText(bankerByEmail.getMobileNumber());
                    addressT.setText(bankerByEmail.getAddress());
                    emailT.setText(bankerByEmail.getEmail());
                    jobTitleCB.setSelectedItem(bankerByEmail.getJobTitle());
                }
                vgap += 80;
            }
            
            // CONFIRM BUTTON
            vgap += 260;
            ECBconfirmBtn = new JButton("Confirm");
            ECBconfirmBtn.setBounds(hgapL, vgap, width, 40);
            ECBconfirmBtn.setFont(font20);
            ECBconfirmBtn.setBackground(Color.BLUE);
            ECBconfirmBtn.setForeground(Color.WHITE);
            ECBconfirmBtn.addActionListener(this);
            this.add(ECBconfirmBtn);

            this.update(getGraphics());
        }

        else if (evt.getSource() == ECBconfirmBtn) {
            try {
                String nidT = this.nidT.getText();
                String nameT = this.nameT.getText();
                int birthYearT = Integer.parseInt(this.birthYearT.getText());
                String mobileNumberT = this.mobileNumberT.getText();
                String addressT = this.addressT.getText();
                String emailT = this.emailT.getText();

                if (pressedBtn == "editCustomerInfo") {
                    Customer customerByNid = customerList.getCustomerByNid(nidT);
                    Customer customerByEmail = customerList.getCustomerByEmail(nidT);

                    if (customerByNid != null) {
                        customerByNid.setName(nameT);
                        customerByNid.setBirthYear(birthYearT);
                        customerByNid.setMobileNumber(mobileNumberT);
                        customerByNid.setAddress(addressT);
                        customerByNid.setEmail(emailT);
                    }
                    else if (customerByEmail != null) {
                        customerByEmail.setName(nameT);
                        customerByEmail.setBirthYear(birthYearT);
                        customerByEmail.setMobileNumber(mobileNumberT);
                        customerByEmail.setAddress(addressT);
                        customerByEmail.setEmail(emailT);
                    }

                    FileIO.writeCustomerList(customerList);
                    JOptionPane.showMessageDialog(this, "Customer info updated successfully");
                }
                else if (pressedBtn == "editBankerInfo") {
                    Banker bankerByNid = bankerList.getBankerByNid(nidT);
                    Banker bankerByEmail = bankerList.getBankerByEmail(nidT);

                    if (bankerByNid != null) {
                        bankerByNid.setName(nameT);
                        bankerByNid.setBirthYear(birthYearT);
                        bankerByNid.setMobileNumber(mobileNumberT);
                        bankerByNid.setAddress(addressT);
                        bankerByNid.setEmail(emailT);
                        bankerByNid.setJobTitle(jobTitleCB.getSelectedItem().toString());
                    }
                    else if (bankerByEmail != null) {
                        bankerByEmail.setName(nameT);
                        bankerByEmail.setBirthYear(birthYearT);
                        bankerByEmail.setMobileNumber(mobileNumberT);
                        bankerByEmail.setAddress(addressT);
                        bankerByEmail.setEmail(emailT);
                        bankerByEmail.setJobTitle(jobTitleCB.getSelectedItem().toString());
                    }

                    FileIO.writeBankerList(bankerList);
                    JOptionPane.showMessageDialog(this, "Banker info updated successfully");
                }

                this.dispose();
                new ManagerDashboard(customerList, bankerList, loginPage);

            } catch (Exception expt) { 
                JOptionPane.showMessageDialog(this, "Error.");
                System.out.println("Error [ManagerDashboard: ECBconfirmBtn] " + "\n\n" + expt + "\n\n");
            }
        }

        // CANCEL BUTTON

        else if (evt.getSource() == cancelBtn) {
            this.dispose();
            new ManagerDashboard(customerList, bankerList, loginPage);
        }
    
    }
}