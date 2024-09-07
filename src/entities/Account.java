package entities;

import exceptions.AmountException;

public class Account {
	
	private String name;
	private Double amount;
	
	public Account() {
	}

	public Account(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}
	
	public void deposit(Double amount) {
		if (amount <= 1.0) {
			throw new AmountException("Error: please deposit a amount biggest than $1.0");
		} else {
			this.amount += amount;
		}
	}
	
	public void transfer(Double amount) {
		if (amount <= 1.0) {
			throw new AmountException("Error: please transfer a amount biggest than $1.0");
		} else {
			this.amount -= amount;
		}
	}
	
	@Override
	public String toString() {
		return "Name: " + this.name + "\nAmount: " + this.amount;
	}

}
