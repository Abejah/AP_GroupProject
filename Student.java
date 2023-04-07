package model;

public class Student
{
    private String idNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int contactNumber;
    private String issueType;
    private String issue;
    private String issueDetails;

    public Student()
    {
        this.idNumber = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.contactNumber = 0;
        this.issueType = "";
        this.issue= "";
        this.issueDetails = "";
    }

    public Student(String idNumber, String firstName, String lastName, String email,
                   int contactNumber, String issueType, String issue, String issueDetails)
    {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
        this.issueType = issueType;
        this.issue = issue;
        this.issueDetails = issueDetails;
    }


    public Student(Student student) {
        this.firstName = student.firstName;
        this.lastName = student.lastName;
        this.email = student.email;
        this.contactNumber = student.contactNumber;
        this.issueType = student.issueType;
        this.issue = student.issue;
        this.issueDetails = student.issueDetails;
    }
    //getters and setters
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

    @Override
    public String toString()
    {
        return "Student{" +
                "idNumber='" + idNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber=" + contactNumber +
                ", issueType='" + issueType + '\'' +
                ", issue='" + issue + '\'' +
                ", issueDetails='" + issueDetails + '\'' +
                '}';
    }
}


