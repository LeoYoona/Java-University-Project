package JavaUniversityProject;
import java.time.LocalDate;
import java.util.ArrayList;

public class Database {
	ArrayList<Person> personList;

	public Database() {
		personList = new ArrayList<Person>();
		createUsers();
	}

	private void createUsers() {
		// list of students
		addStudent("Rabiah", "Ali", "s1", "s01", 1, LocalDate.of(1996, 05, 8), "IT-02-A");
		addStudent("Salihah", "Alabrash", "s2", "s02", 2, LocalDate.of(1997, 6, 7), "IT-02-A");
		addStudent("Valerio", "Perra", "s3", "s03", 3, LocalDate.of(1998, 7, 21), "IT-02-A");
		addStudent("Tiziano", "Maiolo", "s4", "s04", 4, LocalDate.of(1999, 8, 29), "IT-02-A");
		addStudent("Hyejin", "Ryu", "s5", "s05", 5, LocalDate.of(2000, 9, 28), "IT-02-A");
		addStudent("Jisung", "Hwa", "s6", "s06", 6, LocalDate.of(2001, 10, 27), "IT-02-A");
		
		//list of teachers 
		addTeacher("IlSung", "Sam", "t1", "t01", 7, LocalDate.of(1965, 6, 15), 8000.0);
		addTeacher("Kamatari", "Kawai", "t2", "t02", 8, LocalDate.of(1975, 7, 5), 5200.0);
		addTeacher("Xionglong", "Liu", "t3", "t03", 9, LocalDate.of(1985, 3, 6), 2200.0);
		addTeacher("Fayha", "Barbery", "t4", "t04", 10, LocalDate.of(1995, 1, 4), 6200.0);
		addTeacher("Hassiba", "Zafrani", "t5", "t05", 11, LocalDate.of(1983, 5, 7), 5800.0);
		addTeacher("Malvika", "Randhawa", "t6", "t06", 12, LocalDate.of(1985, 10, 25), 9000.0);

	}

	public Person checkUserCredentials(String username, String password) 
	{
		for(Person per : personList) {
			if(per.checkUserCredentials(username, password) == true)
				return per;
			}
		return null;
	}
	
	private void addTeacher(String firstName, String lastName, String userName, String password, int id, LocalDate birthDate, double salary) {
		Teacher t = new Teacher(firstName, lastName,userName, password, id,  birthDate, salary);
		personList.add(t);
	}
	
	private void addStudent(String firstname, String lastname, String username, String password, int id,LocalDate birthdate, String group) 
	{
		Student s = new Student(firstname, lastname, username, password,id ,birthdate,group);
		personList.add(s);
	}

	public ArrayList<Teacher> getTeachers(){
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		for(Person u : personList) {
			if(u instanceof Teacher) {
				Teacher t = (Teacher) u;
				teacherList.add(t);
			}
		}
		return teacherList;
	}
	
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



