package JavaUniversityProject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class HelloFX extends Application {
	public static void main(String[] args) {
		launch();
	}
}

	@Override
	public void start(Stage window) throws Exception {
		window.setTitle("Hello JavaFX");
		Button button = new Button("Click me");
		button.setPrefWidth(300);
		button.setPrefHeight(300);
		Scene scene = new Scene(button);
		window.setScene(scene);
		window.show();
		}
}