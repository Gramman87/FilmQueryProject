package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to SDVID-31 Film Database");

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean user = true;
		int userInput = 0;

		while (user) {
			printMenu();
			userInput = input.nextInt();
			input.nextLine();

			switch (userInput) {
			case 1:
				System.out.print("Please enter the film ID: ");

				userInput = input.nextInt();
				input.nextLine();

				System.out.println("Film " + userInput + " details below: ");

				Film userSelection = db.findFilmById(userInput);
				if (userSelection == null) {
					System.out.println("========================================================");
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("Sorry, no such film could be found... please try again.");
				} else {
					userSelection.simpleString();
					System.out.println("========================================================");
				}

				break;

			case 2:
				System.out.print("Please enter your search parameter: ");

				String keyword = "%" + input.nextLine() + "%";

				List<Film> matches = db.keywordSearch(keyword);

				if (matches.size() == 0) {
					System.out.println("========================================================");
					System.out.println();
					System.out.println();
					System.out.println();
					System.out.println("Sorry, no matches found for that parameter... please try again.");
				} else {
					for (Film film : matches) {
						film.simpleString();
						System.out.println();
					}
				}

				break;

			case 3:
				System.out.println("Thanks for using SDVID-31 Film Database... Goodbye!");
				user = false;
				break;
			}
		}
	}

	private void printMenu() {

		System.out.println();
		System.out.println("======= SEARCH MENU ======");
		System.out.println("|                        |");
		System.out.println("|      1. By Film ID     |");
		System.out.println("|      2. By Keyword     |");
		System.out.println("|         3. Exit        |");
		System.out.println("|                        |");
		System.out.println("==========================");
		System.out.println();
		System.out.print("Please select from the menu above: ");

	}

}
