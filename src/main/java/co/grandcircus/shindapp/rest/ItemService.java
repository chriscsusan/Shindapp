package co.grandcircus.shindapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.*;
import org.apache.commons.lang3.text.WordUtils;

import co.grandcircus.shindapp.model.Allergen;
import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.model.ItemSession;
import co.grandcircus.shindapp.rest.HttpHelper;

@Service
public class ItemService {
	
	private final static String APP_ID = "potluck";
	
	@Value("${api_key}")
	private String apiKey;
	
	@Value("${test_session}")
	private String testSession;
	
	public ItemService(){
	}
	
	public String getKey(){
		return apiKey;
	}
	public String getTestSession(){
		return testSession;
	}
	
	public String getSession() {
		return testSession;
	}
	
	//Get first 50 results of an item search from API 
	public ArrayList<Item> getItemInfoByName(String session, String itemName, String key) throws UnsupportedEncodingException {
		ArrayList<Item> results = new ArrayList<>();
		String encodedItemName = URLEncoder.encode(itemName, "UTF-8");
		String url = "http://api.foodessentials.com/searchprods?q=" + encodedItemName + "&sid=" + testSession + "&n=50&s=1&f=json&v=2.00&api_key=" + apiKey;
		
		
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			JsonArray temp = root.getAsJsonObject().get("productsArray").getAsJsonArray();
			Integer i = 1;
			for (JsonElement e: temp){
				Item tempItem = new Item();
				tempItem.setUpc(e.getAsJsonObject().get("upc").getAsString());
				tempItem.setFoodName(WordUtils.capitalizeFully((e.getAsJsonObject().get("product_name").getAsString())));
				tempItem.setId(i);
				results.add(tempItem);
				i++;
			}
						
			return results;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
	
	public ArrayList<Item> getItemInfoByNameTenResults(String session, String itemName, String key) throws UnsupportedEncodingException {
		ArrayList<Item> results = new ArrayList<>();
		String encodedItemName = URLEncoder.encode(itemName, "UTF-8");
		String url = "http://api.foodessentials.com/searchprods?q=" + encodedItemName + "&sid=" + testSession + "&n=10&s=1&f=json&v=2.00&api_key=" + apiKey;
		
		
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			JsonArray temp = root.getAsJsonObject().get("productsArray").getAsJsonArray();
			Integer i = 1;
			for (JsonElement e: temp){
				Item tempItem = new Item();
				tempItem.setUpc(e.getAsJsonObject().get("upc").getAsString());
				tempItem.setFoodName(WordUtils.capitalizeFully((e.getAsJsonObject().get("product_name").getAsString())));
				tempItem.setId(i);
				results.add(tempItem);
				i++;
			}
						
			return results;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
	
	public ArrayList<Item> getItemInfoByNameNextTenResults(String session, String itemName, String key, int searchStart) throws UnsupportedEncodingException {
		ArrayList<Item> results = new ArrayList<>();
		String encodedItemName = URLEncoder.encode(itemName, "UTF-8");
		String url = "http://api.foodessentials.com/searchprods?q=" + encodedItemName + "&sid=" + testSession + "&n=10&s=" + searchStart + "&f=json&v=2.00&api_key=" + apiKey;
		
		
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			JsonArray temp = root.getAsJsonObject().get("productsArray").getAsJsonArray();
			Integer i = 1;
			for (JsonElement e: temp){
				Item tempItem = new Item();
				tempItem.setUpc(e.getAsJsonObject().get("upc").getAsString());
				tempItem.setFoodName(WordUtils.capitalizeFully((e.getAsJsonObject().get("product_name").getAsString())));
				tempItem.setId(i);
				results.add(tempItem);
				i++;
			}
						
			return results;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
	
	public ArrayList<Allergen> getItemInfoByUPC(String upc) {
		ArrayList<Allergen> allergens = new ArrayList<>();
		String url = "http://api.foodessentials.com/label?u=" + upc + "&sid=" + testSession + "&appid=" + APP_ID + "&f=json&api_key=" + apiKey;
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			JsonArray allergensArray = root.getAsJsonObject().get("allergens").getAsJsonArray();
			for (JsonElement e: allergensArray){
				if(e.getAsJsonObject().get("allergen_value").getAsInt() > 0){
					allergens.add(new Allergen(e.getAsJsonObject().get("allergen_name").getAsString(), e.getAsJsonObject().get("allergen_value").getAsString()));
				}else{
					allergens.add(new Allergen("none",""));
				}
			}
			
						
			return allergens;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
		

}
