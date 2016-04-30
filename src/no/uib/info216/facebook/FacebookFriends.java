/**
 * 
 */
package no.uib.info216.facebook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.jena.base.Sys;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import facebook4j.*;
import facebook4j.auth.AccessToken;


/**
 * @author Marius This is the class which runs the program.
 */
public class FacebookFriends {

	// These are the fields for the main class.
	private static String appId = "1565029937145946";
	private static String appSecret = "69fec6df69ae3da5a308d62005f85976";
	private static String accessToken = "EAAWPYu13aFoBAAra0ZBcGf7aeYVKcTBveiR45Hf5aKpUr46ZCiO2sfdtMVEYwMarUKNRYzV5lZCOjEPe2YJKLWWQuUJDEfehHGrQfWlxr5qo0pmr6Xd47jwqEMutZA91iWKr13huOZAlFIZBarDjZCgrt141CUnqMIZD";
	private static String appToken = "1565029937145946|RVWOId2jQZjW89yHa9fONbi4rto";
	private static Facebook user = new FacebookFactory().getInstance();
	private static CreatingRdf cr = new CreatingRdf();
	private static OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, cr.getModel());


	/**
	 * This is the main method which runs the program.
	 * 
	 * @param args
	 * @throws FacebookException
	 */
	public static void main(String[] args) throws FacebookException {

		user.setOAuthAppId(appId, appSecret);
		user.setOAuthPermissions(appToken);
		user.setOAuthAccessToken(new AccessToken(accessToken));

		cr.getIr().getInterests(user);
		cr.createModel();
		writeToFile(user);

		test(createUserWithRandomInterests(4)); //Creates 4 "Fake" users.
	}

	/**
	 * This method writes the ontolgy model to a .ttl file. Which is rdf turtle
	 * syntax.
	 * @throws FacebookException 
	 * @throws IllegalStateException 
	 */
	public static void writeToFile(Facebook fb) throws IllegalStateException, FacebookException {
		FileWriter out = null;
		try {
			out = new FileWriter(fb.getId() + ".ttl");
			m.write(out, "TURTLE");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException ignore) {

				}
			}
		}
	}

	/**
	 * This method selects randomly chosen Strings from an arrayList
	 * and adds these to another list. Only 1/4 of Strings from the
	 * Already existing list are added to the new randomList which is returned.
	 * @param arrayList to choose random values from.
	 * @return arrayList with random values from param.
     */
	public static ArrayList<String> selectRandomInterestsFromList (ArrayList<String> arrayList){
		Random random = new Random();

		Set<String> randomUniqueList = new HashSet<String>();
		ArrayList<String> randomList = new ArrayList<String>();

		int size = 0;
		if(arrayList.size() < 10){
			size = arrayList.size()/2;
		} else{
			size = arrayList.size()/4;
		}

		for(int i = 0;i < size; i++){
			int  n = random.nextInt(arrayList.size()) + 0;
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
	 * @param numberOfUsers to be generated.
	 * @return ArrayList - A list of FacebookUser objects.
	 */
	public static ArrayList<FacebookUser> createUserWithRandomInterests(int numberOfUsers){
		ArrayList<FacebookUser> randomUsers = new ArrayList<FacebookUser>();
		for(int i = 0; i < numberOfUsers; i++) {
			ArrayList<String> tvShows = selectRandomInterestsFromList(cr.getIr().getTvShows());
			ArrayList<String> movies = selectRandomInterestsFromList(cr.getIr().getMovies());
			ArrayList<String> music = selectRandomInterestsFromList(cr.getIr().getMusic());
			ArrayList<String> book = selectRandomInterestsFromList(cr.getIr().getBook());
			ArrayList<String> games = selectRandomInterestsFromList(cr.getIr().getGames());
			ArrayList<String> events = selectRandomInterestsFromList(cr.getIr().getEvents());
			ArrayList<String> likes = selectRandomInterestsFromList(cr.getIr().getLikes());

			FacebookUser fu = new FacebookUser(tvShows, movies, music, book, games, events, likes);
			randomUsers.add(fu);
		}

		return randomUsers;
	}

	/**
	 * This method was created for testing purposes only and does not
	 * really do much other than check if lists are empty or not.
	 * An example of how it can be used could be.
	 * test(createUserWithRandomInterests(5); Also shown in the main method.
	 * @param a - An arrayList of FacebookUser objects.
     */
	public static void test(ArrayList<FacebookUser> a){

		for (int i = 0; i < a.size(); i++){
			for (String s : a.get(i).getEvents()){
				System.out.println(s);
			}
			System.out.println("NUMBER: " + (i+1) + "  ###############################################");
		}
	}
}
