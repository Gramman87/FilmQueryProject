package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?usesSSL=flase";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}

	private Connection databaseConnection() {
		String user = "student";
		String pass = "pass";

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(URL, user, pass);
		} catch (SQLException e) {
			System.err.println(e);
		}

		return conn;

	}

	@Override
	public Film findFilmById(int filmId) {

		return null;
	}

	@Override
	public Actor findActorById(int actorId) {

		return null;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors;

		return null;
	}

}
