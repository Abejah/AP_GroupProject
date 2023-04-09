package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//import log4j2 library
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import client.Client;
import factories.DBConnectorFactory;

public class LoginPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private Connection connection = null;
	private Statement stmt;

	private Client client = new Client();
	private Administration administrator = new Administration();  //instantiate a new instance of the administration class
	private Student student = new Student();   //instantiate a new instance of the student class
	
	//initializing logger to class
    private static final Logger logger = LogManager.getLogger(LoginPage.class); 

	
	
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField username = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton1 = new JButton("ADMIN LOGIN");       //admin login button
    JButton loginButton2 = new JButton("STUDENT LOGIN");     //student login button
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
 
 
    public LoginPage() {
    	connection = DBConnectorFactory.getConnection();		//get connection from database
    	
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent1();     //admin action event
        addActionEvent2();     //student action event
 
    }
 
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        username.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton1.setBounds(50, 300, 120, 30);   //admin button
        loginButton2.setBounds(110, 400, 150, 30); 	//student button
        resetButton.setBounds(200, 300, 100, 30);
 
 
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(username);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton1);   //adding admin button to the container
        container.add(loginButton2);  //adding student button to the container
        container.add(resetButton);
    }
 
    //admin
    public void addActionEvent1() {
        loginButton1.addActionListener(this);   //listen for the admin button to be clicked
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
    
    //student
    public void addActionEvent2() {
        loginButton2.addActionListener(this);   //listen for the student button to be clicked
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
 
    //admin
	@Override
    public void actionPerformed(ActionEvent e) {
    	//Coding Part of LOGIN button
        
     if (e.getSource() == loginButton1) {
    	
    	try {
    		
        	logger.info("Admin attempted to login");   //user attempts to log in
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbproject", "root", "usbw");	
            String sql = "Select * from admin where userName=? and password= ?";
            
            PreparedStatement pst = connection.prepareStatement(sql);
            
            //accepting from user
            pst.setString(1, username.getText());
            pst.setString(2, passwordField.getText());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
            	JOptionPane.showMessageDialog(null, "You are logged in as administrator");
            	AdminMainMenu menu = new AdminMainMenu();
            	menu.setVisible(true);
            	setVisible(false);
            	
            	logger.info("Administrator Logged in successfully");
            	
            }else {

            	JOptionPane.showMessageDialog(null, "Invalid log in. Please try again.");
            	username.setText("");
            	passwordField.setText("");
            	
            	logger.info("Unsuccessful login attempt by administrator");
            }
            
            connection.close();
            
            
        	} catch (Exception exception) {
                exception.printStackTrace();
            } 
        	
     }	//closes if statement  
        
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            username.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
        
        else {

        	//Coding Part of LOGIN button
         if (e.getSource() == loginButton2) {
        	
        	try {
        		
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbproject", "root", "usbw");  //using local host 3307	
                String sql = "Select * from student where userName=? and password= ?";
                
                PreparedStatement pst = connection.prepareStatement(sql);
                //accepting from user
                pst.setString(1, username.getText());
                pst.setString(2, passwordField.getText());
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()) {
                	JOptionPane.showMessageDialog(null, "You are logged in as student");
                	StudentDashboard menu = new StudentDashboard();
                	menu.setVisible(true);
                	setVisible(false);
                	
                	logger.info("Student Logged in successfully");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Invalid log in. Please try again.");
                	username.setText("");
                	passwordField.setText("");
                	
                	logger.info("Unsuccessful login attempt by student");
                }
                
                connection.close();
                
                
            	} catch (Exception exception) {
                    exception.printStackTrace();
                } 
            	
         }	//closes if statement  
            
            //Coding Part of RESET button
            if (e.getSource() == resetButton) {
                username.setText("");
                passwordField.setText("");
            }
            
           //Coding Part of showPassword JCheckBox
            if (e.getSource() == showPassword) {
                if (showPassword.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        
        }
    } //this close the entire action performed
 


	
	public static void main(String args[]) {
		
		LoginPage frame = new LoginPage();
		frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(350, 190, 520, 570);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
		
		
	}     

}
