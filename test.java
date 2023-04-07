package controller;

import model.Student;

public class test
{

    public static void main(String[] args) {

        Controller controller = new Controller();

        Student student1 = controller.findIssue("Missing Grades","1001");

        System.out.println(student1);
    }
}