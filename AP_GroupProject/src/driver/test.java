package driver;

import client.DBClient;
import domain.Student;

public class test {

    public static void main(String[] args) {

    	DBClient DBClient = new DBClient();

        Student student1 = DBClient.findIssue("Missing Lecturers", "2002450");

        System.out.println(student1);
    }
}