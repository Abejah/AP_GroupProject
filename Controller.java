package controller;

import model.Student;

import javax.swing.*;
import java.io.Serializable;
import java.sql.*;

public class Controller extends Student implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static Connection connection=null;
    private static Statement statement;
    private static ResultSet resultSet;


    public Controller()
    {
        connection=DbConn.getConnection();
    }

    public static void createRecord(Student student)
    {
        if(connection==null)
        {
            connection=DbConn.getConnection();
        }

        String idNumber=student.getIdNumber();
        String firstName= student.getFirstName();
        String lastName= student.getLastName();
        String email= student.getEmail();
        int contactNumber= student.getContactNumber();
        String issueType= student.getIssueType();
        String issue= student.getIssue();
        String issueDetail= student.getIssueDetails();

        String createRecordSQL="INSERT INTO studentsdb.students(idNumber, firstName, lastName, email, contactNumber, issueType, issue, issueDetails)" + "VALUES('"+idNumber+"', '"+firstName+"','"+lastName+"','"+email+"','"+contactNumber+"','"+issueType+"','"+issue+"','"+issueDetail+"');";

        try {
            statement = connection.createStatement();
            int inserted = statement.executeUpdate(createRecordSQL);

            if(inserted == 1) {
                JOptionPane.showMessageDialog(null, "Student Information Stored Successfully", "Student Insertion Status", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Student Information Stored Unsuccessfully", "Student Insertion Status", JOptionPane.ERROR_MESSAGE);
            }

        }catch(SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "SQL Syntax Excpetion Detected: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception Error: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage() + "Try again later", "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Student findIssue(String issue,String id) {
        String findStudentSQL = "SELECT idNumber, firstName, lastName, email, contactNumber,issueType,issue, issueDetails FROM studentsdb.students WHERE issue ='"+issue+"' AND idNumber ='"+id+"' ;";

        try {
            statement =connection.createStatement();
            resultSet = statement.executeQuery(findStudentSQL);

            if (resultSet.next()) {
                String idNumber = resultSet.getString("IdNumber");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                int contactNumber= resultSet.getInt("contactNumber");
                String issueType = resultSet.getString("issueType");
                String issue1 = resultSet.getString("issue");
                String issueDetails=resultSet.getString("IssueDetails");


                Student issues=new Student();
                issues.setIdNumber(idNumber);
                issues.setFirstName(firstName);
                issues.setLastName(lastName);
                issues.setEmail(email);
                issues.setContactNumber(contactNumber);
                issues.setIssueType(issueType);
                issues.setIssueType(issue1);
                issues.setIssueDetails(issueDetails);


                return issues;
            }
        }catch(SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "SQL Syntax Excpetion Detected: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception Error: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage() + "Try again later", "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }
        return null;



    }
}
