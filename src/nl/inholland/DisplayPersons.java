package nl.inholland;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class DisplayPersons {
	/*
	 * ecplise short cuts. ctrl+shift+/ to comment ctrl+sift+f to indent code
	 * ctrl+alt+down-arrow to copy seleted code to the next line/down *
	 */
	Database db;
	GridPane gridPane;
	Person loggedInUser;

	public DisplayPersons(Database dataB, boolean includeGrades, Stage window, String personType, Person loggedInUsr) {
		this.db = dataB;
		this.loggedInUser = loggedInUsr;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		if (personType == "student") {
			window.setTitle("Students");
			displayStudentDetails(includeGrades, window);
		} else if (personType == "teacher") {
			window.setTitle("Teachers");
			displayTeacherDetails(window);
		}

	}

	public void displayStudentDetails(boolean includeGrades, Stage window) {
		Label id = new Label("Student ID");
		GridPane.setConstraints(id, 0, 0); // c, r

		Label fn = new Label("First Name");
		GridPane.setConstraints(fn, 1, 0);

		Label ln = new Label("Last Name");
		GridPane.setConstraints(ln, 2, 0);

		Label dob = new Label("D.O.B");
		GridPane.setConstraints(dob, 3, 0);

		Label age = new Label("Age");
		GridPane.setConstraints(age, 4, 0);

		Label group = new Label("Group");
		GridPane.setConstraints(group, 5, 0);

		gridPane.getChildren().addAll(id, fn, ln, dob, age, group);

		ArrayList<Student> students = db.getStudents();
		Scene scene;
		int row = 1;
		for (Student s : students) {

			Label labelId = new Label(String.valueOf(s.id));
			GridPane.setConstraints(labelId, 0, row); // 1st col , nth row

			Label labelFn = new Label(s.firstname);
			GridPane.setConstraints(labelFn, 1, row);

			Label labelLn = new Label(s.lastname);
			GridPane.setConstraints(labelLn, 2, row);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			String birthdateFormat = s.birthdate.format(formatter);
			Label labelDOB = new Label(birthdateFormat);
			GridPane.setConstraints(labelDOB, 3, row);

			Label labelAge = new Label(String.valueOf(s.getAge()));
			GridPane.setConstraints(labelAge, 4, row);

			Label labelGrp = new Label(s.group);
			GridPane.setConstraints(labelGrp, 5, row);

			gridPane.getChildren().addAll(labelId, labelFn, labelLn, labelDOB, labelAge, labelGrp);

			if (includeGrades == true) {
				Label gradesJ = new Label("JAVA Grades");
				GridPane.setConstraints(gradesJ, 6, 0);
				Label grades_J = new Label(Integer.toString(s.getGrade(CourseEnum.JAVA)));
				GridPane.setConstraints(grades_J, 6, row);

				Label gradesC = new Label("C# Grades");
				GridPane.setConstraints(gradesC, 7, 0);
				Label grades_C = new Label(Integer.toString(s.getGrade(CourseEnum.CSHARP)));
				GridPane.setConstraints(grades_C, 7, row);

				Label gradesS = new Label("SQL Grades");
				GridPane.setConstraints(gradesS, 8, 0);
				Label grades_S = new Label(Integer.toString(s.getGrade(CourseEnum.SQL)));
				GridPane.setConstraints(grades_S, 8, row);

				Label gradesP = new Label("PHP Grades");
				GridPane.setConstraints(gradesP, 9, 0);
				Label grades_P = new Label(Integer.toString(s.getGrade(CourseEnum.PHP)));
				GridPane.setConstraints(grades_P, 9, row);

				gridPane.getChildren().addAll(gradesJ, gradesC, gradesS, gradesP, grades_J, grades_C, grades_S,
						grades_P);
			}

			row++;
		}

		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 1, row + 2);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

		gridPane.getChildren().addAll(backBtn);

		scene = new Scene(gridPane, 700, 250); // W, H
		window.setScene(scene);
		window.show();

	}

	public void displayTeacherDetails(Stage window) {

		Label id = new Label("Teacher ID");
		GridPane.setConstraints(id, 0, 0); // c, r

		Label fn = new Label("First Name");
		GridPane.setConstraints(fn, 1, 0);

		Label ln = new Label("Last Name");
		GridPane.setConstraints(ln, 2, 0);

		Label age = new Label("Age");
		GridPane.setConstraints(age, 3, 0);

		Label sal = new Label("Salary");
		GridPane.setConstraints(sal, 4, 0);

		gridPane.getChildren().addAll(id, fn, ln, age, sal);

		ArrayList<Teacher> teacher = db.getTeachers();
		Scene scene;
		int row = 1;
		for (Teacher t : teacher) {

			Label labelId = new Label(String.valueOf(t.id));
			GridPane.setConstraints(labelId, 0, row); // 1st col , nth row

			Label labelFn = new Label(t.firstname);
			GridPane.setConstraints(labelFn, 1, row);

			Label labelLn = new Label(t.lastname);
			GridPane.setConstraints(labelLn, 2, row);

			Label labelAge = new Label(String.valueOf(t.getAge()));
			GridPane.setConstraints(labelAge, 3, row);

			Label labelSal = new Label("₹ " + Double.toString(t.salary));
			GridPane.setConstraints(labelSal, 4, row);

			gridPane.getChildren().addAll(labelId, labelFn, labelLn, labelAge, labelSal);

			row++;
		}

		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 1, row + 2);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

		gridPane.getChildren().addAll(backBtn);

		scene = new Scene(gridPane, 500, 250); // W, H
		window.setScene(scene);
		window.show();

	}
}
