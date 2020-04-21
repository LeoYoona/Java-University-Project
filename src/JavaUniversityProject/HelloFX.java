package JavaUniversityProject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/*
import JavaUniversityProject.factory.MenuBuilder;
import JavaUniversityProject.javafx.layout.GeneratedMenuMainWindow;
import JavaUniversityProject.javafx.layout.MenuMainWindow;
import JavaUniversityProject.model.Role;*/


@SuppressWarnings("unused")
public class HelloFX extends Application {
	public static void main(String[] args) {
		launch();
	}


	@Override
	/*public void start(Stage window) throws Exception {
		window.setTitle("Hello JavaFX");
		Button button = new Button("Click me");
		button.setPrefWidth(300);
		button.setPrefHeight(300);
		Scene scene = new Scene(button);
		window.setScene(scene);
		window.show();
		}*/
		public void start(Stage window) throws Exception {
		
		window.setTitle("Login screen");
	
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		Label userLabel = new Label();
		userLabel.setText("Username:");
		GridPane.setConstraints(userLabel, 0, 0);
		
		TextField userInput = new TextField();
		userInput.setPromptText("username");
		GridPane.setConstraints(userInput, 1, 0);
		
		Label passwordLabel = new Label("Password");
		GridPane.setConstraints(passwordLabel,  0,  1);
		
	
		PasswordField passwordField = new PasswordField();
		GridPane.setConstraints(passwordField, 1, 1);
	
		String password = passwordField.getText();
		Button loginButton = new Button();
		loginButton.setText("Log in");
		GridPane.setConstraints(loginButton, 1, 2);
		
		Scene scene = new Scene(gridPane, 300, 200);
		
		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if (userInput != null) {
					System.out.println("Closing the window...");
					window.close();
					//new MenuMainWindow();
					//new GeneratedMenuMainWindow(Role.ADMIN);
				}
				
			}
		});
		
		gridPane.getChildren().addAll(userLabel, userInput, passwordLabel, passwordField, loginButton);
		
		window.setScene(scene);
		window.show();
		
	}

}