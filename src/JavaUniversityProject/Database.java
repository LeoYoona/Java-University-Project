package JavaUniversityProject;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Database {
	ArrayList<Person> personList;
	public int ids;

	public Database() {
		personList = new ArrayList<Person>();
		createUsers();
	}

	private void createUsers() {
		// list of students
		addStudent("Rabiah", "Ali", "s1@inhmail.com", "s01", ids + 1, LocalDate.of(1996, 05, 8), "IT-02-A");
		addStudent("Salihah", "Alabrash", "s2@inhmail.com", "s02", ids + 2, LocalDate.of(1997, 6, 7), "IT-02-A");
		addStudent("Valerio", "Perra", "s3@inhmail.com", "s03", ids + 3, LocalDate.of(1998, 7, 21), "IT-02-A");
		addStudent("Tiziano", "Maiolo", "s4@inhmail.com", "s04", ids + 4, LocalDate.of(1999, 8, 29), "IT-02-A");
		addStudent("Hyejin", "Ryu", "s5@inhmail.com", "s05", ids + 5, LocalDate.of(2000, 9, 28), "IT-02-A");
		addStudent("Jisung", "Hwa", "s6@inhmail.com", "s06", ids + 6, LocalDate.of(2001, 10, 27), "IT-02-A");

		// list of teachers
		addTeacher("IlSung", "Sam", "t1@inhmail.com", "t01", ids + 7, LocalDate.of(1965, 6, 15), 8000.0);
		addTeacher("Kamatari", "Kawai", "t2@inhmail.com", "t02", ids + 8, LocalDate.of(1975, 7, 5), 5200.0);
		addTeacher("Xionglong", "Liu", "t3@inhmail.com", "t03", ids + 9, LocalDate.of(1985, 3, 6), 2200.0);
		addTeacher("Fayha", "Barbery", "t4@inhmail.com", "t04", ids + 10, LocalDate.of(1995, 1, 4), 6200.0);
		addTeacher("Hassiba", "Zafrani", "t5@inhmail.com", "t05", ids + 11, LocalDate.of(1983, 5, 7), 5800.0);
		addTeacher("Malvika", "Randhawa", "t6@inhmail.com", "t06", ids + 12, LocalDate.of(1985, 10, 25), 9000.0);

		// add managers

		addManager("Kim", "Namjoon", "m1@inhmail.com", "m01", ids + 13, LocalDate.of(1994, 9, 12));
		addManager("Kim", "Seokjin", "m2@inhmail.com", "m02", ids + 14, LocalDate.of(1992, 12, 4));

		updateID(ids + 14);
	}

	public void updateID(int id) {
		ids = id + 1;
	}

	public Person checkUserCredentials(String username, String password) {
		for (Person per : personList) {
			if (per.checkUserCredentials(username, password) == true)
				return per;
		}
		return null;
	}

	public void addManager(String firstName, String lastName, String userName, String password, int id,
			LocalDate birthDate) {
		Manager m = new Manager(firstName, lastName, userName, password, id, birthDate);
		personList.add(m);
	}

	public void addTeacher(String firstName, String lastName, String userName, String password, int id,
			LocalDate birthDate, double salary) {
		Teacher t = new Teacher(firstName, lastName, userName, password, id, birthDate, salary);
		personList.add(t);
	}

	public void addStudent(String firstname, String lastname, String username, String password, int id,
			LocalDate birthdate, String group) {
		Student s = new Student(firstname, lastname, username, password, id, birthdate, group);
		personList.add(s);
	}

	public ArrayList<Teacher> getTeachers() {
		ArrayList<Teacher> teacherList = new ArrayList<Teacher>();
		for (Person u : personList) {
			if (u instanceof Teacher) {
				Teacher t = (Teacher) u;
				teacherList.add(t);
			}
		}
		return teacherList;
	}

	public ArrayList<Student> getStudents() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		for (Person u : personList) {
			if (u instanceof Student) {
				Student s = (Student) u;
				studentList.add(s);
			}
		}
		return studentList;
	}

	
	
	public boolean saveReportAsDoc(int id) {
		boolean tf = false;
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = getStudents();

		for (Student s : studentList) {
			if (s.id == id) {
				//System.out.println();
				String all = s.printIndvidualData();
				printTextInFile(all, s.firstname+"_"+s.lastname+"_report");
				tf = true;
			}
		}
		return tf;
	}
	public void saveAllReportsAsDoc() {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = getStudents();

		for (Student s : studentList) {
			{
				String all ="\n\n"+ (String.format("%15s", "Student report"));
				all += s.printIndvidualData();
				printTextInFile(all, s.firstname+"_"+s.lastname+"_report");
			}
		}
	}
	
	public void printTextInFile(String all, String studentName) {
		try {
			JFileChooser fr = new JFileChooser();
			FileSystemView fw = fr.getFileSystemView();
			String studentDir = fw.getDefaultDirectory().toString() + "\\student_reports";
			Path path = Paths.get(studentDir);
			Files.createDirectories(path);
			
			File file = new File(path.toString() + "\\"+studentName+".doc");
			// Create the file
			file.createNewFile();
			
			// Write Content
			FileWriter writer = new FileWriter(file);
			writer.write(all);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public String getSubjectGrade(int id, String subject) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = getStudents();
		String grade = "";
		for (Student s : studentList) {
			if (s.id == id) {
				switch (subject) {
				case "java":
					grade = Integer.toString(s.getGrade(CourseEnum.JAVA));
					break;
				case "sql":
					grade = Integer.toString(s.getGrade(CourseEnum.SQL));
					break;
				case "csharp":
					grade = Integer.toString(s.getGrade(CourseEnum.CSHARP));
					break;
				case "php":
					grade = Integer.toString(s.getGrade(CourseEnum.PHP));
					break;
				}

			}
		}
		return grade;
	}

	public void editStudentGrade(int id, String subject, int grade) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = getStudents();
		for (Student s : studentList) {
			if (s.id == id) {
				s.editStudentGrade(subject, grade);
			}
		}

	}

}
