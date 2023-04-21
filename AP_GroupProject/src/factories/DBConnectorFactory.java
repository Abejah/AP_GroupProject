package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnectorFactory {
	private static Connection dbConn = null;
	
	public static Connection getDatabaseConnection() {
		if(dbConn==null) {
			String url = "jdbc:mysql://localhost:3306/dblab";
			try {
				dbConn=DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "Connection Established", "JBDC Connection Status",
						JOptionPane.INFORMATION_MESSAGE);;
			
			}catch(SQLException e) {
				System.err.println("SQL Exception: " +e.getMessage());
			}catch (Exception e) {
				System.err.println("Unexpected Exception: " +e.getMessage());
			}
		}
		
		return dbConn;
	}
}
