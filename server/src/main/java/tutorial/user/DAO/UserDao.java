package tutorial.user.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import tutorial.user.Models.Users;

/**
 * 
 * @author Latrach Anass
 * @email anaslatrach@gmail.com
 * 	
 * 
 */


public class UserDao {

	protected String tableName, sql;
	 protected ArrayList<Users> listeModel;
	public UserDao(String tableName) {
		this.tableName = tableName;
	}
	
	public ArrayList<Users> getAll() throws SQLException, ClassNotFoundException, IOException {
		 if (listeModel == null) {
	            listeModel = new ArrayList<>();
	            sql = "SELECT * FROM "+tableName ;
	            Connection cnt = DAOUtil.getConnection();
	            Statement stm = DAOUtil.getStatement(cnt);
	            ResultSet rs = stm.executeQuery(sql);
	            Users user;
	            while (rs.next()) {
	            	user = new Users(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_fname"));
	                listeModel.add(user);
	            }
	            rs.close();
	            rs = null;
	            stm.close();
	            stm = null;
	            cnt.close();
	            cnt = null;
	        }
	        return listeModel;
	}
	
	public Users getByID(int id) throws SQLException, ClassNotFoundException, IOException {
		getAll();
        for (Users abstractModel : listeModel) {
            if (abstractModel.getId() == id) {
                return abstractModel;
            }
        }
		return null;
	}
	
	
	public int insert(Users m) throws SQLException, ClassNotFoundException, IOException {
		Users user =   m;
		sql = "INSERT INTO " + tableName + "(user_id, user_name, user_fname) VALUES (?, ?, ?)";
		Connection cnt = DAOUtil.getConnection();
		PreparedStatement stm = DAOUtil.getStatement(cnt, sql);
		//stm.setInt(1, Types.NULL);
		stm.setInt(1, Types.NULL);
		stm.setString(2, user.getNom());
		stm.setString(3, user.getPrenom()); 
		int rs = stm.executeUpdate();
		if (rs == 1) {
			if (listeModel != null)
				listeModel.add(user);
		}
		stm.close();
		stm = null;
		cnt.close();
		cnt = null;
		return rs;
	}

	
	public int Update(Users usr) throws SQLException, ClassNotFoundException, IOException {
		
        sql = "UPDATE " + tableName + " SET user_name = ?, user_fname = ? WHERE user_id = ?";
        Connection cnt = DAOUtil.getConnection();
        PreparedStatement stm = DAOUtil.getStatement(cnt, sql);
		stm.setString(1, usr.getNom());
		stm.setString(2, usr.getPrenom()); 
		stm.setInt(3, usr.getId());
        int rs = stm.executeUpdate();
        if(rs == 1) {
            if(listeModel != null) {
                int i = 0;
                for (Users abstractModel : listeModel) {
                    if (abstractModel.getId() == usr.getId()) {
                        listeModel.set(i, usr);
                        break;
                    }
                    i++;
                }
            }
        }
        stm.close();
        stm = null;
        cnt.close();
        cnt = null;
        return rs;
	}
	
	public int Delete(int id) throws SQLException, ClassNotFoundException, IOException {
		
		 sql = "DELETE FROM " + tableName + " WHERE user_id = " + id;
	        Connection cnt = DAOUtil.getConnection();
	        Statement stm = DAOUtil.getStatement(cnt);
	        int rs = stm.executeUpdate(sql);
	        if(rs == 1) {
	            if(listeModel != null) {
	                int i = 0;
	                for (Users userModel : listeModel) {
	                    if (userModel.getId() == id) {
	                        listeModel.remove(i);
	                        break;
	                    }
	                    i++;
	                }
	            }
	        }
	        stm.close();
	        stm = null;
	        cnt.close();
	        cnt = null;
	        return rs;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		UserDao gdao= new UserDao("user");
	
		ArrayList<Users> liste = gdao.getAll();
		Users ds;
        for (Users abstractModel : liste) {
        	ds = (Users) abstractModel;
        
        	
        		
            System.out.println("* ID " + ds.getId() + " -  Nom: " + ds.getNom()); }
        
 System.out.println("##############################");
        
        ds = (Users) gdao.getByID(1);
        System.out.println("* ID " + ds.getId() + " -  Nom: " + ds.getNom()); 

    	Users usr = new Users();
    	usr.setId(1);
    	usr.setNom("Anass");
    	usr.setPrenom("Latrach");
    	
    	//System.out.println(gdao.insert(usr));
    	System.out.println(gdao.Update(usr));
         }

	
}
