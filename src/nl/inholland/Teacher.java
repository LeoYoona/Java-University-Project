package nl.inholland;

import java.time.LocalDate;

public class Teacher extends Person {
	protected double salary;
	public Teacher(String firstname, String lastname,String username, String password, int id,LocalDate birthdate,
			double salary ) {
		super(firstname,lastname, username, password, id, birthdate, UserAccessLevel.Editor);
		this.salary = salary;
	}
	
}
