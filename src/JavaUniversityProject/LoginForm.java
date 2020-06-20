package JavaUniversityProject;

import java.util.Scanner;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("unused")
public class LoginForm extends Application {
	
	
	Database db;
	Person loggedInUser;
	String username, password;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage window) {
		db = new Database();
		
		window.setTitle("Login University System");
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		Label userLabel = new Label();
		userLabel.setText("Username:");
		GridPane.setConstraints(userLabel, 0, 0);  // 1st column, 1st row

		TextField userInput = new TextField();
		userInput.setPromptText("username");
		GridPane.setConstraints(userInput, 1, 0); //2nd col , 1st row

		Label passwordLabel = new Label("Password");
		GridPane.setConstraints(passwordLabel, 0, 1); 

		PasswordField passwordField = new PasswordField();
		GridPane.setConstraints(passwordField, 1, 1);
		
		final Label lblMessage = new Label();
		GridPane.setConstraints(lblMessage, 1, 2);
		
		Button loginButton = new Button();
		loginButton.setText("Log in");
		GridPane.setConstraints(loginButton, 1, 4);
		
		gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, passwordField, loginButton,lblMessage);

		Scene scene = new Scene(gridPane, 350, 200);
		window.setScene(scene);
		window.show();

		loginButton.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				Person loggedInUser = null;
				
				username = userInput.getText().toString();
				password = passwordField.getText().toString();
				
				loggedInUser = db.checkUserCredentials(username, password);

				if (loggedInUser != null) 
				{
					lblMessage.setText("You are Logged in! \n Please wait");
			        lblMessage.setTextFill(Color.GREEN);	        
			        
			        window.close();
			        new UserMenu(db,loggedInUser,window );
				} else 
				{
					lblMessage.setText("Incorrect user or passowrd.");
			        lblMessage.setTextFill(Color.RED);
				}
				
				userInput.setText("");
				passwordField.setText("");
			}
			}
			);		
	}

}
