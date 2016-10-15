package co.grandcircus.shindapp.dao;





import java.util.List;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import co.grandcircus.shindapp.model.Signup;



public interface SignupDao {
	
	
	List<Signup> getAllSignup();
	
	
	
	List<Signup> getAllSignupSortedBy(String sortOrder) throws IllegalArgumentException;

	
	//Signup getSignup(String firstName) throws NameNotFoundException;
	
	
	//Signup getSignupByFirstnameAndLastName(String firstName, String lastName, String phoneNumber, String dishName);
	
	
	String addSignup(Signup signup);
	
	
	void updateSignup(String firstName, Signup signup) throws NamingException;
	
	
	void deleteSignup(String firstName) throws NameNotFoundException;
	

}
