package JavaUniversityProject;
import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
	String group;	
	Account account;
	List<CourseEnum> course;
	
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
	
	public List<CourseEnum> getCourseEnum()
	{
		course = new ArrayList<CourseEnum>();
		course.add(CourseEnum.JAVA);
		course.add(CourseEnum.PHP);
		course.add(CourseEnum.SQL);
		course.add(CourseEnum.CSHARP);
		return course;
	}

}
