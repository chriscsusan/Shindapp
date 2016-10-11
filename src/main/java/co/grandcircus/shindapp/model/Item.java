package co.grandcircus.shindapp.model;

import java.util.ArrayList;

public class Item {

	private String foodName;
	private ArrayList <String> ingredients;
	private String whoIsBringingFood;
	private ArrayList <String> allergens;
	
	
	public ArrayList<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}
	public ArrayList<String> getAllergens() {
		return allergens;
	}
	public void setAllergens(ArrayList<String> allergens) {
		this.allergens = allergens;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	public String getWhoIsBringingFood() {
		return whoIsBringingFood;
	}
	public void setWhoIsBringingFood(String whoIsBringingFood) {
		this.whoIsBringingFood = whoIsBringingFood;
	}
	
	
}
	
	


