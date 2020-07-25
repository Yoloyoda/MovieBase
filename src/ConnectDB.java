import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

	public static Connection connect()
	{
		String url = "jdbc:mysql://localhost/movies?characterEncoding=UTF-8&serverTimezone=JST";
		String user = "root";
		String password = Your Password;
	try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("Loaded driver");
	        Connection con = DriverManager.getConnection(url, user, password);
	        		
	        System.out.println("Connected to MySQL");
	        return con;
	 } 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
}
