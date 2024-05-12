import entity.account.*;
import entityList.*;
import entity.person.*;
import GUI.*;
import file.*;

public class Start {
	public static void main(String[] args) {
		CustomerList customerList = new CustomerList();
		BankerList bankerList = new BankerList();

		FileIO.loadCustomerList(customerList);
		FileIO.loadBankerList(bankerList);
		FileIO.loadAccounts(customerList);

		new ManagerDashboard(customerList, bankerList);
	}
}