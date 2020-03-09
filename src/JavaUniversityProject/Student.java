package JavaUniversityProject;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
	String group;	
	Account account;
	Course courses;
	
	
	//constructor
	public Student(String firstName, String lastNAme,String group) {
		this.firstName = firstName;
		this.lastNAme = lastNAme;
		this.group = group;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public void setAccount(String username, String password)
	{
		account = new Account(username, password);		
	}
	
	
	
	int abc = CourseEnum.JAVA.getCode();

}
