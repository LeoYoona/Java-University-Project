package JavaUniversityProject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class Student extends Person {
	String group;	
	Course course;	
	Hashtable<CourseEnum, Integer> gradedCourses;
	
	//constructor creates a students with its details and a hash-table of courses with their grades
	public Student(String firstname, String lastname,String username, String password, int id,LocalDate birthdate,String group) {
		super(firstname, lastname, username, password, id, birthdate, UserAccessLevel.Basic);
		this.group = group;
		this.course = new Course();
		this.gradedCourses= course.gradedCourses;
	}
	//////idk
	
	public int getGrade(CourseEnum specificCourse) {
		Integer v = this.gradedCourses.get(specificCourse);
		if (v == null) {
			System.err.println("error! the student has not been graded for this course!");
			v = 0;
		}
		return v;
	}
	
	public void print() {
		printData();
		System.out.println();
	}
	
	public void printReport() {
		// This prints the info of the student, their courses, and results 
		printData();
		printGrades();
		System.out.println();
		
	}
	
	private void printGrades() {
		System.out.print(pad(getGrade(CourseEnum.JAVA), 8) + pad(getGrade(CourseEnum.CSHARP), 8) + 
		pad(getGrade(CourseEnum.SQL), 8) + pad(getGrade(CourseEnum.PHP), 8));
	}
	
	
	private void printData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String birthdateFormat = birthdate.format(formatter);
		System.out.print(pad(id,5) + pad(firstname, 15) + pad(lastname, 15) +
				pad(birthdateFormat, 15) + pad(getAge(), 8) + pad(group, 15));
	}
	
	private String pad(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	
	private String pad(int arg, int n) {
		return String.format("%1$-" + n + "s", arg);
	}
	
}
