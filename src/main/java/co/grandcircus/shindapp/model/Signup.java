package co.grandcircus.shindapp.model;

import java.util.List;

public class Signup {

	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String dishName;
	private int id;
	private Item item;
	private int potluckId;
	private List<Allergen> allergens;
	private int pin;
	private String potluckName;
	
	public String getPotluckName() {
		return potluckName;
	}

	public void setPotluckName(String potluckName) {
		this.potluckName = potluckName;
	}

	public List<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(List<Allergen> allergens) {
		this.allergens = allergens;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPotluckId() {
		return potluckId;
	}

	public void setPotluckId(int potluckId) {
		this.potluckId = potluckId;
	}

	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Signup(String firstName, String lastName, String phoneNumber, String dishName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.dishName = dishName;
	}

	public Signup() {
		// TODO Auto-generated constructor stub
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getDishName() {
		return dishName;
	}

	public void setId(int id) {
		this.id = id;

		// TODO Auto-generated method stub

	}

}
