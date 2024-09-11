package model;

import exceptions.AmountException;

public class Account {
	
	private String name;
	private String email;
	private String password;
	private Double balance;
	private AccountManager accountManager = new AccountManager();
	public static int counter = 1;
	private int id;

	public Account() {
	}

	public Account(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.balance = 0.0;
		id = counter;
		counter++;
		System.out.println("Account created with successful. Welcome!");
		accountManager.addAccount(this);
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

	public Double getBalance() {
//		String balance = String.format("%.2f", this.balance);
//		return Double.valueOf(balance);
		return this.balance;
	}
	
	public int getId() {
		return id;
	}
	
	public AccountManager getAccountManager() {
		return accountManager;
	}

	public void deposit(Double amount) {
		if (amount <= 0.0) {
			throw new AmountException("Error: please deposit a amount biggest than $0.0");
		}
		this.balance += amount;
	}
	
	public void transfer(Account account, Double amount) {
		if (account == null || amount <= 0.0) {
			throw new AmountException("Error: invalid data. Please try again.");
		}
		if (amount > this.balance) {
			throw new AmountException("Error: invalid amount.\nBalance available in your account: $" + String.format("%.2f", getBalance()));
		}
		account.deposit(amount);
		this.balance -= amount;
	}

	@Override
	public String toString() {
		return "Account Holder: " + name + "\n" +
				"Email: " + email + "\n" +
				"ID: " + getId() +
				"\nAmount: $" + String.format("%.2f", getBalance()) + "\n";
	}
	
}
