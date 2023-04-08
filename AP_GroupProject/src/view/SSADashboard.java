package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import client.ChatClient;
import domain.StudentServicesAdvisor;

public class SSADashboard extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JPanel panTop,panLeft,panBottom,panRight,panCentre,panRightTop,panRightBottom;
	private JTextArea studentInfoTA, complaintsTA;

	public JTextArea chatTA;
	private JButton viewBtn, sendBtn, logoutBtn, responseBtn;
	private JTextField responseTxt;
	private JCheckBox availableCheckBox;
	private JList<String> complaintsList;
	public StudentServicesAdvisor sSA;
	 
	 
	 public SSADashboard() {
		setLayout(new GridLayout(2,1));
		this.sSA = new StudentServicesAdvisor();
		
		this.initializeComponents();
		this.addComponentsToPanels();
		this.addPanelsToWindow();
		this.setWindowProperties();
		this.registeredListeners();
	}

	 private void initializeComponents(){
		 String[] complaints = { "I cannot login to my account", "I cannot access my course materials", "I cannot enroll in a course",
		        "I cannot submit an assignment", "I cannot view my grades", "I am having trouble with a quiz",
		        "I need to reset my password" };
		 
		  complaintsList = new JList<>(complaints);
		  complaintsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  
		 //buttons
		 viewBtn = new JButton("View");
		 
		 sendBtn = new JButton("Send");
		 sendBtn.setEnabled(false);
		 
		 responseBtn = new JButton("Response");
		 logoutBtn = new JButton("Logout");
		 
		 //checkbox
		 availableCheckBox = new JCheckBox("I'm available for live chat");
		 
		 //textFields
		 responseTxt = new JTextField(20);
		 responseTxt.setPreferredSize(new Dimension(150, 50));
		 responseTxt.setEditable(false);
		 
		 //textAreas
		 studentInfoTA = new JTextArea("Student Information");
		 studentInfoTA.setEditable(false);
		 
		 complaintsTA = new JTextArea();
		 complaintsTA.setPreferredSize(new Dimension(220, 250));
		 complaintsTA.setEditable(false);
		 
		 chatTA = new JTextArea();
		 chatTA.setPreferredSize(new Dimension(250, 250));
		 chatTA.setEditable(false);
		 
		 //Main panels
		 panTop=new JPanel(new GridLayout(1,3));
		 panBottom = new JPanel(/*new GridLayout(1,3)*/);
		 
		 //Inner Panels
		 panLeft = new JPanel();
		 panLeft.setPreferredSize(new Dimension(250, 300));
		 panLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		 
		 panCentre = new JPanel(new GridLayout());
		 panCentre.setBorder(BorderFactory.createLineBorder(Color.black));
		 
		 panRight = new JPanel();
		 panRight.setPreferredSize(new Dimension(250, 300));
		 panRight.setBorder(BorderFactory.createLineBorder(Color.black));
		 
		 //Right Panels inner panels
		 panRightTop = new JPanel();
		 panRightTop.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		 panRightTop.setPreferredSize(new Dimension(250, 245));
		 
		 panRightBottom = new JPanel();
		 //panRightBottom.setPreferredSize(new Dimension(200,50));
		 
		
	 }
	 
	 private void addComponentsToPanels() {
		 panRightTop.add(new JLabel("Live Chat using threading"));
		 panRightTop.add(chatTA, BorderLayout.CENTER);
		 
		 panRightBottom.add(responseTxt);
		 panRightBottom.add(sendBtn);
		 
		 panRight.add(panRightTop);
		 panRight.add(panRightBottom, BorderLayout.SOUTH);
		 
		 panCentre.add(studentInfoTA);
		 
		 panLeft.add(new JLabel("Complaints from complaints table"));
		 panLeft.add(complaintsTA.add(complaintsList));
		 panLeft.add(viewBtn, BorderLayout.SOUTH);
		 panLeft.add(responseBtn, BorderLayout.SOUTH);
		 
		 panTop.add(panLeft);
		 panTop.add(panCentre);
		 panTop.add(panRight);
		 
		 panBottom.add(availableCheckBox);
		 panBottom.add(logoutBtn);
	 }
	 
	 private void addPanelsToWindow() {
			this.add(panTop, BorderLayout.NORTH);
			this.add(panBottom, BorderLayout.SOUTH);
	}


	private void setWindowProperties() {
		this.setSize(1000, 680);
		this.setTitle("Student Services Advisor "+sSA.getUsername());
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
	}
	String message = "";
	private void registeredListeners() {
		sendBtn.addActionListener(this);
		viewBtn.addActionListener(this);
		logoutBtn.addActionListener(this);
		responseBtn.addActionListener(this);
		sendBtn.addActionListener(this);
		availableCheckBox.addActionListener(this);
		availableCheckBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (availableCheckBox.isSelected()) {
					ChatClient client = new ChatClient(sSA.getUsername());// = new Client(username);***Client Connection***
				    System.out.println("Availability set to: available");
				    sendBtn.setEnabled(true);
				    responseTxt.setEditable(true);
				    				    
				    sendBtn.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if (e.getSource() == sendBtn) {
								   if (!responseTxt.getText().isBlank()) {
									   String response = responseTxt.getText();
									   //("Me: " + responseTxt.getText() + "\n");
						               client.sendMessage(sSA.getUsername(),response);// send message to client 
						            }
						            responseTxt.setText("");
								}//end of if
							message = client.listenForMessage();
						}
					});//end of send button action listener
				}
			}
		});//end of action listener for the check box
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == viewBtn) {
            // get selected complaint from database query
            //String selectedComplaint = (String) complaintsList.getSelectedValue();
            
            // get student details for selected complaint from database
			//**********Dummy values below********
            int studentId = 12345;
            String firstName = "John";
            String lastName = "Doe";
            String email = "johndoe@example.com";
            String contact = "555-1234";
            String typeOfIssue = "Login Issue";
            String detailsOfIssue = "I forgot my password and cannot reset it.";
            
            // display student details
            studentInfoTA.setText("ID: " + studentId + "\nFirst Name: " + firstName + "\nLast Name: " + lastName
                    + "\nEmail: " + email + "\nContact: " + contact + "\nType of Issue: " + typeOfIssue + "\nDetails of Issue: "
                    + detailsOfIssue);
		}
		if (e.getSource() == responseBtn) {
            // get selected complaint
            //String selectedComplaint = (String) complaintsList.getSelectedValue();
            // get response text
            //String responseText = responseTextArea.getText();
            String responseText = JOptionPane.showInputDialog(this, "Enter your response:");
            if (responseText.equals(null) || responseText.equals("")) {
            	responseText = JOptionPane.showInputDialog(this, "Invalid response\nEnter your response:");
            	// send response to student
                JOptionPane.showMessageDialog(null, "Your response has been sent to the student:\n" + responseText);
            }else {
            	// send response to student
                JOptionPane.showMessageDialog(null, "Your response has been sent to the student:\n" + responseText);
            }
            
            // clear response text area
            studentInfoTA.setText("");
		}
		if(availableCheckBox.isSelected()) {
			if (!message.isBlank() || !message.isEmpty() || !message.equals(null)) {
		    	chatTA.append(message+"\n");
		    }
		}
		if (!availableCheckBox.isSelected()) {
	        sendBtn.setEnabled(false);
	        responseTxt.setEditable(false);
	        responseTxt.setText("");
	        System.out.println("\nDisconnected from Server \nAvailability set to: unavailable");
	   }
       if (e.getSource() == logoutBtn) {
		   JOptionPane.showMessageDialog(null, "Thanks Come again \n Return to authentication");
		   //should actually call for authentication;
	   }

	}
}