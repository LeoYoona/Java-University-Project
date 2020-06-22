package nl.inholland;

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
public class AddStudent {
	
	Database db;
	GridPane gridPane;
	Person loggedInUser;
	Stage window;

	public AddStudent(Database dataB, Stage window, Person loggedInUsr) {
		this.db = dataB;
		this.loggedInUser = loggedInUsr;
		this.window = window;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		window.setTitle("Add New Student");
		createNewStudent();
	}
	
	public void createNewStudent() {
		Label uname = new Label("User email");
		GridPane.setConstraints(uname, 0, 0); // c, r
		
		TextField inputUn = new TextField();
		inputUn.setPromptText("For Eg: yn123@inhmail.com");
		GridPane.setConstraints(inputUn,1,0);
		
		Label pwd = new Label("Password");
		GridPane.setConstraints(pwd, 0, 1); // c, r
		
		TextField inputPwd = new TextField();
		inputPwd.setPromptText("For Eg: secret1234");
		GridPane.setConstraints(inputPwd,1,1);
		
		Label fn = new Label("First name");
		GridPane.setConstraints(fn, 0, 2); // c, r
		
		TextField inputFn = new TextField();
		inputFn.setPromptText("For Eg: Yoona");
		GridPane.setConstraints(inputFn,1,2);
		
		Label ln = new Label("Last name");
		GridPane.setConstraints(ln, 0, 3); // c, r
		
		TextField inputLn = new TextField();
		inputLn.setPromptText("For Eg: Kim");
		GridPane.setConstraints(inputLn,1,3);
		
		Label dob = new Label("Date of Birth (DD/MM/YYYY)");
		GridPane.setConstraints(dob, 0, 4); // c, r
		
		TextField inputDOB = new TextField();
		inputDOB.setPromptText("For Eg: 12/12/2012");
		GridPane.setConstraints(inputDOB,1,4);
		
		Label grp = new Label("Group");
		GridPane.setConstraints(grp, 0, 5); // c, r
		
		TextField inputGrp = new TextField();
		inputGrp.setPromptText("For Eg: IT-02-A");
		GridPane.setConstraints(inputGrp,1,5);
		
		final Label lblMessage = new Label();
		GridPane.setConstraints(lblMessage, 1, 8);
		
		Button addBtn = new Button();
		addBtn.setText("Confirm");
		GridPane.setConstraints(addBtn, 1,7);
		addBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {


				if(
						inputUn.getText().isEmpty() || 
						inputPwd.getText().isEmpty() || 
						inputFn.getText().isEmpty() || 
						inputLn.getText().isEmpty() || 
						inputDOB.getText().isEmpty() || 
						inputGrp.getText().isEmpty() 
				  )
				{
					lblMessage.setText("Field(s) empty. Please try again");
			        lblMessage.setTextFill(Color.RED);
				}
				else if (macthesDOBpattern(inputDOB.getText().toString()) == true) 
				{
					{ 
					    Alert alert = new Alert(Alert.AlertType.INFORMATION);
					    alert.setTitle("Succesful");
					    alert.setHeaderText("Student added successfully");
					    alert.setContentText(inputFn.getText()+" "+inputLn.getText()+
					    		"\n"+inputGrp.getText());
					    alert.showAndWait();
					}
					
					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
					LocalDate parsedBirthDate = LocalDate.parse(inputDOB.getText(), formatters);
					int id = db.ids;	
					db.updateID(id);
					
					db.addStudent(inputFn.getText(), inputLn.getText(), 
							inputUn.getText(), inputPwd.getText(),id,parsedBirthDate, inputGrp.getText() );
				} 
				else 
				{
					lblMessage.setText("Incorrect DOB format. Please try again");
			        lblMessage.setTextFill(Color.RED);
				}
				
				inputUn.setText("");
				inputPwd.setText("");
				inputFn.setText("");
				inputLn.setText("");
				inputDOB.setText("");
				inputGrp.setText("");	
			}
		});
		
		Button backBtn = new Button();
		backBtn.setText("Back");
		GridPane.setConstraints(backBtn, 1, 10);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				window.close();
				new UserMenu(db, loggedInUser, window);
			}
		});

	
		gridPane.getChildren().
		addAll(uname,inputUn,pwd,inputPwd,fn,inputFn,ln,inputLn,dob,inputDOB,grp,inputGrp,addBtn,backBtn,lblMessage);
		Scene scene = new Scene(gridPane, 500, 350); // W, H
		window.setScene(scene);
		window.show();
		
	}
	
	public boolean macthesDOBpattern(String birthdate)
	{
		Pattern DATE_PATTERN = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[/](19|20)\\d\\d$");
		if((DATE_PATTERN.matcher(birthdate).matches()))
		{
			return true;
		}
		
		return false;
	}
}
