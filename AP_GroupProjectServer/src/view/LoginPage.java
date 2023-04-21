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

import factories.DBConnectorFactory;
import server.Server;

public class LoginPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private ResultSet rs;
	private Connection connection = null;
	private Statement stmt;

	private Server server = new Server();
	private Administration administrator = new Administration();  //instantiate a new instance of the administration class
	private Student student = new Student();   //instantiate a new instance of the student class
	
	//initializing logger to class
    private static final Logger logger = LogManager.getLogger(LoginPage.class); 

   private Container container = getContentPane();
   private JLabel userLabel = new JLabel("USERNAME");
   private JLabel passwordLabel = new JLabel("PASSWORD");
   private JTextField username = new JTextField();
   private JPasswordField passwordField = new JPasswordField();
   private JButton loginButton1 = new JButton("Supervisor Login");       //admin login button
   private JButton loginButton2 = new JButton("Student Login");     //student login button
   private JButton loginButton3 = new JButton("Advisor Login");      //logs in an advisor
   private JButton resetButton = new JButton("RESET");
   private JCheckBox showPassword = new JCheckBox("Show Password");
 
 
    public LoginPage() {
    	connection = DBConnectorFactory.getConnection();		//get connection from database
    	setTitle("Login Page");
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent1();     //admin action event
        addActionEvent2();     //student action event
        addActionEvent3();     //advisor action event
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 190, 500, 520);
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
        loginButton1.setBounds(50, 300, 146, 30);   //supervisor button
        loginButton2.setBounds(220, 300, 146, 30); 	//student button
        loginButton3.setBounds(50, 360, 146, 30);	//advisor button
        resetButton.setBounds(250, 360, 90, 30);
 
 
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(username);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton1);   //adding supervisor button to the container
        container.add(loginButton2);  //adding student button to the container
        container.add(loginButton3);	//adding advisor button to the container
        container.add(resetButton);
    }
 
    //supervisor
    public void addActionEvent1() {
        loginButton1.addActionListener(this);   //listen for the supervisor button to be clicked
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
    
    //student
    public void addActionEvent2() {
        loginButton2.addActionListener(this);   //listen for the student button to be clicked
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
  //advisor
    public void addActionEvent3() {
        loginButton3.addActionListener(this);   //listen for the advisor button to be clicked
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
    //action performed once a button is clicked
	@Override
    public void actionPerformed(ActionEvent e) {
    	//Coding Part of supervisor LOGIN button
     if (e.getSource() == loginButton1) {	//logs in a Student Service Supervisor
    	
    	try {
        	logger.info("Student Service Supervisor attempted to login");   //user attempts to log in
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbproject", "root", "usbw");	
            String sql = "Select * from admin where userName=? and password= ?";
            
            PreparedStatement pst = connection.prepareStatement(sql);
            
            //accepting from user
            pst.setString(1, username.getText());
            pst.setString(2, passwordField.getText());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
            	JOptionPane.showMessageDialog(null, "You are logged in as a Student Service Supervisor");
            	AdminMainMenu menu = new AdminMainMenu();
            	menu.setVisible(true);
            	setVisible(false);
            	
            	logger.info("Student Service Supervisor logged in successfully");
            	
            }else {

            	JOptionPane.showMessageDialog(null, "Invalid log in. Please try again.");
            	username.setText("");
            	passwordField.setText("");
            	
            	logger.info("Unsuccessful login attempt by Student Service Supervisor");
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
        
        else if(e.getSource() == loginButton2) {		//logs in a student

        	//Coding Part of student LOGIN button
        	try {
        		logger.info("Student attempted to login");   //user attempts to log in
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
                	
                	logger.info("Student logged in successfully");
                	
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
        
        else if (e.getSource() == loginButton3) {		//Student Service Advisor log in

        	//Coding Part of LOGIN button
        	try {
        		logger.info("Student Service Advisor attempted to login");   //user attempts to log in
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/dbproject", "root", "usbw");  //using local host 3307	
                String sql = "Select * from advisor where userName=? and password= ?";
                
                PreparedStatement pst = connection.prepareStatement(sql);
                //accepting from user
                pst.setString(1, username.getText());
                pst.setString(2, passwordField.getText());
                
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()) {
                	JOptionPane.showMessageDialog(null, "You are logged in as a Student Service Advisor");
                	SSADashboard menu = new SSADashboard();
                	menu.setVisible(true);
                	setVisible(false);
                	
                	logger.info("Student Service Advisor logged in successfully");
                	
                }else {
                	JOptionPane.showMessageDialog(null, "Invalid log in. Please try again.");
                	username.setText("");
                	passwordField.setText("");
                	
                	logger.info("Unsuccessful login attempt by Student Service Advisor");
                }
                
                connection.close();
                
                
            	} catch (Exception exception) {
                    exception.printStackTrace();
                } 
            	
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
        frame.setResizable(false);
		
	}     

}
