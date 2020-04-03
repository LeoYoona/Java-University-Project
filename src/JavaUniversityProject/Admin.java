package JavaUniversityProject;

import java.time.LocalDate;

public class Admin extends Person{
	public Admin(String firstname, String lastname,String username, String password, int id,LocalDate birthdate) {
		super(firstname,lastname, username, password, id, birthdate, UserAccessLevel.Admin);
	}
}
