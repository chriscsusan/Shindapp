package co.grandcircus.shindapp.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import co.grandcircus.shindapp.model.Allergen;
import co.grandcircus.shindapp.model.Ingredient;
import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.Signup;
import co.grandcircus.shindapp.model.User;

@Repository
@Primary

// Methods in this class are used to interact with the dishes and Ingredients tables in the database.

public class ItemDaoJdbcImpl implements ItemDao {

	@Autowired
	JdbcConnectionFactory connectionFactory;

	// Pulls all ingredients for a specific uer's dish based on ID.
	@Override
	public Item getAllIngredients(Signup user) {
		String sql = "SELECT * FROM Ingredients WHERE ParticipantID = ?";
		List<Ingredient> items = new ArrayList<>();
		List<Ingredient> tempItems = new ArrayList<>();
		List<User> users = new ArrayList<User>();
		Integer id = user.getId();
		Item item = new Item();

		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				Ingredient temp = new Ingredient();
				temp.setName(result.getString("Ingredient"));
				temp.setUpc(result.getString("upc"));
				items.add(temp);

			}

			item.setParticipantID(id);
			item.setIngredients(items);
			return item;
		} catch (

		SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	//Adds ingredient to Ingredients table with participant ID and upc for future reference.
	@Override
	public void addIngredient(User user, Item item) throws FileNotFoundException {
		String sql = "INSERT INTO Ingredients (participantID, Ingredient, upc) VALUES (?, ?, ?)";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, user.getId());
			statement.setString(2, item.getFoodName());
			statement.setString(3, item.getUpc());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated != 1) {
				throw new FileNotFoundException("No such user");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	//Adds allergens from an ingredient to allergens table.  A 1 means the item has that specific allergent present.
	@Override
	public void addAllergens(Item item, ArrayList<Allergen> allergens) throws FileNotFoundException {
		String sql = "INSERT INTO allergens (participantID, cereals, shellfish, egg, fish, milk, peanuts, sulfites, treenuts, soybean, sesameseeds, gluten, lactose, corn, wheat, coconut, name) "
				+ "		VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, item.getId());
			int counter = 2;
			System.out.println(allergens);
			for (Allergen a : allergens) {
				if (a.getName().equalsIgnoreCase("none")) {
					statement.setInt(counter, 0);
				} else {
					statement.setInt(counter, 1);
				}
				counter++;
			}
			statement.setString(counter, item.getFoodName());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated != 1) {
				throw new FileNotFoundException("No such user");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	//Deletes an allergen from the allergens table, using the participant ID and name.  
	//This way it doesn't delete an allergen from multiple dishes, in case the participant
	//is attending multiple potlucks.
	@Override
	public void deleteAllergen(Item item) throws FileNotFoundException {
		String sql = "DELETE FROM allergens WHERE ParticipantID = ? and name = ?";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, item.getId());
			statement.setString(2, item.getFoodName());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated != 1) {
				throw new FileNotFoundException("No such user");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	//Get an array of ints that represents each allergen.  A 1 indicates it is present int he dish.
	@Override
	public int[] getAllergens(Signup signup) throws FileNotFoundException {
		String sql = "SELECT * FROM allergens WHERE ParticipantID = ?";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {
			statement.setInt(1, signup.getId());

			ResultSet result = statement.executeQuery();
			int[] allergensPresent = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			ArrayList<Integer> allergensTemp = new ArrayList<>();

			while (result.next()) {
				allergensTemp.add(result.getInt("cereals"));
				allergensTemp.add(result.getInt("shellfish"));
				allergensTemp.add(result.getInt("egg"));
				allergensTemp.add(result.getInt("fish"));
				allergensTemp.add(result.getInt("milk"));
				allergensTemp.add(result.getInt("peanuts"));
				allergensTemp.add(result.getInt("sulfites"));
				allergensTemp.add(result.getInt("treenuts"));
				allergensTemp.add(result.getInt("soybean"));
				allergensTemp.add(result.getInt("sesameseeds"));
				allergensTemp.add(result.getInt("gluten"));
				allergensTemp.add(result.getInt("lactose"));
				allergensTemp.add(result.getInt("corn"));
				allergensTemp.add(result.getInt("wheat"));
				allergensTemp.add(result.getInt("coconut"));
				for (int i = 0; i < 15; i++) {
					if (allergensTemp.get(i) == 1) {
						allergensPresent[i] = 1;
					}
				}

			}
			return allergensPresent;
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	//Deletes an ingredient from the Ingredients table, using the participant ID and name.  
	//This way it doesn't delete an ingredient from multiple dishes, in case the participant
	//is attending multiple potlucks.
	@Override
	public void deleteIngredient(Item item, User user) throws FileNotFoundException {
		String sql = "DELETE FROM Ingredients WHERE ParticipantID = ? and Ingredient = ?";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {

			statement.setInt(1, user.getId());

			statement.setString(2, item.getFoodName());

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated != 1) {
				throw new FileNotFoundException("No such user");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}

}
