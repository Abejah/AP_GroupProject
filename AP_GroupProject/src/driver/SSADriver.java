package driver;

import view.SSADashboard;

public class SSADriver {
	
	public static void main(String[] args) {
		SSADashboard client = new SSADashboard();
		try {
			client.sSA.setUsername("Mary");//get from authentication
			client.setVisible(true);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}