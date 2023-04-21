package domain;


public class Supervisor {
	
		private String staffID;
	    private String firstName;
	    private String lastName;
	    private String email;
	    private String password;

	    public Supervisor()
	    {
	        this.staffID = "";
	        this.firstName = "";
	        this.lastName = "";
	        this.email = "";
	        this.password = "";
	    }

	    public Supervisor(String staffID, String firstName, String lastName, String email, String password)
	    {
	        this.staffID = staffID;
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.password = password;
	    }


	    public Supervisor(Supervisor Supervisor) {
	        this.firstName = Supervisor.firstName;
	        this.lastName = Supervisor.lastName;
	        this.email = Supervisor.email;
	        this.password = Supervisor.password;
	    }
	    
	    //getters and setters
		public String getStaffID() {
			return staffID;
		}

		public void setStaffID(String staffID) {
			this.staffID = staffID;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
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
		
		  @Override
		    public String toString()
		    {
		        return "Supervisor{" +
		                "staffID: '" + staffID + '\'' + ", firstName: '" + firstName + '\'' +
		                ", lastName: '" + lastName + '\'' + ", email: '" + email + '\'' + 
		                ", password: '" + password + '\'' + '}';
		    }
}