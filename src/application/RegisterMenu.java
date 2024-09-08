package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Account;

public class RegisterMenu {
	
	private Account account = new Account();
	private static OperationsMenu operationsMenu = new OperationsMenu();
	private Boolean validInput = false;
	private static Scanner scanner = new Scanner(System.in);
	
	public void registerFieldsString() {
		
		while (validInput == false) {
			
			try {
				System.out.println("1 - Register");
				System.out.println("2 - Login");
				System.out.println("3 - Exit");
				System.out.print("Option: ");
				
				int option = scanner.nextInt();
				
				switch (option) {
				case 1:
					register();
					validInput = true;
					break;
				case 2:
					login();
					validInput = true;
					break;
				case 3:
					System.out.println("Endind program.");
					validInput = true;
					break;
				default:
					System.out.println("Error: Invalid input. Please enter a valid option!\n");
					scanner.nextLine();
				} 
			} catch (InputMismatchException exception) {
				System.out.println("Error: Invalid input. Please enter a valid number!\n");
				scanner.nextLine();
			}
		}
	}
	
	public void register() {
		scanner.nextLine();
		
		System.out.print("Full Name: ");
		String name = scanner.nextLine();
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		account = new Account(name, email, password);
		
		operationsMenu.menuInteration();
	}
	
	public void login() {
		scanner.nextLine();
		
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		if (email == account.getEmail() && password == account.getPassword()) {
			System.out.println("Login realized with successful. Welcome back!");
			operationsMenu.menuInteration();
		} else {
			registerFieldsString();
		}
	}

	public Account getAccount() {
		return account;
	}
}
