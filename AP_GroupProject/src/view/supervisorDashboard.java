package view;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class supervisorDashboard extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTable table;
	private JList<?> complaintsList;
	private DefaultTableModel model;
	private JScrollPane tableScrollPane, complaintScrollPane;
	private JPanel panelTop, panLeft, panMiddle, panRIght, panelBottom;
	private JTextField refNumText, advisorText;
	private JButton addBtn;

	public supervisorDashboard() {
		setLayout(new GridLayout(2,1));
		
		this.initializeComponents();
		this.addComponentsToPanels();
		this.addPanelsToWindow();
		this.setWindowProperties();
		this.registeredListeners();
	}
	
	private void initializeComponents() {
		label = new JLabel("SUPERVISOR DASHBOARD");
		String[] complaints = { "Missing Lectures", "Financial Clearance", "Can't Login",
        "Can't Login"};//querry from database
 
		complaintsList = new JList<>(complaints);
		complaintsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		complaintScrollPane = new JScrollPane(complaintsList);
		
		//table
		String[] columnNames = { "Student ID", "First Name", "Last Name", "Issue Type", "Issue", "Details" };
		model = new DefaultTableModel(null, columnNames);//replace null with dbquery that returns Object[][] data
		table = new JTable(model);
		tableScrollPane = new JScrollPane(table);
        tableScrollPane.setPreferredSize(new Dimension(950, 250));
        
        refNumText = new JTextField();
        refNumText.setPreferredSize(new Dimension(200, 50));
        advisorText = new JTextField();
        advisorText.setPreferredSize(new Dimension(200, 50));
        
        addBtn= new JButton("Add");
		
		panelTop = new JPanel(new GridLayout(1, 3));
		panLeft = new JPanel(new GridBagLayout());
		panMiddle = new JPanel();
		panRIght = new JPanel();
		panelBottom = new JPanel();
		
	}

	private void addComponentsToPanels() {
		panLeft.add(complaintScrollPane);
		
		panMiddle.add(label);
		
		
		panRIght.add(new JLabel("Advisor ID #"));
		panRIght.add(advisorText);
		panRIght.add(new JLabel("Complaint Ref #"));
		panRIght.add(refNumText);
		panRIght.add(addBtn);
		
		panelTop.add(panLeft);
		panelTop.add(panMiddle);
		panelTop.add(panRIght);
		
		panelBottom.add(tableScrollPane);
		
	}

	private void addPanelsToWindow() {
		this.add(panelTop);
		this.add(panelBottom);
		
	}

	private void setWindowProperties() {
		this.setSize(1000, 680);
		this.setTitle("Student Services Advisor ");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
	}

	private void registeredListeners() {
		addBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==addBtn){
			String refNum = refNumText.getText().trim();
			String advisorId= advisorText.getText().trim();
			//place both into a query to add advisor to speicfic num
		}
		
	}

	public static void main(String[] args) {
		new supervisorDashboard();
	}

}
