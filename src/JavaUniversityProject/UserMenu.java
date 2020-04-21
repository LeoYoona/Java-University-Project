package JavaUniversityProject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class UserMenu {
	/* https://www.youtube.com/watch?v=r_dDKrx-aKc
	 * ecplise short cuts. 
	 * ctrl+shift+/ to comment
	 * ctrl+sift+f to indent code 
	 * ctrl+alt+down-arrow to copy seleted code to the next line/down		 * 
	 */
	Database db;
	Person loggedInUser;
	
	public UserMenu(Database dataB,Person loggedInUsr,Stage window)
	{
		this.db=dataB;
		this.loggedInUser=loggedInUsr;
		window.setTitle("User Management");
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		Label label = new Label("Welcome "+this.loggedInUser.firstname+" "+this.loggedInUser.lastname+"! \n"
								+"Choose an option to proceed further \n"); 
		GridPane.setConstraints(label, 0, 0);
		// label.setAlignment(Pos.CENTER);
		
		Button viewStudents = new Button();
		viewStudents.setText("Display Students");
		GridPane.setConstraints(viewStudents, 1, 2); //2nd col , 1st row
		
		Button viewTeachers = new Button();
		viewTeachers.setText("Display Teachers");
		GridPane.setConstraints(viewTeachers, 1, 3); //2nd col , 1st row

		
		Button addStudents = new Button();
		addStudents.setText("Add Students");
		GridPane.setConstraints(addStudents, 1, 4); //2nd col , 1st row

		
		Button displayReports = new Button();
		displayReports.setText("Display Student's Reports");
		GridPane.setConstraints(displayReports, 1, 5); //2nd col , 1st row

		
		Button saveReports = new Button();
		saveReports.setText("Save Student's Reports to PC");
		GridPane.setConstraints(saveReports, 1, 6); //2nd col , 1st row

		
		Button exit = new Button();
		exit.setText("Exit");
		GridPane.setConstraints(exit, 1, 7); //2nd col , 1st row

		//window.setCenter(gridPane);  
		
		// add label to vbox 
		gridPane.getChildren().addAll(label,viewStudents,viewTeachers, addStudents, displayReports, saveReports,exit ); 
		Scene scene = new Scene(gridPane, 500, 500);
		window.setScene(scene);
		window.show();
	}
}