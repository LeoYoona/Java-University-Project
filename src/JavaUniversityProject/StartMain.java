package JavaUniversityProject;

import java.util.ArrayList;
import java.util.Scanner;

public class StartMain {

	Database db;
	Person loggedInUser;

	public static void main(String[] args) {

		StartMain myProgram = new StartMain();
		myProgram.Start();

	}

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
		Scanner input = new Scanner(System.in);
		System.out.println("Enter choice: ");
		String choice = input.nextLine();
		return choice.toUpperCase();
	}

	// this will show the output according to user's choice
	public Boolean processCommand(String choice, Person loggedInUser) {
		if (choice.equals("S")) {
			printStudents(false);
		} 
		
		else if (choice.equals("T")) {
			printTeachers();
		} 
		else if (choice.equals("R")) {
			if (loggedInUser.accesslvl == UserAccessLevel.Editor) {
				printStudents(true);
			}
			else
			{
				System.out.println("Wrong option chosen! Please try again.\n");
			}
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
