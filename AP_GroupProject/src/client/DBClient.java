package client;

import javax.swing.*;

import domain.Student;
import factories.DbConn;

import java.io.Serializable;
import java.sql.*;

public class DBClient extends Student implements Serializable
{
	private static final long serialVersionUID = 1L;
    private static Connection dbConn=null;
    private static Statement statement;
    private static ResultSet result;
    private static Student student=new Student();
    

    public DBClient()
    {
       dbConn=DbConn.getConnection();
    }

    public static void createRecord(Student student)
    {
        String idNumber=student.getIdNumber();
        String firstName= student.getFirstName();
        String lastName= student.getLastName();
        String email= student.getEmail();
        int contactNumber= student.getContactNumber();
        String issueType= student.getIssueType();
        String issue= student.getIssue();
        String issueDetail= student.getIssueDetails();
        String responses=student.getResponses();

        String createRecordSQL="INSERT INTO studentsdb.students(idNumber, firstName, lastName, email, contactNumber, issueType, issue, issueDetails,responses)" 
        + "VALUES('"+idNumber+"', '"+firstName+"','"+lastName+"','"+email+"','"+contactNumber+"','"+issueType+"','"+issue+"','"+issueDetail+"','"+responses+"');";

        try {
            statement = dbConn.createStatement();
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
            statement =dbConn.createStatement();
            result = statement.executeQuery(findStudentSQL);

            if (result.next()) {
                student.setIdNumber(result.getString("idNumber"));
                student.setFirstName(result.getString("firstName"));
                student.setLastName(result.getString("lastName"));
                student.setEmail(result.getString("email"));
                student.setContactNumber(result.getInt("contactNumber"));
                student.setIssueType(result.getString("issueType"));
                student.setIssue(result.getString("issue"));
                student.setIssueDetails(result.getString("issueDetails"));
                student.setResponses(result.getString("responses"));                         
            }
        }catch(SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "SQL Syntax Excpetion Detected: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "SQL Exception Error: " + e.getMessage(), "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage() + "Try again later", "Student Database Connection", JOptionPane.ERROR_MESSAGE);
        }
        return student;
    }
    
    
    public ResultSet getResultSet() {
        return result;
    }

    public Student getInfoFromDatabase(String id) {
        String findid= "SELECT idNumber,firstName,lastName,email,contactNumber FROM studentsdb.students WHERE idNumber ='"+id+"';";
        try {
            statement =dbConn.createStatement();
            result= statement.executeQuery(findid);

            if (result.next()) {
                String idNumber1 = result.getString("IdNumber");
                String firstName1 = result.getString("firstName");
                String lastName1 = result.getString("lastName");
                String email1 = result.getString("email");
                int contactNumber1= result.getInt("contactNumber");


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
