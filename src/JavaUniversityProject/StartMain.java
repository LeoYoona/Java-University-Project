package JavaUniversityProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;


public class StartMain {

	Database db;
	Person loggedInUser;

	public static void main(String[] args) {

		StartMain myProgram = new StartMain();
		myProgram.Start();

	}

	@SuppressWarnings("resource")
	void Start() {
		/* ecplise short cuts. 
		 * ctrl+shift+/ to comment
		 * ctrl+sift+f to indent code 
		 * ctrl+alt+down-arrow to copy seleted code to the next line/down		 * 
		 */
		db = new Database();
		Scanner input = new Scanner(System.in);
		String username, password;
		Person loggedInUser = null;

		while (loggedInUser == null) {
			System.out.println("Enter username: ");
			username = input.nextLine();

			System.out.println("Enter password: ");
			password = input.nextLine();

			loggedInUser = db.checkUserCredentials(username, password);

			if (loggedInUser != null) {
				System.out.println("Successfully logged in!");

				while (true) {
					String choice = showMenu(loggedInUser);
					Boolean conti = processCommand(choice, loggedInUser);
					if (conti == false) {
						break;
					}
				}

			} else {
				System.out.println("Username or password is incorrect! Try again!");
			}
		}
	}

	// this prints the menu depending on the accesslvl of the user
	// and also returns a choice of the user
	public String showMenu(Person loggedInUser) {
		if (loggedInUser.accesslvl == UserAccessLevel.Basic) {
			System.out.println(String.format("\nS. Display Students   |") + String.format("   T. Display Teachers   |")
					+ String.format("   X. Exit"));
		}

		else if (loggedInUser.accesslvl == UserAccessLevel.Editor) {
			System.out.println(String.format("\nS. Display Students   |") + String.format("   T. Display Teachers   |")
					+ String.format("   A. Add Students   |") + String.format("   R. Display Reports   |")
					+ String.format("   X. Exit"));
		} else {
			System.out.println(String.format("\nS. Display Students   |") + String.format("   T. Display Teachers   |")
					+ String.format("   A. Add Students   |") + String.format("   R. Display Reports   |")
					+ String.format("   V. Save Reports   |") + String.format("   X. Exit"));
		}
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		System.out.println("Enter choice: ");
		String choice = input.nextLine();
		return choice.toUpperCase();
	}

	// this will show the output according to user's choice
	@SuppressWarnings("resource")
	public Boolean processCommand(String choice, Person loggedInUser) {
		if (choice.equals("S")) {
			printStudents(false);
		}		
		else if (choice.equals("T")) {
			printTeachers();
		} 
		else if (choice.equals("R") && (loggedInUser.accesslvl == UserAccessLevel.Editor) ) {
			
				printStudents(true);
		}
		else if (choice.equals("A") && (loggedInUser.accesslvl == UserAccessLevel.Editor) ) {
			System.out.println("ADD A STUDENT\n");	
			
			System.out.print("Choose a username: ");
			Scanner input = new Scanner(System.in);
			String username = input.nextLine();

			System.out.print("Choose a password: ");
			Scanner input2 = new Scanner(System.in);
			String password = input2.nextLine();

			System.out.print("Enter first name: ");
			Scanner input3 = new Scanner(System.in);
			String firstname = input3.nextLine();

			System.out.print("Enter last name: ");
			Scanner input4 = new Scanner(System.in);
			String lastname = input4.nextLine();
			
			String birthdate;
			while (true)
			{
				System.out.print("Enter birth date in format DD/MM/YYYY: ");
				Scanner input5 = new Scanner(System.in);
				birthdate = input5.nextLine();
				Pattern DATE_PATTERN = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d$");
				if((DATE_PATTERN.matcher(birthdate).matches()))
				{
					break;
				}
				else {
					System.out.println("Date format is incorrect! Try again!");
				}
			}
		    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		    //String text = date.format(formatters);
		    LocalDate parsedBirthDate = LocalDate.parse(birthdate, formatters);
			
			System.out.print("Enter Group: ");
			Scanner input6 = new Scanner(System.in);
			String group = input6.nextLine();
			
			int id = db.ids;	
			db.updateID(id);
			
			db.addStudent(firstname, lastname, username, password, id,parsedBirthDate , group);
		} 
		
		else if (choice.equals("X")) {
			System.out.println("Leaving the program now...");
			return false;
		} 
		
		else {
			System.out.println("Wrong option chosen! Please try again.\n");
		}
		
		return true;
	}

	@SuppressWarnings("resource")
	public void printStudents(boolean includeGrades) {

		System.out.print(String.format("%1$-5s", "Id") + String.format("%1$-15s", "FirstName")
				+ String.format("%1$-15s", "LastName") + String.format("%1$-15s", "BirthDate")
				+ String.format("%1$-8s", "Age") + String.format("%1$-15s", "Group"));

		if (includeGrades) {
			System.out.print(String.format("%1$-8s", "Java") + String.format("%1$-8s", "Csharp")
					+ String.format("%1$-8s", "SQL") + String.format("%1$-8s", "PHP"));
			
		}

		System.out.println();

		ArrayList<Student> students = db.getStudents();

		for (Student s : students) {
			if (includeGrades) {
				s.printReport();				
			} else {
				s.print();
			}

		}
		
		while(includeGrades==true)
		{
			System.out.println("\nEnter student ID (Report Deatils) | type 0 to go backto main menu: ");
			Scanner input = new Scanner(System.in);
			int choice=0;
			try {
				choice= input.nextInt();
			}
			catch(InputMismatchException e)
			{
				System.out.println("\nFatal Error! Input is not an Integer.\n");
				break;
			}
			
			
			boolean idExists = true;
			if((choice) == 0 )
			{
				break;
			}
			else 
			{
				idExists= db.printIndividualStudentReport((choice));
				reportEdit(db, choice); //choice is student id
				
				if(!(idExists))
				{
					System.out.println("Invalid ID! Please try again.\n");
				}
			}
		}
	}
	
	
	@SuppressWarnings({ "resource", "unused" })
	public void reportEdit(Database db, int studentId) {
		while (true) {
			System.out.println(
					"A. Add (Update) Student Report | R.Display Report | B. Back to Main Report |  X. Exit | \n"
							+ "Please enter a choice: ");
			Scanner input = new Scanner(System.in);
			String choice;
			try {
				choice = input.nextLine();
				choice = choice.toUpperCase();
			} catch (InputMismatchException e) {
				System.out.println("\nFatal Error! Input is not an Integer.\n");
				break;
			}

			if (choice.equals("A")) {
				Scanner yninput = new Scanner(System.in);
				String yn = "";
				do {
					
					System.out.println("Choose the subject for which you want to edit the grade");
					System.out.println("1 for JAVA\n2 for C Sharp\n3 for SQL \n4 for PHP");
					Scanner in = new Scanner(System.in);
					int ins = 0;
					try {
						ins = in.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nFatal Error! Input mismatch");
						System.exit(0);
					}

					if (ins == 1) {
						System.out.println("Current grade for Java is " + db.getSubjectGrade(studentId, "java")
								+ "\nEnter new grade for Java: ");
						Scanner input2 = new Scanner(System.in);
						int g2 = input2.nextInt();
						db.editStudentGrade(studentId, "java", g2);
					} else if (ins == 2) {
						System.out.println("Current grade for C Sharp is " + db.getSubjectGrade(studentId, "csharp")
								+ "\nEnter new grade for C Sharp: ");
						Scanner input3 = new Scanner(System.in);
						int g3 = input3.nextInt();
						db.editStudentGrade(studentId, "csharp", g3);
					} else if (ins == 3) {
						System.out.println("Current grade for SQL is " + db.getSubjectGrade(studentId, "sql")
								+ "\nEnter new grade for SQL: ");
						Scanner input4 = new Scanner(System.in);
						int g4 = input4.nextInt();
						db.editStudentGrade(studentId, "sql", g4);
					} else if (ins == 4) {
						System.out.println("Current grade for PHP is " + db.getSubjectGrade(studentId, "php")
								+ "\nEnter new grade for PHP: ");
						Scanner input5 = new Scanner(System.in);
						int g5 = input5.nextInt();
						db.editStudentGrade(studentId, "php", g5);
					} else {
						System.out.println("Invalid input");
					}

					System.out.println("Grade updated successfully");
					System.out.println("Edit (Update) more grades? Y/N: ");
					//Scanner yninput = new Scanner(System.in);
					//String yn = "";
					try {
						yn = yninput.nextLine().toLowerCase();
					} catch (InputMismatchException e) {
						System.out.println("\nFatal Error! Input mismatch\nLeaving program");
						System.exit(0);
					}
					//if(!(yninput.next().equalsIgnoreCase("Y")))
					//if(yn.toLowerCase()!="y")
					//{
						//break;
					//}

				}
				while (yn.equals("y"));

			} else if (choice.equals("R")) {
				System.out.println("Display Student Report\n");
				printStudents(true);
			} else if (choice.equals("B")) {
				System.out.println("Going back to main report\n");
				break;
			} else if (choice.equals("X")) {
				System.out.println("Exiting the program\n");
				System.exit(0);
			}

			else {
				System.out.println("Wrong option chosen! Please try again.\n");
			}

		}

	}

	public void printTeachers() {
		System.out.println(String.format("%1$-5s", "Id") + String.format("%1$-15s", "FirstName")
				+ String.format("%1$-15s", "LastName") + String.format("%1$-15s", "BirthDate")
				+ String.format("%1$-8s", "Age") + String.format("%1$-15s", "Salary"));

		ArrayList<Teacher> teachers = db.getTeachers();
		for (Person u : teachers) {
			Teacher t = (Teacher) u;
			t.print();
		}
	}

}
