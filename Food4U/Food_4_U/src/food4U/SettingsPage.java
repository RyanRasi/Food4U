package food4U;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsPage {
	
	MenuPage menuPage = new MenuPage();
	

	//Settings Page Start
	@SuppressWarnings("resource")
	public static void settingsPage ()	{
		
		try {
		
		FileReader fr = new FileReader("settings.txt");
		BufferedReader ReadFileBuffer = new BufferedReader(fr);
//Reader of text file
		System.out.println("Your preferences are as follows:\n");

		
		System.out.println("Diet: " + ReadFileBuffer.readLine());
		System.out.println("Intolerances: " + ReadFileBuffer.readLine());
		System.out.println("Excluded Ingredients: " + ReadFileBuffer.readLine());
//States current preferences
		ReadFileBuffer.close();
				
		} catch (IOException E)
		{
			System.out.println(E.getMessage());
		}
		
		String amendFile = null;
		do {
		System.out.println("\nDo you wish to amend these preferences?\nY or N");
		Scanner preferenceAmend = new Scanner(System.in);
		amendFile = preferenceAmend.next().toLowerCase(); //Converts input to lowercase
		} while (!amendFile.equals("y") && !amendFile.equals("n"));
		//Validates the input so that the user can only enter the defined preference rule
		switch(amendFile) {
		case "n":
			MenuPage.menuPage();
		case "y":
			break;
		}
		
	String preferredDiet = null;
	do {
	System.out.println("Please state which diet you have. Possible values are: pescetarian, lacto vegetarian, ovo vegetarian, vegan, paleo, primal, and vegetarian.\nAlternatively enter none to skip.");
	Scanner diet = new Scanner(System.in);
	preferredDiet = diet.next().toLowerCase(); //Converts input to lowercase
	} while (!preferredDiet.equals("pescetarian") && !preferredDiet.equals("lactovegetarian") && !preferredDiet.equals("ovovegetarian") && !preferredDiet.equals("vegan") && !preferredDiet.equals("paleo") && !preferredDiet.equals("primal") && !preferredDiet.equals("vegetarian") && !preferredDiet.equals("none"));
	//Validates the input so that the user can only enter the defined diets
	switch(preferredDiet) {
		case "pescetarian":
			Main.userPreferences = "pescetarian";//Sets first line of txt to the diet
			Main.skipDiet = false;//Skip flag
			break;
		case "lacto vegetarian":
			Main.userPreferences = "lacto vegetarian";
			Main.skipDiet = false;
			break;
		case "ovo vegetarian":
			Main.userPreferences = "ovo vegetarian";
			Main.skipDiet = false;
			break;
		case "vegan":
			Main.userPreferences = "vegan";
			Main.skipDiet = false;
			break;
		case "paleo":
			Main.userPreferences = "paleo";
			Main.skipDiet = false;
			break;
		case "primal":
			Main.userPreferences = "primal";
			Main.skipDiet = false;
			break;
		case "vegetarian":
			Main.userPreferences = "vegetarian";
			Main.skipDiet = false;
			break;
		case "none":
			Main.userPreferences = "null";
			Main.diet = null;
			Main.skipDiet = true;
			break;
	}
	//Checks for intolerances
	System.out.println("Please state which intolerances you have. Possible values are: dairy, egg, gluten, peanut, sesame, seafood, shellfish, soy, sulfite, tree nut, and wheat.\n(Seperated by a comma ' , ')\nAlternatively enter none to skip.");
	Scanner intolerances = new Scanner(System.in);
	Main.prefferedIntolerances = intolerances.next().toLowerCase(); //Converts input to lowercase
	//Add if diet = null;
	
	if ((Main.prefferedIntolerances.equals("none")) && (Main.excludedIngredients == null)) { //If user enters none for both
		Main.userPreferences += "\nnull";
		Main.skipIntolerances = true;
	
	} else {
		Main.userPreferences += "\n" + Main.prefferedIntolerances;
//Sets new preference on a new line
		Main.skipIntolerances = false;
		//C
	}
	//Checks for excluded ingredients
	Scanner exclusions = new Scanner(System.in);
	System.out.println("Please state which ingredients you want to exclude. (Seperated by a comma ' , ')\nAlternatively enter none to skip.");
	Main.excludedIngredients = exclusions.next().toLowerCase();	//Converts input to lowercase
	
	if (Main.excludedIngredients.equals("none")) { //If user enters none
		Main.userexcludedIngredients = "null";	//Sets excluded ingredients to null if none is entered
		Main.userPreferences += "\nnull";
		Main.skipExcludedIngredients = true;
	} else {
		Main.userPreferences += "\n" + Main.excludedIngredients;
		//Sets new preference on a new line
		Main.skipExcludedIngredients = false;
	} //Adds excluded ingredients to variable
//Preference write to .txt file
	
	try {  
	    FileWriter outFile = new FileWriter("settings.txt");  
	    PrintWriter out = new PrintWriter(outFile);  

	    // Write preferences to file  
	    out.println(Main.userPreferences);  
	    out.close();  //Close file
	} catch (IOException E) {  //E message
		System.out.println(E.getMessage()); 
	}
	
	System.out.println("---Preferences saved---");//Dashed lines increase visibility and emphasise?
	
	MenuPage.menuPage(); //Links to menu page -- Efficiency ++
	
	}
	//Settings Page End

}
