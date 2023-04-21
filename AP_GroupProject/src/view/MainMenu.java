package view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private Container container = getContentPane();
	private JLabel Text = new JLabel("Please select one of the option");
	private JComboBox<String> combobox;
	
	public MainMenu() {
		
//		setLocationAndSize();
//		addComponentsToContainer();
		
		this.setBounds(30, 50, 499, 600);
		this.setVisible(true);
		this.setTitle("Main Menu");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new JFrame("University Main Menu");
		//Create a String array of ComboBox list items
		String options[] = {"Register", "Login"};
		 combobox = new JComboBox<>(options);
		//Set one of the options (Register) as selected
		combobox.setSelectedIndex(0);
		//Indicate where to position the component on the frame
		combobox.setBounds(100, 50, 150, 30);
		//adding action listener to combobox
		combobox.addActionListener(this);
		//Add the ComboBox to the frame
		this.add(combobox);
		this.setLayout(null);
		this.setSize(400, 500);
		this.setVisible(true);
	
		this.add(Text);
		this.setLayout(null);
		this.setSize(100, 300);
		this.setVisible(true);
	}
	
	public void setLayoutManager() {
        container.setLayout(null);
    }

	//only shows the login form
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==combobox) {
			
			try {
				
				System.out.println(combobox.getSelectedItem());   //outputs selected item in console
				LoginPage login = new LoginPage();
	        	login.setVisible(true);
	        	login.setTitle("University Login");
	        	login.setBounds(30, 50, 499, 600);
	        	login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        	login.setResizable(false);
	        	//add logger here
	        	
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}else {
			if (e.getSource() == combobox) {  
				try {
					System.out.println(combobox.getSelectedItem());
					MainRegistration main = new MainRegistration();
		        	main.setVisible(true);
		        	main.setTitle("University Login");
		        	main.setBounds(450, 190, 1014, 597);
		        	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	main.setResizable(false);
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	
	public static void main(String[] args) {
		new MainMenu();

	}

}
