package co.grandcircus.shindapp.dao;






import java.io.FileNotFoundException;
import java.util.List;
import javax.naming.NameAlreadyBoundException;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;

import co.grandcircus.shindapp.model.Signup;
import co.grandcircus.shindapp.model.User;



public interface SignupDao {
	
	

	public List<Signup> getAllSignup();
	//public void updateSignup();
	//public void deleteSignup();
	void addSignup(Signup signup);
	Signup getSignup(int id) throws NameNotFoundException;
	void deleteSignup(int id) throws FileNotFoundException;
	void updateSignup(Signup signup) throws NamingException;
	
	
	
	
	//void addSignup(User user, Signup signup) throws FileNotFoundException;

	//void deleteSignup(User user, Signup signup) throws FileNotFoundException;
	
	//void updateSignup(int id, Signup signup) throws NameAlreadyBoundException;
	
	//Signup getSignup(int id) throws NameAlreadyBoundException;

	//List<Signup> getAllSignup();

	//String addSignup();

	//Signup getSignup();

}
	
	
//public List<Signup> getAllSignup();
//public Student getSignup(String firstName);
//public void updateSignup(Signup signup);
//public void deleteSignup(Signup signup);
//public void addSignup(Signup signup);




	//List<Signup> getAllSignupSortedBy(String sortOrder) throws IllegalArgumentException;
	//String addSignup(User user);
	//void deleteSignup(String firstName) throws NameNotFoundException;
	//Signup getSignup(String firstName) throws NameNotFoundException;
	//Signup getSignupByFirstnameAndLastName(String firstName, String lastName, String phoneNumber, String dishName);
	
	