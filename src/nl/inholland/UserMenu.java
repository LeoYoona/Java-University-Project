package nl.inholland;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class UserMenu {
	/*
	 * ecplise short cuts. ctrl+shift+/
	 * to comment ctrl+sift+f to indent code ctrl+alt+down-arrow to copy seleted
	 * code to the next line/down *
	 */
	Database db;
	Person loggedInUser;
	Stage window;

	public UserMenu(Database dataB, Person loggedInUsr, Stage window) {
		this.db = dataB;
		this.loggedInUser = loggedInUsr;
		this.window=window;
		window.setTitle("User Management");

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		Label label = new Label("Welcome " + this.loggedInUser.firstname + " " + this.loggedInUser.lastname + "! \n"
				+ "Choose an option to proceed further \n");
		GridPane.setConstraints(label, 0, 0);
		// label.setAlignment(Pos.CENTER);

		Button viewStudents = new Button();
		viewStudents.setText("Display Students");
		GridPane.setConstraints(viewStudents, 0, 2); // 2nd col , 1st row
		viewStudents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				if (loggedInUser.accesslvl == UserAccessLevel.Basic) {
					new DisplayPersons(db, false, window, "student",loggedInUser);
				} else {
					new DisplayPersons(db, true, window, "student",loggedInUser);
				}
			}
		});

		Button viewTeachers = new Button();
		viewTeachers.setText("Display Teachers");
		GridPane.setConstraints(viewTeachers, 0, 3); // 2nd col , 1st row
		viewTeachers.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				new DisplayPersons(db, true, window, "teacher",loggedInUser);
			}
		});

		Button addStudents = new Button();
		addStudents.setText("Add Students");
		GridPane.setConstraints(addStudents, 0, 4); // 2nd col , 1st row
		addStudents.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				new AddStudent(db, window,loggedInUser);
			}
		});
		

		Button displayReports = new Button();
		displayReports.setText("Display Student's Reports");
		GridPane.setConstraints(displayReports, 0, 5); // 2nd col , 1st row
		displayReports.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				new viewEditReports(db, window, loggedInUser);
			}
		});

		Button saveReports = new Button();
		saveReports.setText("Save Student's Reports to PC");
		GridPane.setConstraints(saveReports, 0, 6); 
		saveReports.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				new saveReport(db, window,loggedInUser);
			}
		});
		
		Button logout = new Button();
		logout.setText("Logout");
		GridPane.setConstraints(logout, 0, 8); 
		logout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
				Stage stage = new Stage();
				LoginForm lg = new LoginForm();
				lg.start(stage);
			}
		});
		
		Button About = new Button();
		About.setText("About");
		GridPane.setConstraints(About, 0, 7); // 2nd col , 1st row
		About.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				window.close();
				new About(window);
			}
		});


		Button exit = new Button();
		exit.setText("Exit");
		GridPane.setConstraints(exit, 0, 9);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				window.close();
			}
		});

		gridPane.setAlignment(Pos.CENTER);
		// window.setCenter(gridPane);

		// add label to vbox
		gridPane.getChildren().addAll(label, viewStudents, viewTeachers, addStudents, displayReports, saveReports,logout,About,
				exit);

		if ((loggedInUser.accesslvl == UserAccessLevel.Basic)) // for students remove the following controls
		{
			gridPane.getChildren().removeAll(addStudents, displayReports, saveReports);
		} else if ((loggedInUser.accesslvl == UserAccessLevel.Editor)) // for teachers remove the following controls
		{
			gridPane.getChildren().remove(saveReports);
		}
		Scene scene = new Scene(gridPane, 400, 400);
		window.setScene(scene);
		window.show();
		
		//close confirmation 
		window.setOnCloseRequest((WindowEvent event1) -> {
			{ 

				GridPane secondaryLayout = new GridPane();
				secondaryLayout.setPadding(new Insets(10, 10, 10, 10));
				secondaryLayout.setVgap(10); // Vertical spacing between grid items
				secondaryLayout.setHgap(8); // Horizontal spacing between grid items
				
	            Scene secondScene = new Scene(secondaryLayout, 230, 80); //w,h
	 
	            // New window (Stage)
	            Stage newWindow = new Stage();
	            newWindow.setTitle("INFO");
	            newWindow.setScene(secondScene);
	 
	            // Specifies the modality for new window.
	            newWindow.initModality(Modality.WINDOW_MODAL);
	 
	            // Specifies the owner Window (parent) for new window
	            newWindow.initOwner(window);
	 
	            // Set position of second window, related to primary window.
	            newWindow.setX(window.getX() + 200);
	            newWindow.setY(window.getY() + 100);
	            
				Label secondLabel = new Label("Close the window?");
				GridPane.setConstraints(secondLabel, 0, 0);
				
				Button btnOk = new Button();
				GridPane.setConstraints(btnOk, 0, 1);
				
				btnOk.setText("OK");
				btnOk.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						window.close();
					}
				});
				
				Button cancelBtn = new Button();
				GridPane.setConstraints(cancelBtn, 1, 1);
				
				cancelBtn.setText("Cancel");
				cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {

						newWindow.close();
						new UserMenu(db,loggedInUser,window );
					}
				});
				
	            
	            secondaryLayout.getChildren().addAll(btnOk,cancelBtn,secondLabel);
	 
	            newWindow.showAndWait();
			}
	       
	    });
	}
	
}
