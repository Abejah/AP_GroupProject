package view;

import java.io.Serializable;

public class Student implements Serializable
{
	
private static final long serialVersionUID = 1L;
private String firstName;
private String lastName;
private String userName;
private String passowrd;
private String email;
private String mobileNumber;

public Student() {
	this.firstName = "";
	this.lastName = "";
	this.userName = "";
	this.passowrd = "";
	this.email = "";
	this.mobileNumber = "";
}

public Student(String firstName, String lastName, String userName, String passowrd, String email,
		String mobileNumber) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.userName = userName;
	this.passowrd = passowrd;
	this.email = email;
	this.mobileNumber = mobileNumber;
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

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassowrd() {
	return passowrd;
}

public void setPassowrd(String passowrd) {
	this.passowrd = passowrd;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getMobileNumber() {
	return mobileNumber;
}

public void setMobileNumber(String mobileNumber) {
	this.mobileNumber = mobileNumber;
}

@Override
public String toString() {
	return "First Name: " + firstName +
			"\nLast Name: " + lastName + 
			"\nUser Name: " + userName + 
			"\nPassowrd: " + passowrd + 
			"\nEmail: " + email + 
			"\nMobile Number: " + mobileNumber + "\n";
}




}
