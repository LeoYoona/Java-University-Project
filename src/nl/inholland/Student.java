package nl.inholland;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

@SuppressWarnings("unused")
public class Student extends Person {
	String group;
	Course course;
	int retakes;
	String result;
	CourseEnum courseName;
	Hashtable<CourseEnum, Integer> gradedCourses;

	// constructor creates a students with its details and a hash-table of courses
	// with their grades
	public Student(String firstname, String lastname, String username, String password, int id, LocalDate birthdate,
			String group) {
		super(firstname, lastname, username, password, id, birthdate, UserAccessLevel.Basic);
		this.group = group;
		this.course = new Course();
		this.gradedCourses = course.gradedCourses;
	}

	public int getGrade(CourseEnum specificCourse) {
		Integer v = this.gradedCourses.get(specificCourse);
		if (v == null) {
			System.err.println("error! the student has not been graded for this course!");
			v = 0;
		}
		return v;
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

	public void getResultRetakes() {
		this.result = "Passed";
		this.retakes = 0;
		if (getGrade(CourseEnum.JAVA) < 55) {
			retakes++;
		}
		if (getGrade(CourseEnum.CSHARP) < 55) {
			retakes++;
		}
		if (getGrade(CourseEnum.SQL) < 55) {
			retakes++;
		}
		if (getGrade(CourseEnum.PHP) < 55) {
			retakes++;
		}

		if (retakes > 0) {
			result = "Not passed";
		}
	}

	public String printIndvidualData() {
		String reportText = "";

		reportText += "\nReport of student " + this.firstname + " " + this.lastname;
		reportText += "\n";
		reportText += (pad("\nStudent ID", Integer.toString(this.id)));
		reportText += (pad("\nFirst Name", this.firstname));
		reportText += (pad("\nLast Name", this.lastname));
		reportText += (pad("\nAge", Integer.toString(this.getAge())));

		reportText += "\n";

		reportText += (pad("\nJava", Integer.toString(getGrade(CourseEnum.JAVA))));
		reportText += (pad("\nC Sharp", Integer.toString(getGrade(CourseEnum.CSHARP))));
		reportText += (pad("\nSQL", Integer.toString(getGrade(CourseEnum.SQL))));
		reportText += (pad("\nPHP", Integer.toString(getGrade(CourseEnum.PHP))));

		reportText += "\n";

		reportText += (String.format("%15s", "Results"));
		reportText += "\n";

		
		getResultRetakes();

		reportText += (pad("Result", result));
		reportText += "\n";
		reportText += (pad("Retakes", Integer.toString(retakes)));
		
		return reportText;
	}

	private String pad(String sub, String grade) {
		return String.format("%-15s %15s", sub, grade);
	}

}
