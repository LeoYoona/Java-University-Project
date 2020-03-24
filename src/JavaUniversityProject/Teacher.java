package JavaUniversityProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Teacher extends Person {
	protected double salary;
	public Teacher(String firstname, String lastname,String username, String password, int id,LocalDate birthdate,
			double salary ) {
		super(firstname,lastname, username, password, id, birthdate, UserAccessLevel.Editor);
		this.salary = salary;
	}
	
	public void print() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String birthdateFormat = birthdate.format(formatter);
		System.out.print(pad(id,5) + pad(firstname, 15) + pad(lastname, 15) +
				pad(birthdateFormat, 15) + pad(getAge(), 8) + pad(salary, 15));
		
		System.out.println();
	}
	
	private String pad(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}
	
	private String pad(int arg, int n) {
		return String.format("%1$-" + n + "s", arg);
	}
	
	private String pad(double arg, int n) {
		return String.format("%1$-" + n + "s", arg);
	}

}
