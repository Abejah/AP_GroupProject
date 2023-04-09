package client;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import factories.DBConnectorFactory;
import view.Administration;
import view.LoginPage;
import view.Student;

public class Client extends Administration implements Serializable{

	private static final long serialVersionUID = 1L;
	private static Connection dbConn = null;
	private static Statement stmt;
	//private static ResultSet result;
	//private static Administration ad = new Administration();
	
	//initializing logger to class
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(LoginPage.class); 
	
	public Client() {
		dbConn = DBConnectorFactory.getConnection();
	}

	//creates a an administration and adds them to the admin table in the database
	public static void createRecord(Administration ad) {
		   String firstName = ad.getFirstName();
			String lastName = ad.getLastName();
			String userName = ad.getUserName();
			String password = ad.getPassowrd();
			String emailId = ad.getEmail();
			String mobileNumber = ad.getMobileNumber();
		
		String insertSQL = "INSERT INTO admin values('" + firstName + "','" + lastName + "','" + userName + "','" +
                password + "','" + emailId + "','" + mobileNumber + "')";
		try {

            stmt = dbConn.createStatement();
            int x = stmt.executeUpdate(insertSQL);
            if (x == 1) {
            	JOptionPane.showMessageDialog(null, "Account created");
            } else {
            	JOptionPane.showMessageDialog(null, "Already exist");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } 
	}

	//creates a student and adds them to the student table in the database
	public static void createStudentRecord(Student st) {
		    String firstName =st.getFirstName();
			String lastName = st.getLastName();
			String userName = st.getUserName();
			String password = st.getPassowrd();
			String emailId = st.getEmail();
			String mobileNumber = st.getMobileNumber();
		
		String insertSQL = "INSERT INTO student values('" + firstName + "','" + lastName + "','" + userName + "','" +
             password + "','" + emailId + "','" + mobileNumber + "')";
		try {

         stmt = dbConn.createStatement();
         int x = stmt.executeUpdate(insertSQL);
         if (x == 1) {
         	JOptionPane.showMessageDialog(null, "Account created");
         } else {
         	JOptionPane.showMessageDialog(null, "Already exist");
         }
     } catch (Exception exception) {
         exception.printStackTrace();
     } 
	
	}

	
	//authentication of a admin user
	/*public void authenticateAdmin(Administration ad) {
		String username = ad.getUserName();
		String password = ad.getPassowrd();
		String sql = "Select * from admin where userName=? and password= ?";
		
		PreparedStatement pst = dbConn.prepareStatement(sql);
		
		//accepting from user
        pst.setString(1, ad.getUserName());
        pst.setString(2, ad.getPassowrd());
		
        
        try {
        	stmt = dbConn.createStatement();
        	
        	ResultSet rs = pst.executeQuery();
        	if(rs.next()) {
            	JOptionPane.showMessageDialog(null, "You are logged in as administrator");
            	StudentDashboard menu = new StudentDashboard();
            	menu.setVisible(true);
            	
            	logger.info("Administrator Logged in successfully");
            	
            }else {

            	JOptionPane.showMessageDialog(null, "Invalid log in. Please try again.");
            	username.  //setText("");
            	password.wait();  //setText("");
            	
            	logger.info("Unsuccessful login attempt by administrator");
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        } 
	}*/
	
	
	public static void main(String[] args) {
	
		new Client();

	}

}
