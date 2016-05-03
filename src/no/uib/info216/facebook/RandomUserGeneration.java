/**
 * 
 */
package no.uib.info216.facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import no.uib.info216.RDF.RDFHandler;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;


/**
 * @author Marius This is the class which runs the program.
 */
public class RandomUserGeneration {

	//ArrayLists
	private ArrayList<String> firstNames = new ArrayList<String>();
	private ArrayList<String> lastNames = new ArrayList<String>();

	/**
	 * This is the Constructor for the RandomUserGeneration class
	 * which only runnes a single method on creation.
	 */
	public RandomUserGeneration(){
		fillNameLists();
	}

	/**
	 * This method selects randomly chosen Strings from an arrayList
	 * and adds these to another list. Only 1/4 or 1/2 of Strings from the
	 * Already existing list are added to the new randomList which is returned.
	 *
	 * @param arrayList to choose random values from.
	 * @return arrayList with random values from param.
	 */
	public ArrayList<String> selectRandomInterestsFromList(ArrayList<String> arrayList) {
		Random random = new Random();

		Set<String> randomUniqueList = new HashSet<String>();
		ArrayList<String> randomList = new ArrayList<String>();

		int size = 0;
		if (arrayList.size() < 10) {
			size = arrayList.size() / 2;
		} else {
			size = arrayList.size() / 4;
		}

		for (int i = 0; i < size; i++) {
			int n = random.nextInt(arrayList.size()) + 0;
			String s = arrayList.get(n);
			randomUniqueList.add(s);
		}
		randomList.addAll(randomUniqueList);
		return randomList;
	}

	/**
	 * This method creates a new list of Facebook users with lists of interests
	 * that is randomly generated by picking random interests from an already
	 * existing list. A new ArrayList is returned containing the new FaceBookUser's.
	 *
	 * @param numberOfUsers to be generated.
	 * @return ArrayList - A list of FacebookUser objects.
	 */
	public ArrayList<FacebookUser> createUserWithRandomInterests(int numberOfUsers, Interests ir) {
		Random random = new Random();

		ArrayList<FacebookUser> randomUsers = new ArrayList<FacebookUser>();
		for (int i = 0; i < numberOfUsers; i++) {
			ArrayList<String> tvShows = selectRandomInterestsFromList(ir.getTvShows());
			ArrayList<String> movies = selectRandomInterestsFromList(ir.getMovies());
			ArrayList<String> music = selectRandomInterestsFromList(ir.getMusic());
			ArrayList<String> book = selectRandomInterestsFromList(ir.getBook());
			ArrayList<String> games = selectRandomInterestsFromList(ir.getGames());
			ArrayList<String> events = selectRandomInterestsFromList(ir.getEvents());
			ArrayList<String> likes = selectRandomInterestsFromList(ir.getLikes());

			FacebookUser fu = new FacebookUser(tvShows, movies, music, book, games, events, likes);
			int randomFirst = random.nextInt(firstNames.size()) + 0;
			int randomLast = random.nextInt(lastNames.size()) + 0;
			fu.setName(firstNames.get(randomFirst));
			fu.setLastName(lastNames.get(randomLast));
			randomUsers.add(fu);
		}
		return randomUsers;
	}

	/**
	 * This method was created for testing purposes only and does not
	 * really do much other than check if lists are empty or not.
	 * An example of how it can be used could be.
	 * test(createUserWithRandomInterests(5); Also shown in the main method.
	 *
	 * @param a - An arrayList of FacebookUser objects.
	 */
	public void test(ArrayList<FacebookUser> a) {

		for (int i = 0; i < a.size(); i++) {
			for (String s : a.get(i).getEvents()) {
				System.out.println(s);
			}
			System.out.println("NUMBER: " + (i + 1) + "  ###############################################");
		}
	}

	/**
	 * This method selects a random user from a list
	 * containing FacebookUser objects.
	 * @param users a list of FacebookUser objects
	 * @return fu - a single FacebookUser
	 */
	public FacebookUser selectRandomUser(ArrayList<FacebookUser> users) {
		Random random = new Random();
		int user = random.nextInt(users.size()) + 0;

		FacebookUser fu = users.get(user);
		return fu;
	}


	/**
	 * This method reads an xml file called Names.xml and fills
	 * the first and last name ArrayLists with the names from
	 * this file. They are then used while generating new random
	 * users.
	 */
	public void fillNameLists() {
		File file = new File("Names.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodelist = doc.getElementsByTagName("name");
			for (int i = 0; i < nodelist.getLength(); i++) {
				Node node = nodelist.item(i);
				Element eElement = (Element) node;

				firstNames.add(eElement.getAttribute("firstName"));
				lastNames.add(eElement.getAttribute("lastName"));
			}
		}catch(Exception e){
				e.printStackTrace();

			}

	}
}
