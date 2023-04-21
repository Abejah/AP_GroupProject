package client;

import javax.swing.*;

import domain.Student;
import factories.DbConn;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;

public class DBClient extends Student implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Socket socket;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	String action;
  

    public DBClient() {
        this.createConnection();
        this.configureStreams();
       
      
    }

    private void createConnection() {

        try {
            socket = new Socket("127.0.0.1", 8888);
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void configureStreams() {
        try {
            //Creates an input stream to receive data from the server
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //Creates an output stream to send data to the server
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendAction(String action) {
        this.action = action;
        try {
            outputStream.writeObject(action);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendStudent(Student stuObj) {
        try {
            outputStream.writeObject(stuObj);
            outputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sendStudentInfo(String issue,String stuId) {
        try {
            outputStream.writeObject(issue);
            outputStream.writeObject(stuId);
            outputStream.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public Student receiveResponse() {
    		Student student = new Student();
    		Object[][] data = null;
        try {
            if(action.equalsIgnoreCase("Add Student")) {
                Boolean flag = (Boolean) inputStream.readObject();
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "Record added successfully", "Add Record Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if(action.equalsIgnoreCase("Find Student")) {
               
                try {
                	student = (Student) inputStream.readObject();
					
				} catch (EOFException e) {
					e.getMessage();
				}
                
                System.out.println(student);
                if (student == null) {
                    JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
                    
                }
                //return student;
            }
            if (action.equalsIgnoreCase("View All")) {
               
                try {
                	data = (Object[][])inputStream.readObject();
					
				} catch (EOFException e) {
					e.getMessage();
				}
                
                System.out.println(student);
                if (student == null) {
                    JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
                }
            }

        }catch(ClassCastException ex) {

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return student;
    }
    
    public Object[][] receiveAll() {
		Object[][] data = null;
	    try {
	        if (action.equalsIgnoreCase("View All")) {
	            try {
	            	data = (Object[][])inputStream.readObject();
				} catch (EOFException e) {
					e.getMessage();
				}
	            if (data == null) {
	                JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
	            }
	        }
	    }catch(ClassCastException ex) {
	
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return data;
    }
}
