package co.grandcircus.shindapp.model;

import java.util.ArrayList;

public class User {

	private String firstName;
	private String lastName;
	private ArrayList <String> allergicTo;
	private String password;
	private String dish;
	private String phoneNumber;
	private int id;
	
	public User(String firstName, String lastName, String phoneNumber, String dish){
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dish = dish;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDish() {
		return dish;
	}
	public void setDish(String dish) {
		this.dish = dish;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
