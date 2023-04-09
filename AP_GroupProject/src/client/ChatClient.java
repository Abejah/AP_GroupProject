package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
	
	private DataOutputStream objOs;
	private DataInputStream objIs;
	private Socket connectionSocket;
	
	public ChatClient(String username){
		createConnection();
		configureConnection(username);
	}
	
	public void createConnection() {
		try {
			//create a socket to connect to the server
			this.connectionSocket = new Socket("localhost", 8000);
			System.out.println("Connected to server.");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void configureConnection(String username) {
		try {
			objOs = new DataOutputStream(connectionSocket.getOutputStream());
			objIs = new DataInputStream(connectionSocket.getInputStream());
			objOs.writeUTF(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendMessage(String username, String messageToSend) {
		try {
			objOs.writeUTF(username);
			objOs.writeUTF(messageToSend);
			//System.out.println(username+" sent Message: "+messageToSend);
			objOs.flush();
		} catch (Exception e) {
			System.err.println("Error sending message to server");
			closeEverything(connectionSocket, objIs, objOs);
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
            closeEverything(connectionSocket, objIs, objOs);
        }
        messageGot = (username+" : "+message);
        return messageGot;
    }
	public void closeEverything(Socket socket, DataInputStream objIs, DataOutputStream objOs) {
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
}
