package view;

import model.Student;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.Serializable;

public class StudentQueryForm extends JFrame implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Student student;

    private static JTextField idNumber,firstName,lastName,email,contactNumber;
    private static JButton saveButton;
    private static JTextArea queryDetail;

    public static void main(String[] args)
    {
        StudentQueryForm studentQueryForm= new StudentQueryForm();
        studentQueryForm.setVisible(true);

    }

    public StudentQueryForm()
    {
        //decaration and initialization
        String[] queryType={"Financial Clearance","Academic Records","Module Queries",
                "Missing Lecturers","Academic Advisor"};
        JComboBox<String> queryTypeComboBox=new JComboBox<>(queryType);

        //of labels
        JLabel idNumberLabel =new JLabel("ID Number: ");
        JLabel firstNameLabel =new JLabel("First Name: ");
        JLabel lastNameLabel =new JLabel("Last Name: ");
        JLabel emailLabel =new JLabel("Email: ");
        JLabel contactNumberLabel=new JLabel("Contact Number: ");
        JLabel queryTypeLabel= new JLabel("Query Type: ");
        JLabel queryDetailLabel= new JLabel("Query Details: ");

        //of text-fields
        idNumber=new JTextField();
        firstName=new JTextField();
        lastName=new JTextField();
        email=new JTextField();
        contactNumber=new JTextField();
        queryDetail=new JTextArea();


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

        queryTypeLabel.setBounds(50, 300, 100, 30);
        queryTypeComboBox.setBounds(150, 300, 200, 30);

        queryDetailLabel.setBounds(50, 350, 100, 30);
        queryDetail.setBounds(150, 350, 200, 150);
        queryDetail.setBorder(new LineBorder(Color.GRAY));
        JScrollPane scrollPane = new JScrollPane(queryDetail);


        saveButton = new JButton("Save");
        saveButton.setBounds(200, 550, 100, 30);


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
        add(queryTypeLabel);
        add(queryTypeComboBox);
        add(queryDetailLabel);
        add(queryDetail);
        add(scrollPane);
        add(saveButton);

        setTitle("Student Query Form");
        setSize(500,680);
        setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

}
