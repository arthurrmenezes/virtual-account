package application;

import java.util.InputMismatchException;
import java.util.Scanner;

//import com.google.gson.Gson;

import exceptions.AmountException;
import model.Account;
import model.AccountAuth;
//import model.CurrencyQuote;
import services.ConsumeApi;

public class Menu {
	
	private static Scanner scanner = new Scanner(System.in);
	private Account account;
	private Boolean validInput = true;
	private ConsumeApi consumeApi = new ConsumeApi();
//	private Gson gson = new Gson();
	private AccountAuth accountAuth = new AccountAuth();
	
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
					scanner.nextLine();
					System.out.print("Full Name: ");
					String name = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					System.out.print("Password: ");
					String password = scanner.nextLine();
					accountAuth.createAccount(name, email, password);
					menuInteration();
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
	
	public void login() {
		scanner.nextLine();
		
		System.out.println("\n1 - Login with Email: ");
		System.out.println("2 - Login with ID: ");
		System.out.println("3 - Back");
		System.out.print("Option: ");
		int n = scanner.nextInt();
		
		switch (n) {
		case 1:
			scanner.nextLine();
			System.out.print("\nEmail: ");
			String email = scanner.nextLine();
			System.out.print("Password: ");
			String password = scanner.nextLine();
			if (accountAuth.loginEmail(email, password) == true) {
				menuInteration();
			} else {
				login();
			}
			break;
		case 2:
			scanner.nextLine();
			System.out.print("\nID: ");
			int id = scanner.nextInt();
			System.out.print("Password: ");
			password = scanner.nextLine();
			if (accountAuth.loginId(id, password) == true) {
				menuInteration();
			} else {
				login();
			}
			break;
		case 3:
			scanner.nextLine();
			inicialScreen();
			break;
		default:
			System.out.println("Error: Invalid input. Please enter a valid option!\n");
			scanner.nextLine();
			break;
			}
		}
	
	public void menuInteration() {
		
		while (validInput) {
			menuFieldsString();

			try {
				switch (scanner.nextInt()) {
				case 1:
					scanner.nextLine();
					System.out.println("Full Name: ");
					String name = scanner.nextLine();
					System.out.print("Email: ");
					String email = scanner.nextLine();
					System.out.print("Password: ");
					String password = scanner.nextLine();
					accountAuth.createAccount(name, email, password);
					break;
				case 2:
					System.out.println("\nAccount Details:\n");
					System.out.println(accountAuth.getAccount().toString());
					break;
				case 3:
					System.out.print("Amount to deposit: ");
					accountAuth.getAccount().deposit(scanner.nextDouble());
					System.out.println();
					break;
				case 4:
					System.out.print("Destination Account Number: ");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.print("Amount to transfer: ");
					
					accountAuth.getAccount().transfer(accountAuth.getAccount().getAccountManager().getAccountById(id), scanner.nextDouble());
					scanner.nextLine();
					break;
				case 5:
					System.out.println("\nNumber Total of Accounts: " + accountAuth.getAccount().getAccountManager().getTotalNumberOfAccounts());
					accountAuth.getAccount().getAccountManager().getAccountList();
					break;
				case 6:
//					String json = gson.toJson(consumeApi.useApi());
					System.out.println("\n" + consumeApi.useApi());
					
//					String json = consumeApi.useApi();
//					CurrencyQuote currencyQuote = gson.fromJson(json, CurrencyQuote.class);
//					System.out.println(currencyQuote.getName());
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
				scanner.nextLine();
			} catch (InputMismatchException exception) {
				System.out.println("Error: Invalid input. Please enter a valid number!");
				scanner.nextLine();
			}

		}

	}

	public void menuFieldsString() {
		System.out.println("***********");
		System.out.println("\nOperations");
		System.out.println("1 - Create New Account");
		System.out.println("2 - Show Account Details");
		System.out.println("3 - Deposit");
		System.out.println("4 - Transfer");
		System.out.println("5 - Account List");
		System.out.println("6 - Exchange Rate");
		System.out.println("7 - Exit");
		System.out.println("\nAmount: " + String.format("%.2f",
				accountAuth.getAccount().getBalance()));
		System.out.print("Option: ");
	}

}
