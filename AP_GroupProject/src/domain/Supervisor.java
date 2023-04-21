package domain;

public class Supervisor {
	private String iD;
	private String name;
	private String password;
	
	public Supervisor() {
		super();
		this.iD = "";
		this.name = "";
		this.password = "";
	}
	public Supervisor(String iD, String name, String password) {
		super();
		this.iD = iD;
		this.name = name;
		this.password = password;
	}


	public String getiD() {
		return iD;
	}


	public void setiD(String iD) {
		this.iD = iD;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "ID: " + iD + "\nName: " + name + "Password: " + password;
	}
	
	
	
}
