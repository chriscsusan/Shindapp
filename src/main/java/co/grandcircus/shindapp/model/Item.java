package co.grandcircus.shindapp.model;

import java.util.ArrayList;
import java.util.List;

public class Item {

	private String foodName;
	private List <String> ingredients;
	private String whoIsBringingFood;
	private ArrayList <String> allergens;
	private String upc;
	private Integer participantID;
	
	
	public Integer getParticipantID() {
		return participantID;
	}
	public void setParticipantID(Integer participantID) {
		this.participantID = participantID;
	}
	public List<String> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<String> ingredients) {
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
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	
	
}
	
	


