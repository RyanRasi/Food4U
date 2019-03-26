package food4U;

import java.util.Scanner;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class FindByIngredients {
	
	

	@SuppressWarnings("resource")
	public static void findByIngredients(String ingredients){
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.rapidapi.com/recipes/searchComplex?"+Main.diet+"&includeIngredients="+ingredients+Main.excludedIngredients+Main.userIntolerances+"&type=main+course&limitLicense=false&offset=0&number=3")
			.header("X-Mashape-Key", Main.RapidAPIKey)
					.header("Accept", "application/json")
					.asJson();
			
			JSONObject json = new JSONObject(response.getBody());
			Main.AllRecipe = (json.toString(4));
			//System.out.print(AllRecipe);
			
			String delete[] = {"array", "number", "totalResults", "offset" , "object", "processingTimeMs", "baseUri:", "https://spoonacular.com/recipeImages/", "results", "[", "]", ",", "                " , ""}; //Deletes excess JSON
			String result = Main.AllRecipe;	//New variable to store temp recipes
			for (int i = 0; i < delete.length; ++i) //Find all occurances of array in JSON to remove
			{
			    result = result.replace(delete[i], "");
			}
			//This parses all the extra characters and special charatcers out of the string
String RecipeSorted = result.replaceAll("[{}\"]", ""); //Replaces random special characters
			RecipeSorted = RecipeSorted.replace("", "");
			
			RecipeSorted = RecipeSorted.replace("image: ", "");
			RecipeSorted = RecipeSorted.replace("missedIngredientCount: ", "");
			RecipeSorted = RecipeSorted.replace("id: ", "");
			RecipeSorted = RecipeSorted.replace("title: ", "");
			RecipeSorted = RecipeSorted.replace("usedIngredientCount: ", "");
			RecipeSorted = RecipeSorted.replace("likes: ", "");
			
			String[] parts = RecipeSorted.split("\n"); //Puts whole string into an array for easy extraction with the desired numbers correlating to data
			
			Main.recipe1Image = parts[9];
			Main.recipe1MissedIngredientCount = parts[10];
			Main.recipe1ID = parts[11];
			Main.recipe1Title = parts[12];
			Main.recipe1UsedIngredientCount = parts[14];
			Main.recipe1Likes = parts[15];
			
			Main.recipe2Image = parts[18];
			Main.recipe2MissedIngredientCount = parts[19];
			Main.recipe2ID = parts[20];
			Main.recipe2Title = parts[21];
			Main.recipe2UsedIngredientCount = parts[23];
			Main.recipe2Likes = parts[24];
			
			Main.recipe3Image = parts[27];
			Main.recipe3MissedIngredientCount = parts[28];
			Main.recipe3ID = parts[29];
			Main.recipe3Title = parts[30];
			Main.recipe3UsedIngredientCount = parts[32];
			Main.recipe3Likes = parts[33];
			
			System.out.println("\nAvailable recipes as follows");
			
			System.out.println("\n\nRecipe 1:\n" + "Image: " + Main.recipe1Image + "\nMissing Ingredients: " + Main.recipe1MissedIngredientCount + "\nRecipe ID: " + Main.recipe1ID + "\nTitle: " + Main.recipe1Title + "\nUsed Ingredients: " + Main.recipe1UsedIngredientCount + "\nLikes: " + Main.recipe1Likes);
			System.out.println("\n\nRecipe 2:\n" + "Image: " + Main.recipe2Image + "\nMissing Ingredients: " + Main.recipe2MissedIngredientCount + "\nRecipe ID: " + Main.recipe2ID + "\nTitle: " + Main.recipe2Title + "\nUsed Ingredients: " + Main.recipe2UsedIngredientCount + "\nLikes: " + Main.recipe2Likes);
			System.out.println("\n\nRecipe 3:\n" + "Image: " + Main.recipe3Image + "\nMissing Ingredients: " + Main.recipe3MissedIngredientCount + "\nRecipe ID: " + Main.recipe3ID + "\nTitle: " + Main.recipe3Title + "\nUsed Ingredients: " + Main.recipe3UsedIngredientCount + "\nLikes: " + Main.recipe3Likes);
//Prints all the recipes out in a neat order		
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Recipe choice parameter start
		String recipechoice = null;
		do {
			System.out.println("\nEnter 1 - 3 to view a recipe in more detail or enter menu to go back to menu");
			Scanner menu = new Scanner(System.in);
			recipechoice = menu.next();
		} while  (!recipechoice.equals("1") && !recipechoice.equals("2") && !recipechoice.equals("3") && !recipechoice.equals("menu"));

	switch (recipechoice) { //Lets user view a recipe in more detail
	case "1":
		System.out.println("Recipe 1\n");
		
		Main.recipeID = Main.recipe1ID;
		
		FindByRecipe.findByRecipe(Main.recipeID);
		
		break;
	case "2":
		System.out.println("Recipe 2\n");
		
		Main.recipeID = Main.recipe2ID;
		
		FindByRecipe.findByRecipe(Main.recipeID);
		
		break;
	case "3":
		System.out.println("Recipe 3\n");
		
		Main.recipeID = Main.recipe3ID;
		
		FindByRecipe.findByRecipe(Main.recipeID);
		
		break;
	case "menu":
		MenuPage.menuPage(); //Links to menu page -- Efficiency ++
		}
	//Recipe choice parameter end
	}

}
