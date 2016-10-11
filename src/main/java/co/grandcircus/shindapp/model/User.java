package co.grandcircus.shindapp.model;

import java.util.ArrayList;

public class User {

	private String name;
	private ArrayList <String> allergicTo;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getAllergicTo() {
		return allergicTo;
	}
	public void setAllergicTo(ArrayList<String> allergicTo) {
		this.allergicTo = allergicTo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
