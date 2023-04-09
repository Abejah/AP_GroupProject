package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnectorFactory {
	
	private static Connection dbConn = null;
	
	public static Connection getConnection() {
		if(dbConn ==null) {
			String url = "jdbc:mysql://localhost:3307/dbproject";	//localhost:3307 is used with webserver
			try {
				dbConn =DriverManager.getConnection(url, "root", "usbw"); //using web server, therefore password is "usbw" 
				JOptionPane.showMessageDialog(null, "Connection Established", "JDBC Connection Status", JOptionPane.INFORMATION_MESSAGE);
			} catch (SQLException e) {
				System.err.println("SQL Exception: " + e.getMessage());
			} catch(Exception e) {
				System.err.println("Unexpected Error: " + e.getMessage());
			}
		}
		return dbConn;
	}
}
