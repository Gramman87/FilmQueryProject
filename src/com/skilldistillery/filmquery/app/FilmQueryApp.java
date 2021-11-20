package com.skilldistillery.filmquery.app;

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
		
		boolean appExit = false;

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

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
