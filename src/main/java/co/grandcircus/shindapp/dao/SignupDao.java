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
	void addSignup(Signup signup);
	Signup getSignup(int id) throws NameNotFoundException;
	void deleteSignup(int id) throws FileNotFoundException;
	void updateSignup(Signup signup) throws NamingException;
	int getSignupPin(int id) throws NameNotFoundException;
	int getSignupId(Signup signup) throws NameNotFoundException;

}	