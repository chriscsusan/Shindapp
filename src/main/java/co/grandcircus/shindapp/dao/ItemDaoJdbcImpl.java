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

import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.User;

@Repository
@Primary
public class ItemDaoJdbcImpl implements ItemDao {

	// private static final Logger logger =
	// LoggerFactory.getLogger(UserDao.class);

	@Autowired
	JdbcConnectionFactory connectionFactory;

	@Override
	public Item getAllIngredients(User user) {
		String sql = "SELECT * FROM Ingredients WHERE ParticipantID = ?";
		List<String> items = new ArrayList<String>();
		List<String> tempItems = new ArrayList<String>();
		List<User> users = new ArrayList<User>();
		Integer id = user.getId();
		Item item = new Item();
		
		try (Connection connection = connectionFactory.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();

			
			while (result.next())  {
				items.add(result.getString("Ingredient"));

			}
			
			item.setParticipantID(id);
			for(String s:items){
				tempItems.add(s);
			}
			item.setIngredients(tempItems);
			return item;
		}catch(

	SQLException ex)
	{
		throw new RuntimeException(ex);
	}
	}

	@Override
	public void addIngredient(User user, Item item) throws FileNotFoundException {
		String sql = "INSERT INTO Ingredients SET participantID = ?, Ingredient = ?";
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

	@Override
	public void deleteIngredient(int ingredientIndex, Item item, User user) throws FileNotFoundException {
		String sql = "DELETE FROM Ingredients WHERE ParticipantID = ?, Ingredient = ?";
		try (Connection conn = connectionFactory.getConnection();
				PreparedStatement statement = conn.prepareStatement(sql)) {

			statement.setInt(1, user.getId());

			statement.setString(2, item.getIngredients().get(ingredientIndex));

			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated != 1) {
				throw new FileNotFoundException("No such user");
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
	}



}
