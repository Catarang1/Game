
package editor;

// @author Jan

import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

 
public class AddEventWindow {

	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	private static FXMLLoader loader;
	private static Controller_AddEvent controller;
	
	static {
		loader = new FXMLLoader(Controller_AddEvent.class.getResource("editor_createEvent.fxml"));
		try {
			root = loader.load();
		} catch (IOException ex) {
			Logger.getLogger(AddEventWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		controller = loader.getController();
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Add Event");
	}
	
	public static void show() {
		stage.show();
	}
	
	public static void close() {
		stage.close();
	}

	public static Controller_AddEvent getController() {
		return controller;
	}
	
	
	

}
