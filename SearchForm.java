package view;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTextArea resultsArea;

    public SearchForm() {
        //super("Search GUI");
        setLayout(null);

        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);

        searchField.setBounds(10, 10, 200, 30);
        searchButton.setBounds(220, 10, 100, 30);

        //resultsArea.setBounds(10, 50, 380, 210);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBounds(10, 60, 681, 600);



        add(searchField);
        add(searchButton);
        //add(resultsArea);
        add(scrollPane);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();

                Controller controller=new Controller();
                Student students = controller.findIssue(query,"1001");


                //System.out.println(student1);
                // Perform search with query and display results in resultsArea
                resultsArea.setText("Results for: " + students);
            }
        });

        setBounds(250,30,781, 618);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

    }

    public static void main(String[] args) {
        SearchForm searchForm=new SearchForm();
        searchForm.setVisible(true);
    }
}
