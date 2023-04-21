package view;

import javax.swing.*;


import client.DBClient;
import domain.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchForm extends JFrame implements ActionListener{
  
	private static final long serialVersionUID = 1L;
	private JTextField searchField;
    private JTextField idSearchField;
    private JButton searchButton;
    private JTextArea resultsArea;
    private JScrollPane scrollPane;
    
    public Student student=new Student();
       
    public SearchForm()
    {
    	//this.db= new DBClient();
    	//this.student=new Student();

       
        
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
        scrollPane = new JScrollPane();
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        scrollPane.setBounds(10, 60, 700, 500);
       

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
        add(scrollPane);
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = searchField.getText();
                String id= idSearchField.getText();
                
                DBClient dbClient =new DBClient();
                dbClient.sendAction("Find Student");
                dbClient.sendStudentInfo(search, id);
                student= dbClient.receiveResponse();

                resultsArea.append(student.toString());
            }
        });
         

        setBounds(250,30,781, 650);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

    }
//    private void displayTable(Student[] data) {
//        StringBuilder sb = new StringBuilder();
//
//        for (Student cell : data) {
//            sb.append(cell).append(" ");
//        }
//        sb.append("\n");
//
//        resultsArea.setText(sb.toString());
//
//    }

    public static void main(String[] args) {
        SearchForm searchForm=new SearchForm();
        searchForm.setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    

}
