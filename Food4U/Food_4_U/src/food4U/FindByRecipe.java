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

public class FindByRecipe {

	public static void findByRecipe(String recipeID){
		
		String RecipeTitle = null;
		int RecipeCookTime = 0;
		int RecipeServings = 0;
		JSONArray RecipeIngredients;
		String nameAndAmountofIngredient = null;
		String RecipeInstructions = null;
		String RecipeCreditText = null;
		String RecipeSourceURL = null;
		
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/" +recipeID+ "/information?includeNutrition=false")
			.header("X-RapidAPI-Key", Main.RapidAPIKey)
					.header("Accept", "application/json")
					.asJson();
			
			JSONObject obj = new JSONObject(response.getBody());
		
			//JSON to Strings
			RecipeTitle = obj.getJSONObject("object").getString("title");
			System.out.println("Recipe Title: " + RecipeTitle); //Sets the vairables from the JSON object
			String RecipeImage = obj.getJSONObject("object").getString("image");
			System.out.println("Recipe Image: " + RecipeImage);
			RecipeCookTime = obj.getJSONObject("object").getInt("readyInMinutes");
			System.out.println("Recipe Cook Time: " + RecipeCookTime + "minutes");
			RecipeServings = obj.getJSONObject("object").getInt("servings");
			System.out.println("Recipe Servings: " + RecipeServings + "people");

			RecipeIngredients = obj.getJSONObject("object").getJSONArray("extendedIngredients");
			System.out.println("\nIngredients:");
			
			
			
			
			for (int i = 0; i < RecipeIngredients.length(); i++)
			{
			    nameAndAmountofIngredient += RecipeIngredients.getJSONObject(i).getString("original") + "\n";
			    
			    System.out.println(RecipeIngredients.getJSONObject(i).getString("original"));
			}
			
			RecipeInstructions = obj.getJSONObject("object").getString("instructions");
			RecipeInstructions = RecipeInstructions.replace("                                            ", "\n"); //Gets Instructions and beautifys it
			RecipeInstructions = RecipeInstructions.replace(". ", ".\n"); //Gets Instructions and beautifys it
			System.out.println("\nRecipe Instructions:\n" + RecipeInstructions + "\n");
			
			
			
			RecipeCreditText = obj.getJSONObject("object").getString("creditsText");
			System.out.println("Recipe Source: " + RecipeCreditText);
			RecipeSourceURL = obj.getJSONObject("object").getString("spoonacularSourceUrl");
			System.out.println("Recipe Source URL: " + RecipeSourceURL);
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\nWould you like to save the recipe as a email? Or go back to the menu?");
		String afterChoice = null;
		do {
			System.out.println("email or menu"); //Checks for input whether email or menu
			@SuppressWarnings("resource")
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
			RecipeTitle = RecipeTitle.replace(" ", "%20");
			nameAndAmountofIngredient = nameAndAmountofIngredient.replace(" ", "%20");
			nameAndAmountofIngredient = nameAndAmountofIngredient.replace("\n", "%0A");
			nameAndAmountofIngredient = nameAndAmountofIngredient.replace("null", "");
			RecipeInstructions = RecipeInstructions.replace(" ", "%20");
			RecipeInstructions = RecipeInstructions.replace("\n", "%0A");
			RecipeCreditText = RecipeCreditText.replace(" ", "%20");
			RecipeSourceURL = RecipeSourceURL.replace(" ", "%20");
			

			
			mailto = new URI("mailto:EnterYourEmailAddress?subject=" + RecipeTitle + "&body=Recipe%20Title:%20" + RecipeTitle + "%0A%0ARecipe%20Cook%20Time:%20" + RecipeCookTime + "%20minutes" + "%0ARecipe%20Servings:%20" + RecipeServings + "%20people" + "%0A%0ARecipe%20Ingredients:%20%0A" + nameAndAmountofIngredient + "%0A%0ARecipe%20Instructions:%20%0A" + RecipeInstructions + "%0A%0ARecipe%20Source:%20" + RecipeCreditText + "%0ARecipe%20Source%20URL:%20" + RecipeSourceURL);
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
			@SuppressWarnings("resource")
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
//Random recipe page end

}
