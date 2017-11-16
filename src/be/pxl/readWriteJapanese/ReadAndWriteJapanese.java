package be.pxl.readWriteJapanese;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadAndWriteJapanese {
	public static void main(String[] args) {
		System.out.println("file.encoding=" + System.getProperty("file.encoding"));
		Path input = Paths.get(System.getProperty("user.home"), "Opdrachten/Opdracht2/japanese.txt");
		try (Reader reader = new FileReader(input.toString())) {
			int karakter = 0;
			while ((karakter = reader.read()) != -1) {
				System.out.println(karakter + " " + (char)karakter);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found.");
		} catch (IOException e) {
			System.out.println("An error occured processing the file" + e.getMessage());
		}
		
		System.out.println("UTF-8");
		try (InputStream inputStream = Files.newInputStream(input);
				Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
			int karakter = 0;
			while ((karakter = reader.read()) != -1) {
				System.out.println(karakter+ " " + (char)karakter);
			}

		} catch (FileNotFoundException e) {
			System.out.println("File cannot be found.");
		} catch (IOException e) {
			System.out.println("An error occured processing the file" + e.getMessage());
		}
	}
}
