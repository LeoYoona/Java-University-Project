package JavaUniversityProject;

public class Account {

	public String username, password;
	
	public Account(String username, String password)
	{
		setAccount(username, password);
	}
	
	private void setAccount(String username, String password)
	{
		this.username = username;
		this.password= password;
	}
}
