/**
 * 
 */
package no.uib.info216.facebook;
import facebook4j.*;

import java.util.ArrayList;

public class Interests {

	private ArrayList<String> tvShows = new ArrayList<String>();
	private ArrayList<String> movies = new ArrayList<String>();
	private ArrayList<String> music = new ArrayList<String>();
	private ArrayList<String> book = new ArrayList<String>();
	private ArrayList<String> games = new ArrayList<String>();
	private ArrayList<String> events = new ArrayList<String>();
	private ArrayList<String> likes = new ArrayList<String>();


	/**
	 * This is a simple method which prints all the interests the current user
	 * has and seperates them with categories.
	 * 
	 * @param fb a facebook user
	 * @throws FacebookException
	 */
	public void getInterests(Facebook fb) throws FacebookException {

		for (Television t : fb.getTelevision()) {
			tvShows.add(t.getName());
		}

		for (Movie m : fb.getMovies()) {
			movies.add(m.getName());
		}

		for (Music m : fb.getMusic()) {
			music.add(m.getName());
		}

		for (Book b : fb.getBooks()) {
			book.add(b.getName());
		}

		for (Game g : fb.getGames()) {
			games.add(g.getName());
		}

		for (Event e : fb.getEvents()) {
			events.add(e.getName());

		for (Like l : fb.getUserLikes()) {
				likes.add(l.getName());
		}
	}
}

	/**
	 * @return the tvShows
	 */
	public ArrayList<String> getTvShows() {
		return tvShows;
	}

	/**
	 * @return the movies
	 */
	public ArrayList<String> getMovies() {
		return movies;
	}

	/**
	 * @return the music
	 */
	public ArrayList<String> getMusic() {
		return music;
	}

	/**
	 * @return the book
	 */
	public ArrayList<String> getBook() {
		return book;
	}

	/**
	 * @return the games
	 */
	public ArrayList<String> getGames() {
		return games;
	}

	/**
	 * @return the events
	 */
	public ArrayList<String> getEvents() {
		return events;
	}

	/**
	 * @return the likes
	 */
	public ArrayList<String> getLikes() {
		return likes;
	}
}
