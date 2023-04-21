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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import domain.Student;

public class StudentServer implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	//server
	private ServerSocket serverSocket;
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	
	//database
	private static Connection connection;
	private static Statement statement;
	private ResultSet resultSet = null;
	
	
	public StudentServer()
	{
		
		this.createConncection();
		this.waitForRequests();
		
	}
	
	private void createConncection() {
		try {
			serverSocket = new ServerSocket(8888);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void configureStreams() {
		try {
			//Instantiate the output stream, using the getOutputStream method of the Socket object as argument to the constructor
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			//Instantiate the input stream, using the getInputStream method of the Socket object as argument to the constructor
			inputStream = new ObjectInputStream(socket.getInputStream());
		}catch(IOException ex) {
			
		}
	}
	
	private static Connection getDatabaseConnection() {
		if (connection == null) {
			try {
				String url = "jdbc:mysql://localhost:3306/studentsdb";
				connection = DriverManager.getConnection(url, "root", "password");
				JOptionPane.showMessageDialog(null, "DB Connection Established", "CONNECTION STATUS", JOptionPane.INFORMATION_MESSAGE);

			}catch(SQLException ex) {
				JOptionPane.showMessageDialog(null, "Could not connect to database\n", "Connection Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		return connection;
	}
	
	private void closeConnection() {
		try {
			outputStream.close();
			inputStream.close();
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void addStudentToFile(Student student) {
		String sql ="INSERT INTO studentsdb.students(idNumber, firstName, lastName, email, contactNumber, issueType, issue, issueDetails,responses)" 
		        + "VALUES('"+student.getIdNumber()+"', '"+student.getFirstName()+"','"+student.getLastName()+"','"+student.getEmail()+"','"+student.getContactNumber() +"','"
				+student.getIssueType()+"','"+student.getIssue()+"','"+student.getIssueDetails()+"','"+student.getResponses()+"');";
		try {
			statement= connection.createStatement();
			
			if(statement.executeUpdate(sql)==1) {
				outputStream.writeObject(true);
			}else {
				outputStream.writeObject(false);
			}
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	 // Method declaration with two input parameters: issue and stuId
 	private Student findStudentByID(String issue,String stuId) {
     // Create a new Student object
     Student stuObj = new Student();
     // Create a query string to select data from the students table in the studentsdb database
     String query = "SELECT refNumber, idNumber,firstName,lastName,email,contactNumber,issueType,issue,issueDetails,responses FROM studentsdb.students WHERE issue ='"+issue+"' AND idNumber ='"+stuId+"' ;";

     try {
         // Create a Statement object to execute the query
         statement= connection.createStatement();
         // Execute the query and store the result in a ResultSet object
         resultSet = statement.executeQuery(query);

         // Check if the result set has any rows
         if(resultSet.next()) {
             // Set the properties of the Student object with data from the first row of the result set
             stuObj.setRefNumber(resultSet.getString(1));
             stuObj.setIdNumber(resultSet.getString(2));
             stuObj.setFirstName(resultSet.getString(3));
             stuObj.setLastName(resultSet.getString(4));
             stuObj.setEmail(resultSet.getString(5));
             stuObj.setContactNumber(resultSet.getInt(6));
             stuObj.setIssueType(resultSet.getString(7));
             stuObj.setIssue(resultSet.getString(8));
             stuObj.setIssueDetails(resultSet.getString(9));
             stuObj.setResponses(resultSet.getString(10));
         }

     }catch(SQLException e) {
         // Print the stack trace if an SQLException is thrown
         e.printStackTrace();
     }
     // Return the Student object
     return stuObj;
 }
		
	

	private void waitForRequests() {
		Object action = "";
		getDatabaseConnection();
		
		Student stuObj = null;
		
		try {
			while(true) {
				socket = serverSocket.accept();
				this.configureStreams();
				try {
					//action = (String) inputStream.readObject();	
					action =inputStream.readObject();		
					
					if (action.equals("Add Student")) {
						stuObj = (Student) inputStream.readObject();
						addStudentToFile(stuObj);
						outputStream.writeObject(true);
					}else if (action.equals("Find Student")) {
						String issue=(String) inputStream.readObject();
                        String stuId = (String) inputStream.readObject();
                        stuObj = findStudentByID(issue,stuId);
                        outputStream.writeObject(stuObj);
					}
					
				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
					
				}catch(ClassCastException ex) {
					ex.printStackTrace();
					
				}
				this.closeConnection();
			}
			
		}catch(EOFException ex) {
			System.out.println("Client has terminated connections with the server");
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new StudentServer();	
	}
}

