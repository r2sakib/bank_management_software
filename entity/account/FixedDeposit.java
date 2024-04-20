package entity.account;

public class FixedDeposit extends Account {
    private double interestRate;
    private int tenureYear;
    
    public FixedDeposit(){}
    
    public FixedDeposit(int accountNumber, double balance, double interestRate, int tenureYear){
        super(accountNumber, balance);
        setInterestRate(interestRate);
        setTenureYear(tenureYear);
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    
    public void setTenureYear(int tenureYear) {
        this.tenureYear = tenureYear;
    }
    
    public double getInterestRate() {
        return this.interestRate;
    }
    
    public int getTenureYear() {
        return this.tenureYear;
    }
}