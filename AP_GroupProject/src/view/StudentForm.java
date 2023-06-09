package view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import client.DBClient;
import domain.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.sql.*;
import java.util.Enumeration;

public class StudentForm extends JFrame implements Serializable
{
    private static final long serialVersionUID = 1L;
    //private Student student;

    private static JTextField refNumber,idNumber,firstName,lastName,email,contactNumber;
    private static JButton saveButton;
    private static JTextArea detail;
    private Student student=new Student();

    public static void main(String[] args)
    {
        StudentForm studentForm = new StudentForm();
        studentForm.setVisible(true);

    }


    public StudentForm() {
        //declaration and initialization
        String[] issueType = {"Compliant", "Query"};
        ButtonGroup group = new ButtonGroup();
        int x = 150;
        String[] compliantType = {"Missing Grades", "Student Records Processing", "Module Selection Glitches",
                "Missing Lecturers", "Academic Advisor"};
        JComboBox<String> compliantTypeComboBox = new JComboBox<>(compliantType);
        String[] queryType = {"Financial Clearance", "Academic Records", "Module Queries",
                "Missing Lecturers", "Academic Advisor"};
        JComboBox<String> queryTypeComboBox = new JComboBox<>(queryType);



        //of labels
        //JLabel refNumberLabel = new JLabel("Reference Number: ");
        JLabel idNumberLabel = new JLabel("ID Number: ");
        JLabel firstNameLabel = new JLabel("First Name: ");
        JLabel lastNameLabel = new JLabel("Last Name: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel contactNumberLabel = new JLabel("Contact Number: ");
        JLabel issueTypeLabel = new JLabel("Issue Type: ");
        JLabel complaintTypeLabel = new JLabel("Complaint Type: ");
        JLabel queryTypeLabel = new JLabel("Query Type: ");
        JLabel complaintDetailLabel = new JLabel("Details: ");

        //of text-fields
        refNumber = new JTextField();
        idNumber = new JTextField();
        firstName = new JTextField();
        lastName = new JTextField();
        email = new JTextField();
        contactNumber = new JTextField();
        detail = new JTextArea();
        
        idNumber.setToolTipText("Input your ID the press ENTER on your keyboard to check if your ID exists.");

        //refNumberLabel.setBounds(50, 5, 100, 30);
        //refNumber.setBounds(150, 5, 200, 30);

        idNumberLabel.setBounds(50, 50, 100, 30);
        idNumber.setBounds(150, 50, 200, 30);

        firstNameLabel.setBounds(50, 100, 100, 30);
        firstName.setBounds(150, 100, 200, 30);

        lastNameLabel.setBounds(50, 150, 100, 30);
        lastName.setBounds(150, 150, 200, 30);

        emailLabel.setBounds(50, 200, 100, 30);
        email.setBounds(150, 200, 200, 30);

        contactNumberLabel.setBounds(50, 250, 100, 30);
        contactNumber.setBounds(150, 250, 200, 30);

        issueTypeLabel.setBounds(50, 300, 100, 30);
        for (String option : issueType) {
            JRadioButton radioButton = new JRadioButton(option);
            radioButton.setBounds(x, 300, 100, 30);
            group.add(radioButton);
            add(radioButton);
            x += 150;



            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (option.equals("Compliant")) {
                        compliantTypeComboBox.setEnabled(true);
                    } else {
                        compliantTypeComboBox.setEnabled(false);
                    }
                    if (option.equals("Query")) {
                        queryTypeComboBox.setEnabled(true);
                    } else {
                        queryTypeComboBox.setEnabled(false);
                    }
                }
            });
        }

        complaintTypeLabel.setBounds(50, 350, 100, 30);
        compliantTypeComboBox.setBounds(150, 350, 200, 30);

        queryTypeLabel.setBounds(50, 400, 100, 30);
        queryTypeComboBox.setBounds(150, 400, 200, 30);

        complaintDetailLabel.setBounds(50, 450, 100, 30);
        detail.setBounds(150, 450, 200, 150);
        detail.setBorder(new LineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(detail);

        saveButton = new JButton("Save");
        saveButton.setBounds(200, 620, 75, 30);

        //add(refNumberLabel);
        //add(refNumber);
        add(idNumberLabel);
        add(idNumber);
        add(firstNameLabel);
        add(firstName);
        add(lastNameLabel);
        add(lastName);
        add(emailLabel);
        add(email);
        add(contactNumberLabel);
        add(contactNumber);
        add(issueTypeLabel);
        add(complaintTypeLabel);
        add(compliantTypeComboBox);
        add(queryTypeLabel);
        add(queryTypeComboBox);
        add(complaintDetailLabel);
        add(detail);
        add(scrollPane);
        add(saveButton);

        setTitle("Student Compliant/Query Form");
        setBounds(250,30,700, 650);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

        idNumber.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String idField=idNumber.getText();

                    DBClient dbClient =new DBClient();
                    dbClient.sendAction("Find StudentID");
                    dbClient.sendStudentId(idField);
                    student= dbClient.receiveResponse();

                    firstName.setEditable(true);
                    lastName.setEditable(true);
                    email.setEditable(true);
                    contactNumber.setEditable(true);
                    
                firstName.setText(student.getFirstName());
				lastName.setText(student.getLastName());
				email.setText(student.getEmail());
				contactNumber.setText(String.valueOf(student.getContactNumber()));
              
				idNumber.setEditable(false);
                firstName.setEditable(false);
                lastName.setEditable(false);
                email.setEditable(false);
                contactNumber.setEditable(false);
            }
        });
        refNumber.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBClient DBClient =new DBClient();
                refNumber.setEditable(false);
                refNumber.setText(student.getRefNumber().toString());
				JOptionPane.showMessageDialog(null, "Please Make Note of your Reference Number: "+ refNumber, "IMPORTANT MESSAGE", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String refField = refNumber.getText();
                String idField = idNumber.getText();
                String fNameField = firstName.getText();
                String lNameField = lastName.getText();
                String emailField = email.getText();
                int contactNumberField= Integer.parseInt(contactNumber.getText());
                //String issueTypeRadiobutton = String.valueOf(group.getSelection());
                String DetailField = detail.getText();
                String complaintTypeBox = (String) compliantTypeComboBox.getSelectedItem();
                String queryTypeBox = (String) queryTypeComboBox.getSelectedItem();

                for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
                    AbstractButton button = buttons.nextElement();
                    if (button.isSelected()) {
                        if (button.getText().equals("Compliant")) {
                            //System.out.println("compliant");""
                        	DBClient dbClient = new DBClient();
                            Student student=new Student(refField,idField,fNameField,lNameField,emailField,contactNumberField,"Compliant",complaintTypeBox,DetailField,"You should get a lecturer by thursday Miss Roye, sorry for any inconvenienced caused");   
                            dbClient.sendAction("Add Student");
                            System.out.println("Message sent to server");
                            dbClient.sendStudent(student);
        					System.out.println("Record sent to the server");
        					dbClient.receiveResponse();
        					System.out.println("response received from server");

                        } else {
                            if (button.getText().equals("Query")) {
                                //System.out.println("query");
                            	DBClient dbClient = new DBClient();
                            	Student student=new Student(refField,idField,fNameField,lNameField,emailField,contactNumberField,"Query",queryTypeBox,DetailField,"hi");
                            	dbClient.sendAction("Add Student");
                                System.out.println("Message sent to server");
                                dbClient.sendStudent(student);
            					System.out.println("Record sent to the server");
            					dbClient.receiveResponse();
            					System.out.println("response received from server");
                            }
                        }
                    }
                }

                refNumber.setText("");
                idNumber.setText("");
                firstName.setText("");
                lastName.setText("");
                email.setText("");
                contactNumber.setText("");
                group.clearSelection();
                queryTypeComboBox.setSelectedIndex(0);
                compliantTypeComboBox.setSelectedIndex(0);
                detail.setText("");

            }
        });



    }

}


