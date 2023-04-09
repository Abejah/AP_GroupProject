package view;

import domain.Supervisor;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Enumeration;

public class SupervisorForm extends JFrame implements Serializable
{
	 private static final long serialVersionUID = 1L;
	    private Supervisor Supervisor;

	    private static JTextField staffID,firstName,lastName,email,password;
	    private static JButton saveButton;

	    public static void main(String[] args)
	    {
	        SupervisorForm SupervisorForm = new SupervisorForm();
	        SupervisorForm.setVisible(true);

	    }


	    public SupervisorForm() {
	        //of labels
	        JLabel staffIDLabel = new JLabel("Staff ID Number: ");
	        JLabel firstNameLabel = new JLabel("First Name: ");
	        JLabel lastNameLabel = new JLabel("Last Name: ");
	        JLabel emailLabel = new JLabel("Email: ");
	        JLabel passwordLabel = new JLabel("Password: ");
	        
	        //of text-fields
	        staffID = new JTextField();
	        firstName = new JTextField();
	        lastName = new JTextField();
	        email = new JTextField();
	        password = new JTextField();


	        staffIDLabel.setBounds(50, 50, 100, 30);
	        staffID.setBounds(150, 50, 200, 30);

	        firstNameLabel.setBounds(50, 100, 100, 30);
	        firstName.setBounds(150, 100, 200, 30);

	        lastNameLabel.setBounds(50, 150, 100, 30);
	        lastName.setBounds(150, 150, 200, 30);

	        emailLabel.setBounds(50, 200, 100, 30);
	        email.setBounds(150, 200, 200, 30);

	        passwordLabel.setBounds(50, 250, 100, 30);
	        password.setBounds(150, 250, 200, 30);
	       
	        saveButton = new JButton("Save");
	        saveButton.setBounds(200, 600, 100, 30);


	        add(staffIDLabel);
	        add(staffID);
	        add(firstNameLabel);
	        add(firstName);
	        add(lastNameLabel);
	        add(lastName);
	        add(emailLabel);
	        add(email);
	        add(passwordLabel);
	        add(password);
	        //add(scrollPane);
	        add(saveButton);

	        setTitle("Supervisor Form");
	        setBounds(250,30,781, 618);
	        setLayout(null);
	        setResizable(false);
	        setUndecorated(true);

	        saveButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String idField = staffID.getText();
	                String fNameField = firstName.getText();
	                String lNameField = lastName.getText();
	                String emailField = email.getText();
	                String passwordField= password.getText();
	                
	            }
	        });

	                /*Controller.createRecord(Supervisor);{
	                staffID.setText("");
	                firstName.setText("");
	                lastName.setText("");
	                email.setText("");
	                password.setText("");*/
	                 
	    }

}