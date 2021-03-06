package JavaUniversityProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.control.Alert;
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
public class viewEditReports {

	Database db;
	GridPane gridPane;
	Person loggedInUser;
	Stage window;
	Scene scene;

	public viewEditReports(Database dataB, Stage window, Person loggedInUsr) {
		this.db = dataB;
		this.loggedInUser = loggedInUsr;
		this.window = window;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		window.setTitle("Student Report");
		displayReports();

		window.show();
	}

	public void displayReports() {
		Label id = new Label("Student ID");
		GridPane.setConstraints(id, 0, 0); // c, r

		Label fn = new Label("First Name");
		GridPane.setConstraints(fn, 1, 0);

		Label ln = new Label("Last Name");
		GridPane.setConstraints(ln, 2, 0);

		Label gradesJ = new Label("JAVA Grades");
		GridPane.setConstraints(gradesJ, 3, 0);

		Label gradesC = new Label("C# Grades");
		GridPane.setConstraints(gradesC, 4, 0);

		Label gradesS = new Label("SQL Grades");
		GridPane.setConstraints(gradesS, 5, 0);

		Label gradesP = new Label("PHP Grades");
		GridPane.setConstraints(gradesP, 6, 0);

		gridPane.getChildren().addAll(id, fn, ln, gradesJ, gradesC, gradesS, gradesP);

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

			gridPane.getChildren().addAll(labelId, labelFn, labelLn);

			Label grades_J = new Label(Integer.toString(s.getGrade(CourseEnum.JAVA)));
			GridPane.setConstraints(grades_J, 3, row);

			Label grades_C = new Label(Integer.toString(s.getGrade(CourseEnum.CSHARP)));
			GridPane.setConstraints(grades_C, 4, row);

			Label grades_S = new Label(Integer.toString(s.getGrade(CourseEnum.SQL)));
			GridPane.setConstraints(grades_S, 5, row);

			Label grades_P = new Label(Integer.toString(s.getGrade(CourseEnum.PHP)));
			GridPane.setConstraints(grades_P, 6, row);

			gridPane.getChildren().addAll(grades_J, grades_C, grades_S, grades_P);

			row++;
		}

		ScrollPane sp = new ScrollPane();
		sp.setPannable(true);

		// to view individual student report

		Label lblStudentHead = new Label();
		lblStudentHead.setText("Enter Student ID to display Individual report");
		GridPane.setConstraints(lblStudentHead, 1, row + 2);

		Label lblStudent = new Label();
		lblStudent.setText("Student ID");
		GridPane.setConstraints(lblStudent, 1, row + 3);

		TextField input = new TextField();
		input.setPromptText("For Eg: 1");
		GridPane.setConstraints(input, 2, row + 3);

		Button indiReport = new Button();
		indiReport.setText("Go");
		GridPane.setConstraints(indiReport, 1, row + 4);
		indiReport.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				displayIndividualReports(Integer.valueOf(input.getText()));
			}
		});

		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 1, row + 6);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

		gridPane.getChildren().addAll(backBtn, lblStudent, input, indiReport, lblStudentHead);
		scene = new Scene(gridPane, 800, 400); // W, H
		window.setScene(scene);
	}

	public void displayIndividualReports(int id) {

		window.setTitle("Individual Student Report");

		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList = db.getStudents();

		for (Student s : studentList) {
			if (s.id == id) {
				Label id1 = new Label("Student ID");
				GridPane.setConstraints(id1, 0, 0); // c, r

				Label fn = new Label("First Name");
				GridPane.setConstraints(fn, 0, 1);

				Label ln = new Label("Last Name");
				GridPane.setConstraints(ln, 0, 2);

				Label gradesJ = new Label("JAVA Grades");
				GridPane.setConstraints(gradesJ, 0, 3);

				Label gradesC = new Label("C# Grades");
				GridPane.setConstraints(gradesC, 0, 4);

				Label gradesS = new Label("SQL Grades");
				GridPane.setConstraints(gradesS, 0, 5);

				Label gradesP = new Label("PHP Grades");
				GridPane.setConstraints(gradesP, 0, 6);

				Label labelId = new Label(String.valueOf(s.id));
				GridPane.setConstraints(labelId, 1, 0); // 1st col , nth row

				Label labelFn = new Label(s.firstname);
				GridPane.setConstraints(labelFn, 1, 1);

				Label labelLn = new Label(s.lastname);
				GridPane.setConstraints(labelLn, 1, 2);

				Label grades_J = new Label(Integer.toString(s.getGrade(CourseEnum.JAVA)));
				GridPane.setConstraints(grades_J, 1, 3);

				Label grades_C = new Label(Integer.toString(s.getGrade(CourseEnum.CSHARP)));
				GridPane.setConstraints(grades_C, 1, 4);

				Label grades_S = new Label(Integer.toString(s.getGrade(CourseEnum.SQL)));
				GridPane.setConstraints(grades_S, 1, 5);

				Label grades_P = new Label(Integer.toString(s.getGrade(CourseEnum.PHP)));
				GridPane.setConstraints(grades_P, 1, 6);
				
				s.getResultRetakes();
				
				Label result = new Label("Result: "+s.result);
				GridPane.setConstraints(result, 0, 7);
				
				Label retakes = new Label(Integer.toString(s.retakes)+" retake(s)");
				GridPane.setConstraints(retakes, 1, 7);

				Button editGr = new Button();
				editGr.setText("Edit Subject Grades");
				GridPane.setConstraints(editGr, 0, 9);
				editGr.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						editReportGrades(id, s.firstname + " " + s.lastname,s);
					}
				});

				Button backBtn = new Button();
				backBtn.setText("Back");
				GridPane.setConstraints(backBtn, 0, 11);
				backBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						window.close();
						new UserMenu(db, loggedInUser, window);
					}
				});

				gridPane.getChildren().clear();
				gridPane.getChildren().addAll(id1, fn, ln, gradesJ, gradesC, gradesS, gradesP, labelId, labelFn,
						labelLn, grades_J, grades_C, grades_S, grades_P, backBtn, editGr,result,retakes);

				window.setTitle("Individual Student Report");

			}
		}

	}

	public void editReportGrades(int id, String name, Student s) {
		window.setTitle("Edit Grades for subjects");

		Label lblGrdJ = new Label();
		lblGrdJ.setText("Java Grade");
		GridPane.setConstraints(lblGrdJ, 0, 0); // c,r

		TextField inputJ = new TextField();
		inputJ.setPromptText(Integer.toString(s.getGrade(CourseEnum.JAVA)));
		GridPane.setConstraints(inputJ, 1, 0);

		Label lblGrdC = new Label();
		lblGrdC.setText("C# Grade");
		GridPane.setConstraints(lblGrdC, 0, 1);

		TextField inputC = new TextField();
		inputC.setPromptText(Integer.toString(s.getGrade(CourseEnum.CSHARP)));
		GridPane.setConstraints(inputC, 1, 1);

		Label lblGrdS = new Label();
		lblGrdS.setText("SQL Grade");
		GridPane.setConstraints(lblGrdS, 0, 2);

		TextField inputS = new TextField();
		inputS.setPromptText(Integer.toString(s.getGrade(CourseEnum.SQL)));
		GridPane.setConstraints(inputS, 1, 2);

		Label lblGrdP = new Label();
		lblGrdP.setText("PHP Gr");
		GridPane.setConstraints(lblGrdP, 0, 3);

		TextField inputP = new TextField();
		inputP.setPromptText(Integer.toString(s.getGrade(CourseEnum.PHP)));
		GridPane.setConstraints(inputP, 1, 3);

		Button save = new Button();
		save.setText("Save Changes");
		GridPane.setConstraints(save, 0, 5);
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				if (!(inputJ.getText().isEmpty())) {
					db.editStudentGrade(id, "java", Integer.parseInt(inputJ.getText()));
				}

				if (!(inputC.getText().isEmpty())) {
					db.editStudentGrade(id, "csharp", Integer.parseInt(inputC.getText()));
				}

				if (!(inputS.getText().isEmpty())) {
					db.editStudentGrade(id, "sql", Integer.parseInt(inputS.getText()));
				}

				if (!(inputP.getText().isEmpty())) {
					db.editStudentGrade(id, "php", Integer.parseInt(inputP.getText()));
				}

				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Succesful");
					alert.setHeaderText("Grades updates successfully");
					alert.showAndWait();
				}
				
				inputJ.setText("");
				inputC.setText("");
				inputS.setText("");
				inputP.setText("");
			}
		});

		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 0, 7);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

		gridPane.getChildren().clear();
		gridPane.getChildren().addAll(lblGrdJ, lblGrdC, lblGrdS, lblGrdP, inputJ, inputC, inputS, inputP, backBtn,save);
	}

}
