package JavaUniversityProject;

import java.util.ArrayList;

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
public class DisplayStudents {
	/*
	 * https://www.youtube.com/watch?v=r_dDKrx-aKc ecplise short cuts. ctrl+shift+/
	 * to comment ctrl+sift+f to indent code ctrl+alt+down-arrow to copy seleted
	 * code to the next line/down *
	 */
	Database db;

	public DisplayStudents(Database dataB, boolean includeGrades,Stage window) {
		this.db = dataB;
		window.setTitle("Students");
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10); // Vertical spacing between grid items
		gridPane.setHgap(8); // Horizontal spacing between grid items
		
		ListView<Label> listView = new ListView<Label>();
		
		ArrayList<Student> students = db.getStudents();

		for (Student s : students) {
			
			Label label = new Label(s.firstname);
			 listView.getItems().add(label);	
		}
		
       
        VBox vBox = new VBox(listView);

        Scene scene = new Scene(vBox, 300, 300);
		window.setScene(scene);
		window.show();
	}
}
