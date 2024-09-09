package model;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
	
	private static List<Account> accountList = new ArrayList<>();
	
	public void addAccount(Account account) {
		accountList.add(account);
	}
	
	public void removeAccount(Account account) {
		accountList.remove(account);
	}
	
	public void getAccountList() {
		for (Account account : accountList) {
			System.out.println(account.toString());
		}
	}
	
	public int getTotalNumberOfAccounts() {
		return accountList.size();
	}
	
	public Account getAccountById(int id) {
		for (Account account : accountList) {
			if (account.getId() == id) {
				return account;
			}
		}
		return null;
	}

}
