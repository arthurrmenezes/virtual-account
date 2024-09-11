package model;

public class AccountAuth {
	
	private Account account;
	
	public void createAccount(String name, String email, String password) {

		name = name.trim();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		account = new Account(name, email, password);
	}
	
	public boolean loginEmail(String email, String password) {
		
		if (email == account.getEmail() && password == account.getPassword()) {
			return true;
		}
		return false;
	}
	
	public boolean loginId(int id, String password) {
		
		if (id == account.getId() && password == account.getPassword()) {
			return true;
		}
		return false;
	}
	
	public Account getAccount() {
		return this.account;
	}

}
