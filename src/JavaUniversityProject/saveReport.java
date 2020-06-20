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
public class saveReport {
	Database db;
	GridPane gridPane;
	Person loggedInUser;
	Stage window;

	public saveReport(Database dataB, Stage window, Person loggedInUsr) {
		this.db = dataB;
		this.loggedInUser = loggedInUsr;
		this.window = window;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		window.setTitle("Save student reports");
		saveAllReports();
		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 2, 3);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

		gridPane.getChildren().add(backBtn);
		Scene scene = new Scene(gridPane, 450, 200); // W, H
		window.setScene(scene);
		window.show();
	}

	public void saveAllReports() {
		Label lbl1 = new Label("Save all reports");
		GridPane.setConstraints(lbl1, 0, 0); // c, r
		Button btn1 = new Button();
		btn1.setText("Confirm");
		GridPane.setConstraints(btn1, 2, 0);
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				db.saveAllReportsAsDoc();
				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Succesful");
					alert.setHeaderText("All student reports saved");
					alert.setContentText("reports saved to Documents >student_reports");
					alert.showAndWait();
				}

			}
		});

		Label lbl2 = new Label("Save report by ID");
		GridPane.setConstraints(lbl2, 0, 1); // c, r
		TextField txt1 = new TextField();
		txt1.setPromptText("For Eg: 1");
		GridPane.setConstraints(txt1, 1, 1);
		Button btn2 = new Button();
		btn2.setText("Confirm");
		GridPane.setConstraints(btn2, 2, 1);
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				saveReportbyID(Integer.valueOf(txt1.getText()), txt1);
				{
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Succesful");
					alert.setHeaderText("Student reports saved");
					alert.setContentText("individual report saved to Documents >student_reports");
					alert.showAndWait();
				}

				txt1.setText("");
			}
		});
		/*
		 * if (!(txt1.getText().isEmpty())) {
		 * 
		 * }
		 */

		gridPane.getChildren().addAll(lbl1, lbl2, txt1, btn1, btn2);
	}

	public void saveReportbyID(int id, TextField txt1) {
		boolean idExists = db.saveReportAsDoc(id);
		if (!(idExists)) {
			{
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Unsuccesful");
				alert.setHeaderText("Non-existent Id");
				alert.setContentText("No student exists with this student id, pleas try again");
				alert.showAndWait();
			}
			txt1.setText("");
		}
	}
}
