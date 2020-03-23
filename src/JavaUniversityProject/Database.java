package JavaUniversityProject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
	ArrayList<Person> personList;
	public Person loggedInUser = null;

	public Database() {
		personList = new ArrayList<Person>();
		createUsers();
	}

	private void createUsers() {
		// list of students
		addStudent("Rabiah", "Ali", "s1", "s01", 5, LocalDate.of(1996, 05, 8), "IT-02-A");
		addStudent("Salihah", "Alabrash", "s2", "s02", 5, LocalDate.of(1997, 6, 7), "IT-02-A");
		addStudent("Valerio", "Perra", "s3", "s03", 5, LocalDate.of(1998, 7, 21), "IT-02-A");
		addStudent("Tiziano", "Maiolo", "s4", "s04", 5, LocalDate.of(1999, 8, 29), "IT-02-A");
		addStudent("Hyejin", "Ryu", "s5", "s05", 5, LocalDate.of(2000, 9, 28), "IT-02-A");
		addStudent("Jisung", "Hwa", "s6", "s06", 5, LocalDate.of(2001, 10, 27), "IT-02-A");
		
		//list of teachers 


	}

	private void addStudent(String firstname, String lastname, String username, String password, int id,LocalDate birthdate, String group) 
	{
		Student s = new Student(firstname, lastname, username, password,id ,birthdate,group);
		personList.add(s);
	}

	public Person checkUserCredentials(String username, String password) 
	{
		for(Person per : personList) {
			if(per.checkUserCredentials(username, password) == true)
				return per;
			}
		return null;
	}
	
	// Returns all students from the database
	public ArrayList<Student> getStudents(){
		ArrayList<Student> studentList = new ArrayList<Student>();
		for(Person u : personList) {
			if (u instanceof Student) {
				Student s = (Student) u;
				studentList.add(s);
			}
		}
		return studentList;
	}


}



