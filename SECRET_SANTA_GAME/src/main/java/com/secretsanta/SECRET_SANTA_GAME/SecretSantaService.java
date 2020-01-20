package com.secretsanta.SECRET_SANTA_GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class SecretSantaService {
	SecretSantaDao secretSantaDao = new SecretSantaDao();
	SecretSantaLauncher secretSantaLauncher = new SecretSantaLauncher();
	List<String> listOfNames;
	List<String> childs = secretSantaDao.getAllChilds();
	List<String> myChilds = new ArrayList();
	Set<String> secretSanta = new HashSet<String>();
	Map<String, String> secretSantaMapping = new HashMap<String, String>();
	String message;

	@SuppressWarnings("static-access")
	public Map<String, String> mySecretSantaIs(String userId, String type) {
		userId = userId.toLowerCase();
		if (type == "new") {
			myChilds = FileServices.readFromFile("File.txt");
			Collections.shuffle(myChilds);
		} else
			myChilds.addAll(childs);

		userId = userId.toLowerCase().trim();
		int length = myChilds.size();
		if (!myChilds.contains(userId)) {
			System.out.println("Invalid userid Select from existing userIds");
			secretSantaLauncher.displayGame(type);
		} else {
			for (String child : myChilds) {
				if (secretSantaMapping.isEmpty() && !(child.equalsIgnoreCase(userId))) {
					secretSantaMapping.put(userId, child);
					break;
				} else if (secretSantaMapping.containsKey(userId)) {
					message = "You're already secret santa";
					break;
				} else if (!(secretSantaMapping.containsValue(child)) && !child.equalsIgnoreCase(userId)) {
					if (secretSantaMapping.containsKey(child) && !secretSantaMapping.containsValue(child)) {
						boolean isBreak = false;
						if (secretSantaMapping.get(child).equalsIgnoreCase(userId)) {
							continue;
						} else {
							secretSantaMapping.put(userId, child);
							isBreak = true;
						}
						if (isBreak)
							break;
					}
					secretSantaMapping.put(userId, child);
					break;
				}
			}
			if (message != null) {
				System.out.println(message);
				message = null;
			}
		}
		return secretSantaMapping;
	}

	public void createNewTeam() throws FileNotFoundException {

		String filename = "File.txt";
		File fileName = new File(filename);
		Scanner sc = new Scanner(System.in);
		// String line = " ";
		System.out.println(" Enter \"done\"  to terminate");
		listOfNames = FileServices.makeListOfNames(sc);

		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println(listOfNames);
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error opening the file " + fileName + e.getMessage());
		}
		System.out.print("****************Below are list of Team Members****************");
		listOfNames = FileServices.readFromFile(filename);
		System.out.println("\n" + listOfNames);
		System.out.println("Wanna play game  y/n");
		String play = sc.nextLine();
		if (play.equalsIgnoreCase("Y")) {
			secretSantaLauncher = new SecretSantaLauncher();
			secretSantaLauncher.displayGame("new");
		} else if (play.equalsIgnoreCase("n")) {
			System.out.println("**********************END OF GAME**************************");
			System.exit(1);
		} else {
			System.out.println("Invalid Choice Play Again");
		}

	}

	public boolean patternMatchingCheck(String userId) {

		String regex = "^[a-zA-Z0-9_ ]*$";
		boolean isValid = false;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(userId);
		if (matcher.matches()) {
			isValid = true;
		}
		return isValid;

	}

}
