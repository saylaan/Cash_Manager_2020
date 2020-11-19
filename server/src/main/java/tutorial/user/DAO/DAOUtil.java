package tutorial.user.DAO;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 * 
 * @author Latrach Anass
 * @email anaslatrach@gmail.com
 * 	
 * 
 */

public class DAOUtil {

	private static String DBNAME;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	private static String DRIVER;

	private static final String CHEMIN_FICHIER_PARAMS = "C:\\Users\\asus\\eclipse-workspace\\user\\src\\main\\java\\tutorial\\user\\util\\Params.properties";
	private static Properties jdbc_params = null;

	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
		FileReader reader = new FileReader(CHEMIN_FICHIER_PARAMS);
		if (jdbc_params == null) {

			jdbc_params = new Properties();
			jdbc_params.load(reader);

			DBNAME = jdbc_params.getProperty("DBNAME");
			URL = jdbc_params.getProperty("URL");
			USERNAME = jdbc_params.getProperty("USERNAME");
			PASSWORD = jdbc_params.getProperty("PASSWORD");
			DRIVER = jdbc_params.getProperty("DRIVER");

		}
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL + DBNAME +"?useTimezone=true&serverTimezone=UTC", USERNAME, PASSWORD);
	}

	public static Statement getStatement(Connection c) throws SQLException {
		return c.createStatement();
	}

	public static PreparedStatement getStatement(Connection c, String sql) throws SQLException {
		return c.prepareStatement(sql);

	}

}
