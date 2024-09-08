package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import entities.Account;
import exceptions.AmountException;

public class OperationsMenu {

	private int n = 0;
	private static Scanner scanner = new Scanner(System.in);
	private RegisterMenu registerMenu = new RegisterMenu();
	Account account = registerMenu.getAccount();

	public void menuInteration() {
		
		do {
			menuFieldsString();

			try {
				switch (n = scanner.nextInt()) {
				case 1:
					System.out.println("Amount: $" + String.format("%.2f", account.getAmount()));
					break;
				case 2:
					System.out.print("Amount to deposit: ");
					account.deposit(scanner.nextDouble());
					break;
				case 3:
					System.out.print("Amount to transfer: ");
					account.transfer(account, scanner.nextDouble());
					break;
				case 4:
					for (Account acc : account.getAccountList()) {
						System.out.println(acc.toString());
					}
					break;
				case 5:
					n = 4;
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

		} while (n >= 0 && n != 4);

	}

	public void menuFieldsString() {
		System.out.println("***********");
		System.out.println("\nOperations");
		System.out.println("1 - Show Amount");
		System.out.println("2 - Deposit");
		System.out.println("3 - Transfer");
		System.out.println("4 - Account List");
		System.out.println("5 - Exit");
		System.out.print("Option: ");
	}
}
