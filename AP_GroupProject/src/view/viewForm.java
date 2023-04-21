package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class viewForm extends JFrame implements ActionListener{
	  
	private static final long serialVersionUID = 1L;

	public viewForm() 
	{
		setBounds(250,30,781, 650);
        setLayout(null);
        setResizable(false);
        setUndecorated(true);
		
	}

	public static void main(String[] args) {
		viewForm view=new viewForm(); 
		view.setVisible(true);
		}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
