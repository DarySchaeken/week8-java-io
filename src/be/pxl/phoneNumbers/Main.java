package be.pxl.phoneNumbers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try (FileOutputStream outputStream = new FileOutputStream("Application.properties")) {
			if(!Files.exists(Paths.get("C:\\USERS\\DARY\\OPDRACHTEN\\OPDRACHT1\\FASE1\\phonedirectory.txt")))
			Files.createFile(Paths.get("C:\\USERS\\DARY\\OPDRACHTEN\\OPDRACHT1\\FASE1\\phonedirectory.txt"));
			Properties properties = new Properties();
			properties.setProperty("File", "C:\\USERS\\DARY\\OPDRACHTEN\\OPDRACHT1\\FASE1\\phonedirectory.txt");
			properties.storeToXML(outputStream, "Application properties");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String filePath = null;
		
		try (FileInputStream inputStream = new FileInputStream("Application.properties");) {
			Properties properties = new Properties();
			properties.loadFromXML(inputStream);
			filePath = properties.getProperty("File");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
				Scanner keyboard = new Scanner(System.in);
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
			HashMap<String, String> telephoneNumbers = new HashMap<String, String>();
			HashMap<String, String> newNumbers = new HashMap<String, String>();
			String line = bufferedReader.readLine();
			while (line != null) {
				telephoneNumbers.put(line.substring(0, line.indexOf(';')), line.substring(line.indexOf(';') + 1));
				line = bufferedReader.readLine();
			}
			System.out.println("SEARCH or ADD or EXIT?");
			String commandInput = keyboard.nextLine();
			while (!commandInput.equalsIgnoreCase("exit")) {
				if (commandInput.equalsIgnoreCase("search")) {
					System.out.println("Enter name:");
					String nameInput = keyboard.nextLine();
					String telephoneResult = telephoneNumbers.get(nameInput);
					if (telephoneResult != null) {
						System.out.println(telephoneResult);
					} else {
						System.out.println("No result.");
					}
				} else if (commandInput.equalsIgnoreCase("add")) {
					System.out.println("Enter name:");
					String newNameInput = keyboard.nextLine();
					if (telephoneNumbers.containsKey(newNameInput)) {
						System.out.println("This name already exists");
					} else {
						System.out.println("Enter a number:");
						String number = keyboard.nextLine();
						System.out.println("Another number? Enter Y or N:");
						String anotherNumberString = keyboard.nextLine();
						while (anotherNumberString.equalsIgnoreCase("Y")) {
							System.out.println("Enter a number:");
							number = number + ";" + keyboard.nextLine();
							System.out.println("Another number? Enter Y or N:");
							anotherNumberString = keyboard.nextLine();
						}
						telephoneNumbers.put(newNameInput, number);
						newNumbers.put(newNameInput, number);

					}
				} else {
					System.out.println("Incorrect Command!");
				}
				System.out.println("SEARCH or ADD or EXIT?");
				commandInput = keyboard.nextLine();
			}

			String[] output = newNumbers.entrySet().stream().map(e -> String.format("%s;%s", e.getKey(), e.getValue()))
					.toArray(String[]::new);
			for (String s : output) {
				bufferedWriter.write(s);
				bufferedWriter.newLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
