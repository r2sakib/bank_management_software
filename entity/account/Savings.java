package entity.account;

public class Savings extends Account {
	private double interestRate;
	
	public Savings(){}

	public Savings(int accountNumber,  double balance, double interestRate){
		super(accountNumber, balance);
		setInterestRate(interestRate);
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return this.interestRate;
	}
	
	public String deposit(int amount) {
		if(amount > 0) {
			super.setBalance(super.getBalance() + amount);
			return "Deposit Successful";
		}
		else {
			return "Invalid Amount";
		}
	}

	public String withdraw(int amount) {
		if (amount <= 0) {
			return "Invalid Amount";
		}
		else {
			if(amount <= super.getBalance()) {
				super.setBalance(super.getBalance() - amount);
				return "Withdrawal Successful";
			}
			else {
				return "Insufficient Balance";
			}
		}
	}
}