package nl.inholland;

import java.util.Scanner;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
		//new Line();
		
		window.setTitle("Login University System");
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items

		Label userLabel = new Label();
		userLabel.setText("User email:");
		GridPane.setConstraints(userLabel, 0, 0);  // 1st column, 1st row

		TextField userInput = new TextField();
		userInput.setPromptText("user email");
		GridPane.setConstraints(userInput, 1, 0); //2nd col , 1st row

		Label passwordLabel = new Label("Password");
		GridPane.setConstraints(passwordLabel, 0, 1); 

		PasswordField passwordField = new PasswordField();
		GridPane.setConstraints(passwordField, 1, 1);
		
		final Label lblMessage = new Label();
		GridPane.setConstraints(lblMessage, 0, 4);
		
		Button loginButton = new Button();
		loginButton.setText("Log in");
		GridPane.setConstraints(loginButton, 0, 2);
		
		
		gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, passwordField, loginButton,lblMessage);

		Scene scene = new Scene(gridPane, 400, 200);//w, h
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
			        
			        //window.close();
			        new UserMenu(db,loggedInUser,window );
				} 
				else if(userInput.getText().toString().isEmpty() || passwordField.getText().toString().isEmpty( ))
				{
					lblMessage.setText("Empty user email or password");
			        lblMessage.setTextFill(Color.RED);
					throw new UnauthorizedException("Empty user email or password");
//					try {
//						throw new UnauthorizedException("\"Empty username or\r\n" + 
//								"password");
//					}
//					catch(UnauthorizedException e){
//						throw new UnauthorizedException("\"Empty username or\r\n" + 
//								"password");
//					}
					
				}
				else 
				{
					lblMessage.setText("Incorrect user email or passowrd.");
			        lblMessage.setTextFill(Color.RED);
			        throw new UnauthorizedException("Incorrect user email or passowrd.");
				}
				
				userInput.setText("");
				passwordField.setText("");
			}
			}
			);		
		
		
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
						Stage stage = new Stage();
						LoginForm lg = new LoginForm();
						lg.start(stage);
					}
				});
				
	            
	            secondaryLayout.getChildren().addAll(btnOk,cancelBtn,secondLabel);
	 
	            newWindow.showAndWait();
			}
	       
	    });
	}

}
