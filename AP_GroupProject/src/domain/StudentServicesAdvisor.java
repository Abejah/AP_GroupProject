package domain;

public class StudentServicesAdvisor {
		private String id;
		private String fname;
		private String lname;
		private String email;
		private String password;
		private String username;
		
		public StudentServicesAdvisor() {
			this.id = "";
			this.fname = "";
			this.lname = "";
			this.email = "";
			this.password = "";
			this.username = "";
		}

		public StudentServicesAdvisor(String id, String fname, String lname, String email, String password, String username) {
			this.id = id;
			this.fname = fname;
			this.lname = lname;
			this.email = email;
			this.password = password;
			this.username = username;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@Override
		public String toString() {
			return "StudentServicesAdvisor \nID: " + id + "\nNname: " + fname + " " + lname + "\nEmail: " + email
					+"\n Username"+ username + "\tPassword: " + password;
		}
		
}