package JavaUniversityProject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class Student extends Person {
	String group;	
	Course course;	
	CourseEnum courseName;	
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
	
	public void editStudentGrade(String subject, int grade) {
		switch (subject) {
		  case "java":
			  gradedCourses.put(CourseEnum.JAVA, grade);
		    break;
		  case "sql":
			  gradedCourses.put(CourseEnum.SQL, grade);
			  break;
		  case "csharp":
			  gradedCourses.put(CourseEnum.CSHARP, grade);
			  break;
		  case "php":
			  gradedCourses.put(CourseEnum.PHP, grade);
			  break;
		}
	}
	
	public String printIndvidualData(boolean onConsole) {
		String reportText="";
		
		reportText+="\nReport of student "+ this.firstname +" "+this.lastname;
		reportText+="\n";
		reportText+=(pad("\nStudent ID", Integer.toString(this.id)));
		reportText+=(pad("\nFirst Name", this.firstname));
		reportText+=(pad("\nLast Name", this.lastname));
		reportText+=(pad("\nAge", Integer.toString(this.getAge()) ));
		
		reportText+="\n";
		
		reportText+=(pad("\nJava", Integer.toString(getGrade(CourseEnum.JAVA)) ));
		reportText+=(pad("\nC Sharp", Integer.toString(getGrade(CourseEnum.CSHARP))));
		reportText+=(pad("\nSQL", Integer.toString(getGrade(CourseEnum.SQL)) ));
		reportText+=(pad("\nPHP", Integer.toString(getGrade(CourseEnum.PHP)) ));
	
		reportText+="\n";
		
		reportText+=(String.format("%15s", "Results"));
		reportText+="\n";		
		
		String result = "Passed";
		int retakes =0;
		if(getGrade(CourseEnum.JAVA)<55)
		{
			retakes++;
		}
		if(getGrade(CourseEnum.CSHARP)<55)
		{
			retakes++;
		}
		if(getGrade(CourseEnum.SQL)<55)
		{
			retakes++;
		}
		if(getGrade(CourseEnum.PHP) <55)
		{
			retakes++;
		}
		
		if(retakes > 0)
		{
			result="Not passed";
		}
		
		reportText+=(pad("Result", result));
		reportText+="\n";
		reportText+=(pad("Retakes", Integer.toString(retakes) ));
		if(onConsole)
		{
			System.out.println(reportText);
		}
		
		return reportText;
	}
	
	private String pad(String sub, String grade) {
		return String.format("%-15s %15s",sub,grade  );
	}
	
	private String pad(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	
	private String pad(int arg, int n) {
		return String.format("%1$-" + n + "s", arg);
	}
	
}
