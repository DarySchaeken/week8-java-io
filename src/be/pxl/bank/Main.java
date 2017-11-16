package be.pxl.bank;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("CREATE account, DEPOSIT, WITHDRAW, BALANCE, EXIT");
		try (Scanner keyboard = new Scanner(System.in)) {
			String input = keyboard.nextLine();
			while (!input.equalsIgnoreCase("exit")) {
				switch (input.toLowerCase()) {
				case "create":
					System.out.println("Enter name:");
					String name = keyboard.nextLine();
					System.out.println("Enter address (on one line):");
					String address = keyboard.nextLine();
					System.out.println("Enter phone number:");
					String phoneNumber = keyboard.nextLine();
					System.out.println("Starting balance:");
					double balance = Double.parseDouble(keyboard.nextLine());
					new Account(name, address, phoneNumber, balance);
					break;
				case "deposit":
					System.out.println("Enter account number:");
					String accountNumber = keyboard.nextLine();
					Account account = Account.deserialize(accountNumber);
					if (account != null) {
						System.out.println("Deposit how much?");
						account.deposit(Double.parseDouble(keyboard.nextLine()));
					} else {
						System.out.println("Account not found.");
					}
					break;
				case "withdraw":
					System.out.println("Enter account number:");
					String accountNumberWithdraw = keyboard.nextLine();
					Account accountWithdraw = Account.deserialize(accountNumberWithdraw);
					if (accountWithdraw != null) {
						System.out.println("Withdraw how much?");
						accountWithdraw.withdraw(Double.parseDouble(keyboard.nextLine()));
					} else {
						System.out.println("Account not found.");
					}
					break;
				case "balance":
					System.out.println("Enter account number:");
					String accountNumberBalance = keyboard.nextLine();
					Account accountBalance = Account.deserialize(accountNumberBalance);
					if (accountBalance != null) {
						System.out.println("Account balance: " + accountBalance.getBalance());
					} else {
						System.out.println("Account not found.");
					}
					break;
				default:
					System.out.println("Wrong command");
					break;
				}
				System.out.println("CREATE account, DEPOSIT, WITHDRAW, BALANCE, EXIT");
				input = keyboard.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
