package file;

import java.util.*;
import java.io.*;

import entity.person.*;
import entity.account.*;
import entityList.*;

public class FileIO {
    public static void writeCustomerList(CustomerList cList) {
        try {
            File file = new File("./file/customers.txt");
            FileWriter fw = new FileWriter(file, false);
            fw.write(cList.customersToString());
            fw.flush();
            fw.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }

    public static void writeAccounts(CustomerList cList) {
        try {
            File file = new File("./file/accounts.txt");
            FileWriter fw = new FileWriter(file, false);
            fw.write(cList.accountsToString());
            fw.flush();
            fw.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }

    public static void loadCustomerList(CustomerList cList) {
        try {
            File file = new File("./file/customers.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] customerData = line.split(",");

                Customer customer = new Customer(customerData[0], customerData[1], Integer.parseInt(customerData[2]), customerData[3], customerData[4], customerData[5], customerData[6]);
                cList.addCustomer(customer);
            }
            sc.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }

    public static void loadAccounts(CustomerList cList) {
        try {
            File file = new File("./file/accounts.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] accountData = line.split(",");

                Account account = null;
                if (accountData.length == 4) {
                    account = new Savings(Integer.parseInt(accountData[1]), Double.parseDouble(accountData[2]), Double.parseDouble(accountData[3]));
                } else if (accountData.length == 5) {
                    account = new FixedDeposit(Integer.parseInt(accountData[1]), Double.parseDouble(accountData[2]), Double.parseDouble(accountData[3]), Integer.parseInt(accountData[4]));
                }
                cList.getCustomerByNid(accountData[0]).addAccount(account);
            }
            sc.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }

    public static void writeBankerList(BankerList bList) {
        try {
            File file = new File("./file/bankers.txt");
            FileWriter fw = new FileWriter(file, false);
            fw.write(bList.bankersToString());
            fw.flush();
            fw.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }

    public static void loadBankerList(BankerList bList) {
        try {
            File file = new File("./file/bankers.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] bankerData = line.split(",");

                Banker banker = new Banker(bankerData[0], bankerData[1], Integer.parseInt(bankerData[2]), bankerData[3], bankerData[4], bankerData[5], bankerData[6], bankerData[7]);
                bList.addBanker(banker);
            }
            sc.close();

        } catch (Exception expt) {
            System.out.println(expt);
        }
    }
}