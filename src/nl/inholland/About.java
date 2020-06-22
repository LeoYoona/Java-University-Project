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
public class About {
	GridPane gridPane;
	
	Stage window;
	public About(Stage window) {
		this.window = window;
		gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		window.setTitle("About");
		
		Button exit = new Button();
		exit.setText("Credits");
		GridPane.setConstraints(exit, 0, 0);
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				new Line(window);
			}
		});
		
		gridPane.getChildren().addAll(exit);
		Scene scene = new Scene(gridPane, 500, 350); // W, H
		window.setScene(scene);
		window.show();
	}

}
