package co.grandcircus.shindapp.dao;

import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.User;
import java.io.FileNotFoundException;

public interface ItemDao {

	public Item getAllIngredients(User user);

	void deleteIngredient(int ingredientIndex, Item item, User user) throws FileNotFoundException;

	void addIngredient(Item item, User user, int ingredientIndex) throws FileNotFoundException;
	
}
