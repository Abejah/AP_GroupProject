package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket serverSocket;
	private Socket connectionSocket;

	public Server() {
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("Server started at " + new java.util.Date()+"\n");
			while(!serverSocket.isClosed()) {
				connectionSocket = serverSocket.accept();//waits for a client to connect and send's that clients socket object to socket
				System.out.println("A new client has connected at " + new java.util.Date());
				
				ClientHandler clientHandler = new ClientHandler(connectionSocket);//responsible for communicating with the client
								
				Thread thread = new Thread((Runnable)clientHandler);//Passes instance of the clientHandler class into a new thread
				thread.start();//runs the thread
				System.out.println("  Thread Id: "+thread.getId()+"\n");
			}//end of while loop
		} catch (IOException e) {
			System.err.println("Error: "+e.getMessage());
			closeServerSocket();
		}
	}
	
	public void closeServerSocket() {//for error handling...if theres an error 
		try {
			if (serverSocket != null) {
				serverSocket.close();//close the server
			}
		}catch (Exception e) {
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {//exception added to method signature alternative to try-catch
		new Server();	
	}
	
	//inner class
	public class ClientHandler implements Runnable{
		private Socket clientSocket;
		private DataOutputStream objOs;
        private DataInputStream objIs;
		private String clientUsername;
		private String messageFromClient;
	
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
			try {				
				objOs = new DataOutputStream(clientSocket.getOutputStream());
                objIs = new DataInputStream(clientSocket.getInputStream());
                clientUsername=objIs.readUTF();
                //System.out.println(clientUsername+" has joined the chat");//Prints to console
			} catch (IOException e) {
				 System.err.println("Error creating input/output streams for client: " + e.getMessage());
				closeEverything(clientSocket, objIs, objOs);
			}
		}
		
		public void run() {//what is done on each seperate threads
			try {
				while(clientSocket.isConnected()) {
					clientUsername=objIs.readUTF();//listens for usernames
					messageFromClient=objIs.readUTF();//listens for messages
					System.out.println("Message from "+clientUsername+" >> "+messageFromClient);
					
					objOs.writeUTF(clientUsername);//writes username
					objOs.writeUTF(messageFromClient);//writes message
					//System.out.println("Message sent from server to client >> "+clientUsername+" : "+messageFromClient);//jus to see
					objOs.flush();
				}
			}catch (Exception e) {
				System.err.println("Client "+clientUsername+" disconnected");

				closeEverything(clientSocket, objIs, objOs);
			}	
		}//end of run
		
		public void closeEverything(Socket connectionSocket, DataInputStream objIs, DataOutputStream objOs) {
			try {
				if (objIs!=null)objIs.close();
				if (objOs!=null)objOs.close();
				if (connectionSocket!=null)connectionSocket.close();	
			} catch (Exception e) {
				  System.err.println("Error closing socket and streams: " + e.getMessage());
			}
		}//end of closeEverything
		
	}//end of inner class
	
	
}//end of server class