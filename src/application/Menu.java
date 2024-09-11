package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;

import exceptions.AmountException;
import model.Account;
import model.CurrencyQuote;
import services.ConsumeApi;

public class Menu {
	
	private static Scanner scanner = new Scanner(System.in);
	private Account account;
	private Boolean validInput = true;
	private ConsumeApi consumeApi = new ConsumeApi();
	private Gson gson = new Gson();
	
	public void inicialScreen() {
		
		while (validInput) {
			
			try {
				System.out.println("1 - Create Account");
				System.out.println("2 - Login");
				System.out.println("3 - Exit");
				System.out.print("Option: ");
				
				int option = scanner.nextInt();
				
				switch (option) {
				case 1:
					createAccount();
					validInput = false;
					break;
				case 2:
					login();
					validInput = false;
					break;
				case 3:
					System.out.println("Ending program.");
					validInput = false;
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
	
	public void createAccount() {
		scanner.nextLine();
		
		System.out.print("Full Name: ");
		String name = scanner.nextLine().trim();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);

		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		account = new Account(name, email, password);
		
		menuInteration();
	}
	
	public void login() {
		scanner.nextLine();
		
		System.out.println("\nLogin with Email: ");
		System.out.println("Login with ID: ");
		System.out.println("Back");
		System.out.print("Option: ");
		int n = scanner.nextInt();
		
		switch (n) {
		case 1:
			scanner.nextLine();
			loginEmail();
			break;
		case 2:
			scanner.nextLine();
			loginId();
			break;
		case 3:
			scanner.nextLine();
			inicialScreen();
			break;
		default:
			System.out.println("Error: Invalid input. Please enter a valid option!\n");
			scanner.nextLine();
			}
		}
			
	public void loginEmail() {
		
		try {
			System.out.print("\nEmail: ");
			String email = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
				
			if (email == account.getEmail() && password == account.getPassword()) {
				menuInteration();
			} else {
				login();
			}
		} catch (InputMismatchException | NullPointerException exception) {
			System.out.println("Error.\n");
			inicialScreen();
		}
	}
	
	public void loginId() {
		
		try {
			System.out.print("\nID: ");
			int id = scanner.nextInt();
			System.out.print("Password: ");
			scanner.nextLine();
			String password = scanner.nextLine();
				
			if (id == account.getId() && password == account.getPassword()) {
				menuInteration();
			} else {
				login();
			}
		} catch (InputMismatchException | NullPointerException exception) {
			System.out.println("Error.\n");
			inicialScreen();
		}
	}
	
	public void menuInteration() {
		
		while (validInput) {
			menuFieldsString();

			try {
				switch (scanner.nextInt()) {
				case 1:
					createAccount();
					break;
				case 2:
					System.out.println("Amount: $" + String.format("%.2f", account.getBalance()));
					break;
				case 3:
					System.out.print("Amount to deposit: ");
					account.deposit(scanner.nextDouble());
					break;
				case 4:
					System.out.print("Destination Account Number: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Amount to transfer: ");
					
					account.transfer(account.getAccountManager().getAccountById(id), scanner.nextDouble());
					scanner.nextLine();
					break;
				case 5:
					System.out.println("\nNumber Total of Accounts: " + account.getAccountManager().getTotalNumberOfAccounts());
					account.getAccountManager().getAccountList();
					break;
				case 6:
//					String json = gson.toJson(consumeApi.useApi());
					System.out.println("\n" + consumeApi.useApi());
					
					String json = consumeApi.useApi();
					CurrencyQuote currencyQuote = gson.fromJson(json, CurrencyQuote.class);
					System.out.println(currencyQuote.getName());
					break;
				case 7:
					validInput = false;
					break;
				default:
					System.out.println("Error: Please select a valid option!");
					break;
				}
			} catch (AmountException exception) {
				System.out.println("Error: " + exception.getMessage());
				break;
			} catch (InputMismatchException exception) {
				System.out.println("Error: Invalid input. Please enter a valid number!");
				break;
			}

		}

	}

	public void menuFieldsString() {
		System.out.println("***********");
		System.out.println("\nOperations");
		System.out.println("1 - Create New Account");
		System.out.println("2 - Show Amount");
		System.out.println("3 - Deposit");
		System.out.println("4 - Transfer");
		System.out.println("5 - Account List");
		System.out.println("6 - Exchange Rate");
		System.out.println("7 - Exit");
		System.out.print("Option: ");
	}

}
