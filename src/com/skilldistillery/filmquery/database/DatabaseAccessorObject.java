package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private final String user = "student";
	private final String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM film WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getDate("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));

				film.setActors(findActorsByFilmId(filmId));
				film.setLanguage(findFilmLanguage(filmId));

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return film;

	}

	@Override
	public String findFilmLanguage(int filmId) {
		String language = "";

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT name FROM film JOIN language ON language_id = language.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				language = rs.getString("name");
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e);
		}

		return language;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM actor JOIN film_actor ON actor.id = actor_id JOIN film ON film_id = film.id WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				actors.add(new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));

			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.err.println(e);
		}

		return actors;
	}

	@Override
	public List<Film> keywordSearch(String keyword) {
		List<Film> films = new ArrayList<>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "SELECT * FROM film WHERE title LIKE ? OR description LIKE ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, keyword);
			stmt.setString(2, keyword);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				films.add(new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getDate("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features")));
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			System.out.println(e);
		}

		for (Film film : films) {
			film.setLanguage(findFilmLanguage(film.getFilmId()));
		}

		return films;
	}

}
