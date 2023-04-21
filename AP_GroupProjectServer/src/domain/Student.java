package domain;

import java.io.Serializable;

public class Student implements Serializable
{
	private static final long serialVersionUID = 1L;
    private String refNumber;
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int contactNumber;
    private String issueType;
    private String issue;
    private String issueDetails;



    private String responses;

    public Student()
    {
        this.refNumber="";
        this.idNumber = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.contactNumber = 0;
        this.issueType = "";
        this.issue= "";
        this.issueDetails = "";
        this.responses="";
    }

    public Student(String refNumber,String idNumber, String firstName, String lastName, String email,
                   int contactNumber, String issueType, String issue, String issueDetails, String responses)
    {
        this.refNumber=refNumber;
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.issueType = issueType;
        this.issue = issue;
        this.issueDetails = issueDetails;
        this.responses=responses;
    }


    public Student(Student student) {
        this.refNumber = student.refNumber;
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.contactNumber = student.contactNumber;
        this.issueType = student.issueType;
        this.issue = student.issue;
        this.issueDetails = student.issueDetails;
        this.responses=student.responses;
    }
    //getters and setters


    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getIssueDetails() {
        return issueDetails;
    }

    public void setIssueDetails(String issueDetails) {
        this.issueDetails = issueDetails;
    }

    public String getResponses() {
        return responses;
    }

    public void setResponses(String responses) {
        this.responses = responses;
    }

    @Override
    public String toString() {
        return ("Reference Number: "+ refNumber +"ID Number: "+ idNumber +"\nFirst Name:"+ firstName +"\nLast Name: "+ lastName + "\nemail: "+ email
                +"\nContact Number: "+ contactNumber +"\nIssue Type: "+ issueType +"\nIssue: "+ issue +"\nIssue Details: "+
                issueDetails + "\nResponses:"+ responses + "\n");

    }
}


