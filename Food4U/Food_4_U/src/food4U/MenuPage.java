package food4U;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MenuPage {

	SettingsPage settingsPage = new SettingsPage();
	IngredientsPage ingredientsPage = new IngredientsPage();
	RandomPage randomPage = new RandomPage();
	
	//Menu Page Start
		@SuppressWarnings("resource")
		public static void menuPage ()	{
			System.out.println("Menu Page");
			
			try {
				
			FileReader fr = new FileReader("settings.txt");
			BufferedReader ReadFileBuffer = new BufferedReader(fr);
		//Reader of text file
			Main.tempDiet = ReadFileBuffer.readLine();
			Main.tempIntolerances = ReadFileBuffer.readLine();
			Main.tempExcluded = ReadFileBuffer.readLine();

			ReadFileBuffer.close();//Closes file
					
			} catch (IOException E)
			{
				System.out.println(E.getMessage());//E message
			}
			
			if (Main.tempDiet == null){
				Main.skipDiet = true;
			} else if (!(Main.tempDiet == null)){
				Main.skipDiet = false;
			} else  if (Main.tempIntolerances == null){
				Main.skipExcludedIngredients = true;
			} else if (!(Main.tempIntolerances == null)){
				Main.skipIntolerances = false;
			} else if (Main.tempExcluded == null){
				Main.skipIntolerances = true;
			} else  if (!(Main.tempExcluded == null)){
				Main.skipExcludedIngredients = false;
			}//Checks if user hasn't changed any parameters from the text file
			
			
			String stringmenuchoice = null;
			do {
		System.out.println("\nEnter 1 to access settings page, 2 to choose ingredients, 3 for a random recipe and 4 to exit.");
		Scanner menu = new Scanner(System.in);
		stringmenuchoice = menu.next();
			} while  (!stringmenuchoice.equals("1") && !stringmenuchoice.equals("2") && !stringmenuchoice.equals("3") && !stringmenuchoice.equals("4"));
		//Validation for menu
		int menuchoice;
		try {
		   menuchoice = Integer.parseInt(stringmenuchoice);
		}
		catch (NumberFormatException e)
		{
		   menuchoice = 0;
		}
		
		switch (menuchoice) {
		case 1:
			System.out.println("Settings Page");
			SettingsPage.settingsPage();
			break;
		case 2:
			System.out.println("Ingredients Page");
			IngredientsPage.ingredientsPage();
			break;
		case 3:
			System.out.println("Random Recipe");
			RandomPage.randomPage();
			break;
		case 4:
			System.out.println("-----------------------------------\n| Thank you for using Food 4 U :) |\n-----------------------------------");
			System.exit(0);
			}
		}
	 //Menu Page End


}
