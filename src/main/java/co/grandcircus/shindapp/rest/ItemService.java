package co.grandcircus.shindapp.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import co.grandcircus.shindapp.model.Item;
import co.grandcircus.shindapp.rest.HttpHelper;

public class ItemService {
	
	
	
	private final static String API_KEY = "yqtv5hue36h96ww6qdtv3dvc";
	private static String session = "";
	
	public static String getKey(){
		return API_KEY;
	}
	
	public void getSession() {
		session = getSessionID(API_KEY);
	}
	
	public String getSessionID(String key) {
		String url = "http://api.foodessentials.com/createsession?uid=ert&devid=ert&appid=ert&f=json&v=2.00&api_key=" + key;
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			String session = root.getAsJsonObject().get("session_id").getAsString();
						
			return session;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
	public String getItemInfoByName(String session, String itemName, String key) {
		String url = "http://api.foodessentials.com/searchprods?q=" + itemName + "&sid=" + session + "&n=5&s=1&f=json&v=2.00&api_key=" + key;
		// Use HTTP GET with the above URL
		try (BufferedReader reader = HttpHelper.doGet(url)) { // try with resources will auto close the reader
			if (reader == null) {
				throw new RuntimeException("Not found: " + url);
			}
			
			JsonElement root = new JsonParser().parse(reader);
			ArrayList<JsonElement> results = null;
			results.add(root.getAsJsonObject().get("productsArray").getAsJsonArray());
						
			return session;
		} catch (IOException ex) {
			throw new RuntimeException("Error reading from URL: " + url, ex);
		}
	}
	

}
