package food4U;

import java.util.Scanner;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class test {

	@SuppressWarnings("resource")
	public static void main(String[] args) {	
		Scanner reader = new Scanner(System.in);
		System.out.println("What ingredients do you want to use Boss?");
		String ingredients = reader.next();
		ingredients = ingredients.replace(",", "%2C");
		findByIngredients(ingredients);

		
	}
	
	public static void findByIngredients(String ingredients){
		try {
			HttpResponse<JsonNode> response = Unirest.get("https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/findByIngredients?fillIngredients=false&ingredients="+ingredients+"&limitLicense=false&number=3&ranking=1")
					.header("X-Mashape-Key", "1823541250mshe6afb9f6344efe2p157f1cjsn660007d9c22e")
					.header("Accept", "application/json")
					.asJson();
			
			JSONObject json = new JSONObject(response.getBody());
			System.out.print(json.toString(4));
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


