package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private String search,id;
    private String[] data=new String[10];
    private String[] columnNames={"Reference Number","Id Number", "First Name", "Last Name", "Email", "Contact Number", "Issue Type","Issue", "Issue Details", "Responses"};
    public DBClient db;
    public Student student=new Student();
    private Object[][] allData=new Object[10][10];
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    
    

    public SearchForm()
    {
    	this.db= new DBClient();
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
        model =new DefaultTableModel(allData, columnNames);
        table =new JTable(model);
        scrollPane.add(table);
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

        searchButton.addActionListener(this);
        
        
        add(searchField);
        add(idSearchField);
        add(searchButton);
        add(scrollPane);
        
        //add(resultsArea);
        

        setBounds(250,30,781, 650);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);

    }
//    private void displayTable(Student[] data) {
//        StringBuilder sb = new StringBuilder();
//
////        for (Student cell : data) {
////            sb.append(cell).append(" ");
////        }
////        sb.append("\n");
//
//        resultsArea.setText(sb.toString());

    //}

    public static void main(String[] args) {
        SearchForm searchForm=new SearchForm();
        searchForm.setVisible(true);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource()==searchButton) 
		{
			search = searchField.getText();
	        id= idSearchField.getText();
			student = db.findIssue(search,id);
			
			
	        data[0]=student.getRefNumber();
	        data[1]=student.getIdNumber();
	        data[2]=student.getFirstName();
	        data[3]=student.getLastName();
	        data[4]=student.getEmail();
	        data[5]=""+student.getContactNumber();
	        data[6]=student.getIssueType();
	        data[7]=student.getIssue();
	        data[8]=student.getIssueDetails();
	        data[9]=student.getResponses();
	        
	       
	        
	        
	        
	     // Repeat the one-dimensional array in the two-dimensional array
	        for (int i = 0; i < allData.length; i++)
	        {
	        	for(int j=0; j<data.length; j++) 
				{
		   
		        	allData[i][j]=data[j % data.length];
		        	table.setValueAt(allData, i, j);
	
				}
	            
	        }
	        System.out.println(""+ allData[0][0]);
	        
	        
		}
	        
         
			
	}
}
