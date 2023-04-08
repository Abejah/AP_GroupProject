package view;

import javax.swing.*;

import client.DBClient;
import domain.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchForm extends JFrame {
    private JTextField searchField;
    private JTextField idSearchField;
    private JButton searchButton;
    private JTextArea resultsArea;

    public SearchForm()
    {

        Student student=new Student();
        String[] data={student.toString()};
        String[] columnNames={"Id Number", "First Name", "Last Name", "Email", "Contact Number", "Issue Type","Issue", "Issue Details", "Responses"};
        //super("Search GUI");
        setLayout(null);

        searchField = new JTextField("Compliant/Query");
        idSearchField=new JTextField("ID Number");
        searchButton = new JButton("Search");
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);

        searchField.setBounds(10, 10, 200, 30);
        idSearchField.setBounds(220, 10, 200, 30);
        searchButton.setBounds(420, 10, 100, 30);

        //resultsArea.setBounds(10, 50, 380, 210);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBounds(10, 60, 700, 600);

        searchField.setForeground(Color.GRAY);
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Compliant/Query")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Compliant/Query");
                }
            }
        });

        idSearchField.setForeground(Color.GRAY);
        idSearchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idSearchField.getText().equals("ID Number")) {
                    idSearchField.setText("");
                    idSearchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (idSearchField.getText().isEmpty()) {
                    idSearchField.setForeground(Color.GRAY);
                    idSearchField.setText("ID Number");
                }
            }
        });

        add(searchField);
        add(idSearchField);
        add(searchButton);
        //add(resultsArea);
        add(scrollPane);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                String id= idSearchField.getText();

                DBClient DBClient =new DBClient();
                Student students = DBClient.findIssue(search,id);

                //Student student=new Student();
                Student[] data={students};
                //String[] columnNames={"Id Number", "First Name", "Last Name", "Email", "Contact Number", "Issue Type","Issue", "Issue Details", "Responses"};


                //System.out.println(student1);
                // Perform search with query and display results in resultsArea
                displayTable(data);
            }
        });

        setBounds(250,30,781, 700);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

    }
    private void displayTable(Student[] data) {
        StringBuilder sb = new StringBuilder();

        for (Student cell : data) {
            sb.append(cell).append(" ");
        }
        sb.append("\n");

        resultsArea.setText(sb.toString());

    }

    public static void main(String[] args) {
        SearchForm searchForm=new SearchForm();
        searchForm.setVisible(true);
    }
}
