package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import javax.swing.JOptionPane;

public class DbConn implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static Connection dbConnection = null;

    public static Connection getConnection() {

        if(dbConnection == null) {
            String url = "jdbc:mysql://localhost:3306/studentsdb";

            try {
                dbConnection = DriverManager.getConnection(url, "root", "password");

                if (dbConnection != null) {
                    JOptionPane.showMessageDialog(null, "Database Connection Successful", "Students Database Connection", JOptionPane.INFORMATION_MESSAGE);
                }

            }catch(SQLSyntaxErrorException e) {
                JOptionPane.showMessageDialog(null, "SQL Syntax Excpetion Detected: " + e.getMessage(), "Students Database Connection", JOptionPane.ERROR_MESSAGE);
            }catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "SQL Exception Detected: " + e.getMessage() + "Try again later", "Students Database Connection", JOptionPane.ERROR_MESSAGE);
            }catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Unexpected Error: " + e.getMessage() + "Try again later", "Students Database Connection", JOptionPane.ERROR_MESSAGE);
            }

        }

        return dbConnection;

    }
}
