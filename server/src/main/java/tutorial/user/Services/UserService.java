package tutorial.user.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import tutorial.user.DAO.UserDao;
import tutorial.user.Models.Users;
 
public class UserService { 
	
	UserDao ddao = new UserDao("user");
	public ArrayList<Users> getAllUsers() throws ClassNotFoundException, SQLException, IOException{
		ArrayList<Users> listproduit = ddao.getAll();
		return listproduit;
		
	}
	
	public Users getUserByID(int id) throws ClassNotFoundException, SQLException, IOException {
		Users usr = new Users();
		usr = ddao.getByID(id);
		return usr; 
	}
	
	public void addUser(Users m) throws ClassNotFoundException, SQLException, IOException {
		ddao.insert(m);
	}
	
	public void updateUser(Users usr) throws ClassNotFoundException, SQLException, IOException {
		ddao.Update(usr);
	}
	
	public int deleteUser(int id) throws ClassNotFoundException, SQLException, IOException { 
		 return ddao.Delete(id);
	}
}
