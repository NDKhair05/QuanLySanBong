package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class JDBCUtil {
	public static Connection getConnection() {
		Connection c = null;
		
		try {
			//Dang ky MySQL voi DriverManager
			DriverManager.registerDriver( new Driver());
			String url = "jdbc:mySQL://localhost:3306/footballfieldbooking";
			String username = "root";
			String password = "306205";
			
			c = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void closeConnection(Connection c) {
		try {
			if(c!= null) {
				c.close();
			}
		} catch (Exception e) {
             e.printStackTrace();  
		}
	}
	
}



