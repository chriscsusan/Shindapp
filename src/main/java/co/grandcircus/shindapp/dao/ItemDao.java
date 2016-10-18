package co.grandcircus.shindapp.dao;

import co.grandcircus.shindapp.model.Allergen;
import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.Signup;
import co.grandcircus.shindapp.model.User;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface ItemDao {

	public void addIngredient(User user, Item item) throws FileNotFoundException;

	public void deleteIngredient(Item item, User user) throws FileNotFoundException;

	public Item getAllIngredients(Signup user);

	public void addAllergens(Item item, ArrayList<Allergen> allergens) throws FileNotFoundException;

	public void deleteAllergen(Item item) throws FileNotFoundException;

	public int[] getAllergens(Signup signup) throws FileNotFoundException;
	
}
