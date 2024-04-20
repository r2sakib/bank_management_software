package entity.account;

public abstract class Account {
    private int accountNumber;
	private double balance;
	
	public Account(){ }

	public Account(int accountNumber, double balance){
		setAccountNumber(accountNumber);
        setBalance(balance);
	}
	
	public void setAccountNumber(int accountNumber){
		this.accountNumber = accountNumber;
	}

	public void setBalance(double balance){
		this.balance = balance;
	}
	
	public int getAccountNumber(){
        return this.accountNumber;
    }

	public double getBalance(){
        return this.balance;
    }
	
}