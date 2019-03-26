package food4U;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RandomPage {

	//Random recipe page Start
		@SuppressWarnings("resource")
		public static void randomPage ()	{
			
			String recipeTitle = null;
			int recipeReadyInMinutes = 0;
			int recipeServings = 0;
			JSONArray RecipeIngredients = null;
			String nameAndAmountofIngredient = null; //Says it's not used but it has to be initialised here - Is the variable for the name and the amount required
			String recipeInstructions = null;
			String recipeSourceName = null;
			String recipeSourceURL = null;
			
			System.out.println("Please select which parameters you would like to be applied to the recipe");
	//////////////////////////
			String parameters = null;
	//////////////////////////
			Scanner reader = new Scanner(System.in);
			System.out.println("Parameters available include - Vegetarian, Vegan, GlutenFree, DairyFree, VeryHealthy, Cheap, VeryPopular, Sustainable and Ketogenic.");
			System.out.println("Please seperate paramters by a comma e.g (' , ') ");
			parameters = reader.next();
	//////////////////////////////////////
	parameters = parameters.replace(",", "%2C");
			//////////////////////////////
			try {
				HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/random?number=1&tags="+parameters)
				.header("X-Mashape-Key", Main.RapidAPIKey)
						.header("Accept", "application/json")
						.asJson();
				
				JSONObject obj = new JSONObject(response.getBody());
				
				JSONArray recipeParse = obj.getJSONObject("object").getJSONArray("recipes");
				JSONObject parseRecipe = recipeParse.getJSONObject(0);
				
				//Sets variables for the parameters from the JSON file
				
				recipeTitle = parseRecipe.getString("title");
				System.out.println("Recipe Title: " + recipeTitle);
				
				String recipeImage = parseRecipe.getString("image");
				System.out.println("Recipe Image: " + recipeImage);
				
				recipeReadyInMinutes = parseRecipe.getInt("readyInMinutes");
				System.out.println("Ready in Minutes: " + recipeReadyInMinutes);
				
				recipeServings = parseRecipe.getInt("servings");
				System.out.println("Recipe Servings" + recipeServings);
				
				RecipeIngredients = parseRecipe.getJSONArray("extendedIngredients");
				System.out.println("\nIngredients:\n");
								
				
				for (int i = 0; i < RecipeIngredients.length(); i++)
				{
				    nameAndAmountofIngredient += RecipeIngredients.getJSONObject(i).getString("original") + "\n";
				    
				    System.out.println(RecipeIngredients.getJSONObject(i).getString("original"));
				}
				
				recipeInstructions = parseRecipe.getString("instructions");
				recipeInstructions = recipeInstructions.replace("                                                                                ","");
				recipeInstructions = recipeInstructions.replace("                          ","");
				recipeInstructions = recipeInstructions.replace("              ","");
				recipeInstructions = recipeInstructions.replace(".",". \n");

				System.out.println("\nRecipe Instructions:\n" + recipeInstructions);	
				
				recipeSourceName = parseRecipe.getString("sourceName");
				System.out.println("\nRecipe Source: " + recipeSourceName);
				
				recipeSourceURL = parseRecipe.getString("spoonacularSourceUrl");
				System.out.println("\nRecipe URL: " + recipeSourceURL);
				
			} catch (UnirestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("\nWould you like to save the recipe as a email? Or go back to the menu?");
			String afterChoice = null;
			do {
				System.out.println("email or menu"); //Checks for input whether email or menu
				Scanner choice = new Scanner(System.in);
				afterChoice = choice.next().toLowerCase();
			} while  (!afterChoice.equals("email") && !afterChoice.equals("menu"));
		switch (afterChoice) {
		case "email":
			//PDF stuff goes here
			Desktop desktop;
			if (Desktop.isDesktopSupported() 
			    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
			  URI mailto = null;
			try {
				recipeTitle = recipeTitle.replace(" ", "%20");
				nameAndAmountofIngredient = nameAndAmountofIngredient.replace(" ", "%20");
				nameAndAmountofIngredient = nameAndAmountofIngredient.replace("\n", "%0A");
				nameAndAmountofIngredient = nameAndAmountofIngredient.replace("null", "");
				recipeInstructions = recipeInstructions.replace(" ", "%20");
				recipeInstructions = recipeInstructions.replace("\n", "%0A");
				recipeSourceName = recipeSourceName.replace(" ", "%20");
				recipeSourceURL = recipeSourceURL.replace(" ", "%20");
				
				
				mailto = new URI("mailto:EnterYourEmailAddress?subject=" + recipeTitle + "&body=Recipe%20Title:%20" + recipeTitle + "%0A%0ARecipe%20Cook%20Time:%20" + recipeReadyInMinutes + "%20minutes" + "%0ARecipe%20Servings:%20" + recipeServings + "%20people" + "%0A%0ARecipe%20Ingredients:%20%0A" + nameAndAmountofIngredient + "%0A%0ARecipe%20Instructions:%20%0A" + recipeInstructions + "%0A%0ARecipe%20Source:%20" + recipeSourceName);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  try {
				desktop.mail(mailto);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} else {
			  
			  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
			}
			//PDF saved
			System.out.println("Email saved");
			do {
				System.out.println("Enter menu if you wish to return back to the menu"); // After PDF saved they can stay on the recipe page or just go back to the menu
				Scanner choice = new Scanner(System.in);//Switch case is twice for validation as well as there is only one thing the user can enter - menu
				afterChoice = choice.next().toLowerCase();
			} while  (!afterChoice.equals("menu"));
			switch (afterChoice) {
			case "menu":
				MenuPage.menuPage();
				break;
			}
			break;
		case "menu":
			MenuPage.menuPage();
			break;
		}
			
	}



}
