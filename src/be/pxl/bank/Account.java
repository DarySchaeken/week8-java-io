package be.pxl.bank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	private static int accountNumberGen = 1;
	private String name;
	private String phoneNumber;
	private String address;
	private double balance;
	private String accountNumber;

	public Account(String name, String address, String phoneNumber, double balance) throws FileNotFoundException, IOException {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.balance = balance;
		accountNumber = generateAccountNumber();
		System.out.println("Your account number is " +accountNumber);
		serialize();
	}

	private static String generateAccountNumber() {
		StringBuilder stringBuilder = new StringBuilder("REK" + accountNumberGen);
		while (stringBuilder.length() < 6) {
			stringBuilder.insert(3, '0');
		}
		accountNumberGen++;
		return stringBuilder.toString();
	}
	
	public void deposit(double deposit) throws FileNotFoundException, IOException{
		balance+=deposit;
		serialize();
	}
	
	public void withdraw(double withdraw) throws FileNotFoundException, IOException{
		if(balance - withdraw >= 0){
			balance-=withdraw;
		} else {
			System.out.println("Balance not big enough.");
		}
		serialize();
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void serialize() throws FileNotFoundException, IOException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(accountNumber + ".ser");
				ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
			outputStream.writeObject(this);
		} 
	}

	public static Account deserialize(String accountNumber) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (FileInputStream fileInputStream = new FileInputStream(accountNumber + ".ser");
				ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);) {
				return (Account) inputStream.readObject();
		}
	}

}
