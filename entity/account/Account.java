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

	public boolean deposit(double amount){
		if (amount > 0) {
			this.balance += amount;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean withdraw(double amount){
		if (this.balance >= amount) {
			this.balance -= amount;
			return true;
		}
		else {
			return false;
		}
	}

	public boolean transfer(Account toAccount, double amount) {
		if (this.withdraw(amount)) {
			toAccount.deposit(amount);
			return true;
		}
		else {
			return false;
		}
	}
	
	
}