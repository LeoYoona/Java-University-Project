package JavaUniversityProject;

import java.time.LocalDate;
import java.time.Period;

public abstract class Person {
	protected int id;
	protected String firstname; 
	protected String lastname; 
	protected LocalDate birthdate;
	protected UserAccessLevel accesslvl = UserAccessLevel.Basic;
	public Account account;
	
	public Person(String firstname, String lastname,String username, String password, int id,LocalDate birthdate, UserAccessLevel accesslvl) {
		this.id = id;
		this.firstname = firstname; 
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.accesslvl = accesslvl;
		account = new Account(username,password);
	}
	
	public Boolean checkUserCredentials(String username, String password) 
	{
		if (account.username.equals(username) && account.password.equals(password))
		{
				return true;
		}
		return false;
	}
	

	public void setAccessLevel(UserAccessLevel accesslvl) {
		this.accesslvl = accesslvl;
	}
	
	public int getAge() {
		int age = 0;
		LocalDate now = LocalDate.now();
		Period diff = Period.between(birthdate, now);
		age = diff.getYears();
		return age;
		
	}
}
