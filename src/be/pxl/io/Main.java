package be.pxl.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	// Will work on windows systems

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));
		Path userHomePath = Paths.get(System.getProperty("user.home"));
		Path phase2Path = userHomePath.resolve("Opdrachten\\Opdracht2");
		Path bijlage1Path = userHomePath.resolve("Opdrachten\\Opdracht2\\bijlage1.txt");
		Path outputPath = userHomePath.resolve(bijlage1Path.getParent() + "\\output.txt");
		BufferedWriter bufferedWriter = null;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(bijlage1Path.toString()))) {

			Files.createDirectories(userHomePath.resolve("Opdrachten\\Opdracht1\\Fase1"));
			Files.createDirectories(userHomePath.resolve("Opdrachten\\Opdracht1\\Fase2"));
			Files.createDirectories(userHomePath.resolve("Opdrachten\\Opdracht2"));
			if (Files.notExists(userHomePath.resolve("Opdrachten\\Opdracht2\\bijlage1.txt"))) {
				// Replace first parameter with location of bijlage1.txt on your
				// system.
				Files.copy(userHomePath.resolve("Documents\\School\\17-18\\Java Advanced\\bestanden\\bijlage1.txt"),
						userHomePath.resolve("Opdrachten\\Opdracht2\\bijlage1.txt"));
			}
			// Requested method prints
			System.out.println("Requested method prints");
			System.out.println(phase2Path.toString());
			System.out.println(phase2Path.getFileName());
			System.out.println(phase2Path.getName(0));
			System.out.println(phase2Path.getNameCount());
			System.out.println(phase2Path.subpath(0, 2));
			System.out.println(phase2Path.getParent());
			System.out.println(phase2Path.getRoot());
			// .getRoot() on a relative path returns \.
			System.out.println(Paths.get("\\School").getRoot());
			TreeMap<String, Integer> words = new TreeMap<String, Integer>();
			String line = bufferedReader.readLine();
			while (line != null) {
				if (words.containsKey(line)) {
					words.put(line, words.get(line).intValue() + 1);
				} else {
					words.put(line, 1);
				}
				line = bufferedReader.readLine();
			}
			Files.deleteIfExists(outputPath);
			Files.createFile(outputPath);
			bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toString()));
			String[] sortedWords = words.keySet().stream().toArray(String[]::new);
			for (String s : sortedWords) {
				bufferedWriter.write(s);
				bufferedWriter.newLine();
			}
			words.values().stream().forEach(System.out::println);
			bufferedWriter.newLine();
			String[] sortedOccurence = words.entrySet().stream().sorted(new Comparator<Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					if (o1.getValue().intValue() > o2.getValue().intValue()) {
						return -1;
					} else {
						return 1;
					}
				}
			}).map(e -> e.getKey()).toArray(String[]::new);
			for (String s : sortedOccurence) {
				bufferedWriter.write(s);
				bufferedWriter.newLine();
			}

			bufferedWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
