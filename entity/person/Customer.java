package entity.person;
import java.util.ArrayList;
import entity.account.Account;

public class Customer extends Person {    
    private ArrayList <Account> accounts;

    public Customer() {
        accounts = new ArrayList<Account>();
    }

    public Customer(String name, String nid, int birthYear, String address, String mobileNumber, String email, String password) {
        super(name, nid, birthYear, address, mobileNumber, email, password);
        accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(int accountNumber) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                accounts.remove(i);
                break;
            }
        }
    }

    public ArrayList getAccounts() {
        return accounts;
    }

}
