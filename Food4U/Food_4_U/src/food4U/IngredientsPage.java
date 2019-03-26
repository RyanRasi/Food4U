package food4U;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class IngredientsPage {
	
	FindByIngredients findByIngredients = new FindByIngredients();
	
	FindByRecipe findByRecipe = new FindByRecipe();
	//Ingredient Page Start
	@SuppressWarnings("resource")
	public static void ingredientsPage ()	{
		
		try {
			
		FileReader fr = new FileReader("settings.txt");
		BufferedReader ReadFileBuffer = new BufferedReader(fr);
		//Reads text file
		Main.diet = ReadFileBuffer.readLine();
		Main.prefferedIntolerances = ReadFileBuffer.readLine();
		Main.excludedIngredients = ReadFileBuffer.readLine();

		ReadFileBuffer.close();//Closes file
				
		} catch (IOException E)
		{
			System.out.println(E.getMessage());
		}
		
		if (!Main.diet.equals("null")) {
			Main.diet = "diet=" + Main.diet; 
		} else if (Main.diet.equals("null")) {
			Main.diet=""; //Skips excluded ingredients flag if == none
		} 
		if (!Main.prefferedIntolerances.equals("null")) {
			Main.prefferedIntolerances = Main.prefferedIntolerances.replace(",", "%2C"); //Replaces commas with the standard text for spaces in links
			Main.userIntolerances = "&intolerances=" + Main.prefferedIntolerances; 
		} else if (Main.prefferedIntolerances.equals("null")) {
			Main.userIntolerances=""; //Skips intolerance flag if == none
		}
		if (!Main.excludedIngredients.equals("null")) {
			Main.excludedIngredients = Main.excludedIngredients.replace(",", "%2C");
			Main.excludedIngredients = "&excludeIngredients=" + Main.excludedIngredients;
		} else if (Main.excludedIngredients.equals("null")) {
			Main.excludedIngredients=""; //Skips excluded ingredients flag if == none
		}
		
	Scanner reader = new Scanner(System.in);
	System.out.println("What ingredients do you have available (Seperated by a comma ' , ') ?");
	String ingredients = reader.next(); //Reads for the inputs
	ingredients = ingredients.replace(",", "%2C");
	FindByIngredients.findByIngredients(ingredients); //Sends inputs to the FindByIngredients class
	}
	

	//Ingredient Page End

}
