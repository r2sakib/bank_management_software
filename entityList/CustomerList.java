package entityList;

import java.util.ArrayList;
import entity.person.Customer;
import entity.account.Account;

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
            if(customerList.get(i).getNid() == nid){
                return customerList.get(i);
            }
        }
        return null;
    }

    public Customer getCustomerByEmail(String email) {
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getEmail() == email){
                return customerList.get(i);
            }
        }
        return null;
    }

    public void removeCustomer(String nid) {
        for(int i = 0; i < customerList.size(); i++){
            if(customerList.get(i).getNid() == nid){
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
}