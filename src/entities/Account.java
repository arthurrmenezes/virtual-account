package entities;

import java.util.ArrayList;
import java.util.List;

import exceptions.AmountException;

public class Account {
	
	private String name;
	private String email;
	private String password;
	private Double amount;
	private static List<Account> accountList = new ArrayList<>();

	public Account() {
	}

	public Account(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.amount = 0.0;
		accountList.add(this);
		System.out.println("Account created with successful. Welcome!");
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public Double getAmount() {
		return amount;
	}
	
	public void deposit(Double amount) {
		if (amount <= 1.0) {
			throw new AmountException("Error: please deposit a amount biggest than $1.0");
		}
		this.amount += amount;
	}
	
	public void transfer(Account account, Double amount) {
		if (account == null && amount <= 1.0) {
			throw new AmountException("Error: invalid data. Please try again.");
		} 
	}
	
	public List<Account> getAccountList() {
		return accountList;
	}
	
	public int getTotalNumberOfAccounts() {
		return accountList.size();
	}

	@Override
	public String toString() {
		return name + "\n" +
				email + "\n" +
				"Amount: $" + String.format("%.2f", getAmount());
	}
	
}
