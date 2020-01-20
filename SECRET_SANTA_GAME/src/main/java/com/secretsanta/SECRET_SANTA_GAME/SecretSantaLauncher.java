package com.secretsanta.SECRET_SANTA_GAME;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class SecretSantaLauncher {
	static SecretSantaService secretSantaService = new SecretSantaService();
	static Scanner sc;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Do you want to play secret santa with already existing team or new team ");
		System.out.println("Chhose the correct option \n 1: Existing one \n 2: New Team");
		sc = new Scanner(System.in);
		int choose = sc.nextInt();
		switch (choose) {
		case 1:
			System.out.println("********Team Names*******\n");
			displayExistingTeamNames();
			System.out.println("Let's start");
			displayGame("existing");
			break;
		case 2:
			secretSantaService.createNewTeam();
			break;

		default:
			System.out.println("Invalid choice");
			break;
		}

	}

	private static void displayExistingTeamNames() {
		SecretSantaDao secretSantaDao = new SecretSantaDao();
		System.out.println(secretSantaDao.getAllChilds());

	}

	public static void displayGame(String type) {

		String choice;
		do {
			System.out.println("Enter youre userid");
			sc = new Scanner(System.in);
			String userId = sc.nextLine();
			boolean isValid = secretSantaService.patternMatchingCheck(userId);
			if (!isValid) {
				System.out.println("INVALID User ID--- \"no special chearter allowed, start from alphabets only\"");
				displayGame(type);
			}
			Map<String, String> mychild = secretSantaService.mySecretSantaIs(userId, type);
			if (null != mychild.get(userId))
				System.out
						.println("---------------- " + userId + "  Your child is------------: " + mychild.get(userId));

			System.out.println("Do you want continue?Y/N");
			choice = sc.nextLine();
			if (!(choice.equalsIgnoreCase("y"))) {
				System.out.println("**********************END OF GAME**************************\n");
				System.out.println("-------------------Secretsanta mapping---------------\n" + mychild);
				System.exit(1);
			} /*
				 * else { System.out.println("Invalid choice"); System.out.
				 * println("-------------------Your Secretsanta mapping---------------\n" +
				 * mychild); }
				 */
		} while (choice.equalsIgnoreCase("y"));
	}

}
