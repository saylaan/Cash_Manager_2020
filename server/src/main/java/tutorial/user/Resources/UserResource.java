package tutorial.user.Resources;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces; 
 
import tutorial.user.Models.Users;
import tutorial.user.Services.UserService;

@Path("users") 
public class UserResource {

 	UserService userservice = new UserService(); 
 	
 	
		@GET
		@Produces("application/json")
		public ArrayList<Users> getUsers() throws ClassNotFoundException, SQLException, IOException   { 
			ArrayList<Users> userList = new ArrayList<Users>();
			userList = userservice.getAllUsers();
			return userList;
					
		}
		
		@GET
		@Path("/{userId}")
		@Produces("application/json")
		public Users getUser(@PathParam("userId") int userId) throws ClassNotFoundException, SQLException, IOException {
			
			return userservice.getUserByID(userId);
		}
		
		
		@POST 
		@Produces("application/x-www-form-urlencoded")
		@Consumes("application/x-www-form-urlencoded")
		public void addUser(@FormParam ("user_name") String user_name,@FormParam("user_fname") String user_fname) throws ClassNotFoundException, SQLException, IOException {
			Users Usr = new Users();
			Usr.setId(Types.NULL);
			Usr.setNom(user_name);
			Usr.setPrenom(user_fname);
			userservice.addUser(Usr);
			
		}
		
		@PUT
		@Path("/{userId}")
		@Produces("application/x-www-form-urlencoded")
		@Consumes("application/x-www-form-urlencoded")
		public void updateUser(@PathParam("userId") int userId, @FormParam ("user_name") String user_name,@FormParam("user_fname") String user_fname) throws ClassNotFoundException, SQLException, IOException {
			Users Usr = new Users();
			Usr.setId(userId);
			Usr.setNom(user_name);
			Usr.setPrenom(user_fname);
			userservice.updateUser(Usr);
		}
		
		@DELETE
		@Path("/{userId}")
		public int deleteUser(@PathParam("userId") int userId) throws ClassNotFoundException, SQLException, IOException {
			return userservice.deleteUser(userId);
			
		}
}
