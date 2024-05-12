package entityList;

import java.util.ArrayList;
import entity.person.Customer;
import entity.account.Account;
import entity.account.FixedDeposit;
import entity.account.Savings;

public class CustomerList {
    private ArrayList <Customer> customerList;
    
    public CustomerList(){
        customerList = new ArrayList<Customer>();
    }
    
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
    
    public Customer getCustomerByNid(String nid) {
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getNid().equals(nid)){
                return customerList.get(i);
            }
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getEmail().equals(email)){
                return customerList.get(i);
            }
        }
        return null;
    }

    public void removeCustomer(String nid) {
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getNid().equals(nid)){
                customerList.remove(i);
            }
        }
    }
    
    public Account getAccount(int accountNumber) {
        Account account = null;
        for (int i = 0; i < customerList.size(); i++) {
            
            ArrayList <Account> accounts = customerList.get(i).getAccounts();
            for (int j = 0; j < accounts.size(); j++) {
                if (accounts.get(j).getAccountNumber() == accountNumber) {
                    account = accounts.get(j);
                }
            }
        }

        return account;
    }

    public Customer getCustomerByAccountNumber(int accountNumber) {
        Customer customer = null;
        for (int i = 0; i < customerList.size(); i++) {
            
            ArrayList <Account> accounts = customerList.get(i).getAccounts();
            for (int j = 0; j < accounts.size(); j++) {
                if (accounts.get(j).getAccountNumber() == accountNumber) {
                    customer = customerList.get(i);
                }
            }
        }

        return customer;
    }

    
    public void removeAccount(int accountNumber) {
        for (int i = 0; i < customerList.size(); i++) {
            
            ArrayList <Account> accounts = customerList.get(i).getAccounts();
            for (int j = 0; j < accounts.size(); j++) {
                if (accounts.get(j).getAccountNumber() == accountNumber) {
                    accounts.remove(j);
                }
            }
        }
    }

    public String customersToString() {
        String str = "";
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            str += customer.getName() + ",";
            str += customer.getNid() + ",";
            str += customer.getBirthYear() + ",";
            str += customer.getAddress() + ",";
            str += customer.getMobileNumber() + ",";
            str += customer.getEmail() + ",";
            str += customer.getPassword();
            str += "\n";
        }
        return str;
    }

    public String accountsToString() {
        String str = "";
        for (int i = 0; i < customerList.size(); i++) {
            Customer c = customerList.get(i);
            ArrayList <Account> accounts = c.getAccounts();

            for (int j = 0; j < accounts.size(); j++) {
                Account a = accounts.get(j);
                str += c.getNid() + ",";
                str += a.getAccountNumber() + ",";
                str += a.getBalance() + ",";

                if (a instanceof FixedDeposit) {
                    FixedDeposit fd = (FixedDeposit) a;
                    str += fd.getInterestRate() + ",";
                    str += fd.getTenureYear();
                } else if (a instanceof Savings) {
                    Savings s = (Savings) a;
                    str += s.getInterestRate();
                }
                str += "\n";
            }
        }
        return str;
    }

    public boolean isValid(String email, String password) {
        boolean valid = false;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getEmail().equals(email) && customerList.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return valid;
    }

    public void clear() {
        customerList.clear();
    }

    public void clearAccounts() {
        for (int i = 0; i < customerList.size(); i++) {
            customerList.get(i).getAccounts().clear();
        }
    }
}