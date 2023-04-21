package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import server.Server;


public class MainRegistration extends JFrame{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField firstname;
    private JTextField lastname;
    private JTextField email;
    private JTextField username;
    private JTextField mob;
    private JPasswordField passwordField;
    private JButton btnNewButton1;   //Register Admin/ Supervisor
    private JButton btnNewButton2;   //Register Student
    private JButton btnNewButton3;   //Register Advisor
    private JButton btnNewButton4;   //login page button
    
    private Server server = new Server();   //initialize a new instance of the client class so that an object(user) can be created
    
    private Administration administrator = new Administration();  //instantiate a new instance of the administration class
    
    private SSAdvisor ssa = new SSAdvisor();   //instantiate a new instance of the SSAdvisor class
    
    private Student student = new Student();   //instantiate a new instance of the student class
    
    public MainRegistration() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 850);
        setResizable(true);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewUserRegister = new JLabel("University of Technology Registration ");
        lblNewUserRegister.setFont(new Font("Times New Roman", Font.PLAIN, 42));
        lblNewUserRegister.setBounds(190, 52, 755, 50);
        contentPane.add(lblNewUserRegister);

        JLabel lblName = new JLabel("First name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email\r\n address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);

        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstname.setBounds(214, 151, 228, 50);
        contentPane.add(firstname);
        firstname.setColumns(10);

        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastname.setBounds(214, 235, 228, 50);
        contentPane.add(lastname);
        lastname.setColumns(10);

        email = new JTextField();

        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 320, 228, 50);
        contentPane.add(email);
        email.setColumns(10);

        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
        username.setBounds(707, 151, 228, 50);
        contentPane.add(username);
        username.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        JLabel lblMobileNumber = new JLabel("Mobile number");
        lblMobileNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblMobileNumber.setBounds(542, 329, 139, 26);
        contentPane.add(lblMobileNumber);

        mob = new JTextField();
        mob.setFont(new Font("Tahoma", Font.PLAIN, 32));
        mob.setBounds(707, 320, 228, 50);
        contentPane.add(mob);
        mob.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 235, 228, 50);
        contentPane.add(passwordField);

      //this button registers a Supervisor
        btnNewButton1 = new JButton("Register Supervisor");
        btnNewButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	administrator.setFirstName(firstname.getText());
            	administrator.setLastName(lastname.getText());
            	administrator.setEmail(email.getText());
            	administrator.setPassowrd(passwordField.getText());
            	administrator.setUserName(username.getText());
            	administrator.setMobileNumber(mob.getText());
      
                int len = mob.getText().length();
                
                //NOTE: THE MOBILE NUMBER MUST BE LESS THAN 10 !!!!!!!
                if (len > 10) {
                    JOptionPane.showMessageDialog(btnNewButton1, "Enter a valid mobile number");
                }
                
                Server.createAdministrationRecord(administrator);
            }
        });
        
        //this button registers a student
        btnNewButton2 = new JButton("Register Student");
        btnNewButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	student.setFirstName(firstname.getText());
            	student.setLastName(lastname.getText());
            	student.setEmail(email.getText());
            	student.setPassowrd(passwordField.getText());
            	student.setUserName(username.getText());
            	student.setMobileNumber(mob.getText());
      
                int len = mob.getText().length();
                
                //NOTE: THE MOBILE NUMBER MUST BE LESS THAN 10 !!!!!!!
                if (len > 10) {
                    JOptionPane.showMessageDialog(btnNewButton2, "Enter a valid mobile number");
                }
                
                Server.createStudentRecord(student);
            }
        });
        
      //this button registers an advisor
        btnNewButton3 = new JButton("Register Advisor");
        btnNewButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ssa.setFirstName(firstname.getText());
            	ssa.setLastName(lastname.getText());
            	ssa.setEmail(email.getText());
            	ssa.setPassowrd(passwordField.getText());
            	ssa.setUserName(username.getText());
            	ssa.setMobileNumber(mob.getText());
      
                int len = mob.getText().length();
                
                //NOTE: THE MOBILE NUMBER MUST BE LESS THAN 10 !!!!!!!
                if (len > 10) {
                    JOptionPane.showMessageDialog(btnNewButton1, "Enter a valid mobile number");
                }
                
                Server.createSSAdvisorRecord(ssa);
            }
        });
        
        //this button is linked to login page
        btnNewButton4 = new JButton("Login");
        btnNewButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            	LoginPage lg = new LoginPage();
            	lg.setVisible(true);
            	setVisible(false);
            	
                
//                Client.createAdministrationRecord(administrator);
            }
        });
        
        
        btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton1.setBounds(150, 447, 259, 74);
        contentPane.add(btnNewButton1);
        
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton2.setBounds(550,447 , 259, 74);
        contentPane.add(btnNewButton2);
        
        btnNewButton3.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton3.setBounds(150, 570 , 259, 74);
        contentPane.add(btnNewButton3);
        
        btnNewButton4.setFont(new Font("Tahoma", Font.PLAIN, 22));
        btnNewButton4.setBounds(550, 570 , 259, 74);
        contentPane.add(btnNewButton4);
        
    } 
        
    
	public static void main(String args[]) {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	MainRegistration frame = new MainRegistration();
                	frame.setTitle("University of Technology Registration Form");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); 
		
		
	}   
     


}
