package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JOptionPane;


import view.Administration;
import view.MainMenu;
import view.SSAdvisor;
import view.Student;

public class Client extends Student implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private Socket connectionSocket;
	private String action;
	
	public Client(){
		this.createConnection();
		this.configureConnection();
	}
	
	public void createConnection() {
		try {
			//create a socket to connect to the server
			this.connectionSocket = new Socket("localhost", 8888);
			System.out.println("Connected to server.");
			 MainMenu main = new MainMenu();    //open the main menu when connected to server
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void configureConnection() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closeConnection() {
        try {
            objOs.close();
            objIs.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendAction(String action) {
        this.action = action;
        try {
            objOs.writeObject(action);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	//write object to database 
    public void sendStudent(Student stuObj) {
        try {
            objOs.writeObject(stuObj);
            objOs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //write object to database 
    public void sendSupervisor(Administration adObj) {
        try {
            objOs.writeObject(adObj);
            objOs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //write object to database 
    public void sendAdvisor(SSAdvisor ssaObj) {
        try {
            objOs.writeObject(ssaObj);
            objOs.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public void receiveResponse() {
        try {
            if(action.equalsIgnoreCase("Add Student")) {
                Boolean flag = (Boolean) objIs.readObject();
                if (flag == true) {
                    JOptionPane.showMessageDialog(null, "Record added successfully", "Add Record Status", JOptionPane.INFORMATION_MESSAGE);
                }
            } else  if(action.equalsIgnoreCase("Add Supervisor")){
                    Boolean flag = (Boolean) objIs.readObject();
                    if (flag == true) {
                        JOptionPane.showMessageDialog(null, "Record added successfully", "Add Record Status", JOptionPane.INFORMATION_MESSAGE);
                    }
            	} else if(action.equalsIgnoreCase("Add Advisor")) {
                    Boolean flag = (Boolean) objIs.readObject();
                    if (flag == true) {
                        JOptionPane.showMessageDialog(null, "Record added successfully", "Add Record Status", JOptionPane.INFORMATION_MESSAGE);
                        
                    }
            	
            	}
            
         }catch(ClassCastException ex) {
        	ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public String listenForMessage() {
		String messageGot,message = null, username = null;
		
        try {
        	username=objIs.readUTF();
        	message = objIs.readUTF();
        	System.out.println(username+" : "+message);
        } catch (IOException e) {
            System.err.println("Error receiving message from server");
            closeAll(connectionSocket, objIs, objOs);
        }
        messageGot = (username+" : "+message);
        return messageGot;
    }
	
	public void closeAll(Socket socket, ObjectInputStream objIs, ObjectOutputStream objOs) {
		try {
			if (objIs!=null) {
				objIs.close();
			}
			if (objOs!=null) {
				objOs.close();
			}
			if (socket!=null) {
				socket.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of closeEverything
	
	public static void main(String[] args) {
		new Client();	//starts the client

	}
}
