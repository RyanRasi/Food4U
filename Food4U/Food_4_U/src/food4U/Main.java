package food4U;

public class Main {
	
MenuPage menuPage = new MenuPage();
	
	public static String RapidAPIKey = "Go to RapidAPI and on Spoonacular sign up for an API key and paste it here";
	
	public static String diet = null;					//These strings have to be static as we would not be able to access it to call the API for the ingredients if they were initialised in the other classes thus they would have been out of scope
	public static String excludedIngredients = null;
	public static String userexcludedIngredients = null;
	public static String prefferedIntolerances = null;
	public static String userIntolerances = null;
	public static String AllRecipe = null;
	public static String userPreferences = null;
	//Needs these skip booleans as if the user hasn't updated any settings then it has to skip the checks for adding in requirements
	public static boolean skipDiet = false;
	public static boolean skipIntolerances = false;
	public static boolean skipExcludedIngredients = false;
	/////////
	public static String tempDiet = null;
	public static String tempIntolerances = null;
	public static String tempExcluded = null;
	/////////These strings add the parameters from the text file to the temp variable in case the user wants to alter them
	public static String recipe1Image = null;
	public static String recipe1MissedIngredientCount = null;
	public static String recipe1ID = null;
	public static String recipe1Title = null;
	public static String recipe1UsedIngredientCount = null;
	public static String recipe1Likes = null;
	/////////Variables for holding recipe data
	public static String recipe2Image = null;
	public static String recipe2MissedIngredientCount = null;
	public static String recipe2ID = null;
	public static String recipe2Title = null;
	public static String recipe2UsedIngredientCount = null;
	public static String recipe2Likes = null;
	/////////
	public static String recipe3Image = null;
	public static String recipe3MissedIngredientCount = null;
	public static String recipe3ID = null;
	public static String recipe3Title = null;
	public static String recipe3UsedIngredientCount = null;
	public static String recipe3Likes = null;
	/////////
	public static String recipeID = null;
	///Variable for parameter of viewing a specific recipe
	
	
	//Bit that says who we are
	public static void main(String[] args) {	
		System.out.println("Food 4 U\n--------\nRyan Rasi\n--------");
		
		MenuPage.menuPage(); //Links to menu page -- Efficiency ++
	}
	

}
