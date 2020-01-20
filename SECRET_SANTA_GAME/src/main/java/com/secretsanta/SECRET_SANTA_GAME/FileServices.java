package com.secretsanta.SECRET_SANTA_GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileServices {
	private static List<String> listOfNames;
	private static Scanner sc;
	private static SecretSantaService ssc;

	public static List<String> makeListOfNames(Scanner sc) {
		listOfNames = new ArrayList<String>();
		System.out.println("Enter name");
		String done = "";
		boolean isValid = false;
		ssc = new SecretSantaService();
		while (!done.equalsIgnoreCase("done")) {
			String name = sc.nextLine().toLowerCase().trim();
			isValid = ssc.patternMatchingCheck(name);
			if (!isValid) {
				System.out.println("INAVALID USERID-\" no special chearter allowed, start from alphabets only \"");
			} else if (listOfNames.isEmpty()) {
				if (name.equalsIgnoreCase("done"))
					done = "done";
				else
					listOfNames.add(name);
			} else if (listOfNames.contains(name)) {
				System.out.println("Name already exist");
			} else if (name.equalsIgnoreCase("done"))
				done = "done";
			else
				listOfNames.add(name);
		}
		return listOfNames;
	}

	@SuppressWarnings("static-access")
	public static List<String> readFromFile(String filename) {
		Scanner inputStream;
		sc = new Scanner(System.in);
		try {
			inputStream = new Scanner(new File("File.txt"));
			while (inputStream.hasNextLine()) {
				String line1 = inputStream.nextLine();

			}
			inputStream.close();

		} catch (FileNotFoundException e) {
			System.out.println("Error " + e.getMessage() + ". Cannot find  " + filename);
		}

		return listOfNames;
	}

}
