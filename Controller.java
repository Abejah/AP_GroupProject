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
        String responses=student.getResponses();

        String createRecordSQL="INSERT INTO studentsdb.students(idNumber, firstName, lastName, email, contactNumber, issueType, issue, issueDetails,responses)" + "VALUES('"+idNumber+"', '"+firstName+"','"+lastName+"','"+email+"','"+contactNumber+"','"+issueType+"','"+issue+"','"+issueDetail+"','"+responses+"');";

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

    public Student findIssue(String Issue,String id) {
        String findStudentSQL = "SELECT idNumber,firstName,lastName,email,contactNumber,issueType,issue,issueDetails,responses FROM studentsdb.students WHERE issue ='"+Issue+"' AND idNumber ='"+id+"' ;";

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
                String issue = resultSet.getString("issue");
                String issueDetails=resultSet.getString("issueDetails");
                String responses=resultSet.getString("responses");

                Student issues=new Student();
                issues.setIdNumber(idNumber);
                issues.setFirstName(firstName);
                issues.setLastName(lastName);
                issues.setEmail(email);
                issues.setContactNumber(contactNumber);
                issues.setIssueType(issueType);
                issues.setIssueType(issue);
                issues.setIssueDetails(issueDetails);
                issues.setResponses(responses);

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

    public ResultSet getResultSet() {
        return resultSet;
    }

    public Student getInfoFromDatabase(String id) {
        String findid= "SELECT idNumber,firstName,lastName,email,contactNumber FROM studentsdb.students WHERE idNumber ='"+id+"';";
        try {
            statement =connection.createStatement();
            resultSet= statement.executeQuery(findid);

            if (resultSet.next()) {
                String idNumber1 = resultSet.getString("IdNumber");
                String firstName1 = resultSet.getString("firstName");
                String lastName1 = resultSet.getString("lastName");
                String email1 = resultSet.getString("email");
                int contactNumber1= resultSet.getInt("contactNumber");


                Student issues1=new Student();
                issues1.setIdNumber(idNumber1);
                issues1.setFirstName(firstName1);
                issues1.setLastName(lastName1);
                issues1.setEmail(email1);
                issues1.setContactNumber(contactNumber1);




                return issues1;
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
