package view;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainMenu extends JFrame{

	private static final long serialVersionUID = 1L;
	private Container container = getContentPane();
	private JLabel lblName = new JLabel("Please select one of the options below");
	private JLabel heading = new JLabel("Welcome to the Main Menu");
	private JButton button1 = new JButton("Login");
	private JButton button2 = new JButton("Register"); 
	
	public MainMenu() {
		
		
		setBounds(350, 190, 500, 520);
		setVisible(true);
		setTitle("Main Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        container.setLayout(null);

		button1.setBounds(50, 300, 90, 30);
		button2.setBounds(225, 300, 90, 30);
        lblName.setBounds(50, 200, 250, 30);
        heading.setBounds(150, 90, 350, 30);
	
		container.add(button1);
		container.add(button2);
        container.add(lblName);
        container.add(heading);
	
	button1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()== button1) {
				
				try {
					LoginPage login = new LoginPage();
		        	login.setVisible(true);
		        	login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        	
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		});
	
	button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()== button2) {
					
					try {
						MainRegistration main = new MainRegistration();
			        	main.setVisible(true);
			        	main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        	
					}catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			});
			
		}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	MainMenu frame = new MainMenu();
                	frame.setTitle("University of Technology Main Menu");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }); 
		

	}

}
