package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Account;
import exceptions.AmountException;

public class Menu {

	List<Account> accountList = new ArrayList<>();
	private static Scanner scanner = new Scanner(System.in);

	public void accountRegister() {

		Boolean validInput = false;
		
		System.out.println("Account data:\n");

		while (validInput == false) {
			try {
				System.out.print("Name: ");
				String name = scanner.nextLine();
				System.out.print("Starter amount: ");
				Double amount = scanner.nextDouble();
				scanner.nextLine();

				Account account = new Account(name, amount);
				accountList.add(account);

				validInput = true;
				menuInteration();

			} catch (InputMismatchException exception) {
				System.out.println("Error: Invalid input. Please enter a valid number!\n");
				scanner.nextLine();
			}
		}
	}

	public void menuInteration() {
		
		int n = 0;

		do {
			menuFieldsString();

			try {
				switch (n = scanner.nextInt()) {
				case 1:
					System.out.println("Amount: $" + String.format("%.2f", accountList.get(0).getAmount()));
					break;
				case 2:
					System.out.print("Amount to deposit: ");
					accountList.get(0).deposit(scanner.nextDouble());
					break;
				case 3:
					System.out.print("Amount to transfer: ");
					accountList.get(0).transfer(scanner.nextDouble());
					break;
				case 4:
					for (Account acc : accountList) {
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
