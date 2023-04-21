package server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import view.Administration;
import view.SSAdvisor;
import view.Student;


public class Server implements Serializable{

	private static final long serialVersionUID = 1L;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private static Connection dbConn = null;
	private static Statement stmt;
	private static ObjectOutputStream objO;
	private static ObjectInputStream objI;

	public Server() {
		
		this.createConnection();
		this.request();
	}
	
	public void createConnection() {
		try {
			//creating a connection on server 8888
			serverSocket = new ServerSocket(8888);		
			System.out.println("Server started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void configureStreams() {
		try {
			objO = new ObjectOutputStream(connectionSocket.getOutputStream());
			objI = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		if(dbConn == null) {
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
	
	public void closeConnection() {
		try {
			objO.close();
			objI.close();
			
		}catch (Exception e) {
			System.err.println("Error: "+e.getMessage());
			//e.printStackTrace();
		}
	}
	
	//creates a an Supervisor and adds them to the admin table in the database
	public static void createAdministrationRecord(Administration ad) {
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
            	objO.writeObject(true);
            	JOptionPane.showMessageDialog(null, "Account created");
            } else {
            	objO.writeObject(false);
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
        	 objO.writeObject(true);
         	JOptionPane.showMessageDialog(null, "Account created");
         } else {
        	 objO.writeObject(false);
         	JOptionPane.showMessageDialog(null, "Already exist");
         }
     } catch (Exception exception) {
         exception.printStackTrace();
     } 
	
	}
	
	//creates a an SSAdvisor and adds them to the admin table in the database
		public static void createSSAdvisorRecord(SSAdvisor adv) {
			    String firstName = adv.getFirstName();
				String lastName = adv.getLastName();
				String userName = adv.getUserName();
				String password = adv.getPassowrd();
				String emailId = adv.getEmail();
				String mobileNumber = adv.getMobileNumber();
			
			String insertSQL = "INSERT INTO advisor values('" + firstName + "','" + lastName + "','" + userName + "','" +
	                password + "','" + emailId + "','" + mobileNumber + "')";
			try {

	            stmt = dbConn.createStatement();
	            int x = stmt.executeUpdate(insertSQL);
	            if (x == 1) {
	            	 objO.writeObject(true);
	            	JOptionPane.showMessageDialog(null, "Account created");
	            } else {
	            	objO.writeObject(false);
	            	JOptionPane.showMessageDialog(null, "Already exist");
	            }
	        } catch (Exception exception) {
	            exception.printStackTrace();
	        } 
		}
	
		public void request() {
		    Object action = "";
		    getConnection();

		    Student stuObj = null;
		    Administration adObj = null;
		    SSAdvisor advObj = null;

		    try {
		        while (true) {
		            connectionSocket = serverSocket.accept();
		            this.configureStreams();
		            try {
		                action = objI.readObject();

		                if (action.equals("Add Student")) {
		                    stuObj = (Student) objI.readObject();
		                    createStudentRecord(stuObj);
		                  //addStudentToFile(stuObj);
		                    objO.writeObject(true);
		                } else if (action.equals("Add Supervisor")) {
		                    adObj = (Administration) objI.readObject();
		                    createAdministrationRecord(adObj);
		                  //addAministratorToFile(adObj);
		                    objO.writeObject(true);
		                } else if (action.equals("Add Advisor")) {
		                    advObj = (SSAdvisor) objI.readObject();
		                  //addAdvisorToFile(advObj);
		                    createSSAdvisorRecord(advObj);
		                    objO.writeObject(true);
		                }

		            } catch (EOFException e) {
		                System.out.println("Client closed the connection.");
		                closeConnection();
		            } catch (Exception e) {
		                e.printStackTrace();
		                closeConnection();
		            }
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}


		
	//main method
		public static void main(String[] args) {
			new Server();	//starts the server
	
		}

}
