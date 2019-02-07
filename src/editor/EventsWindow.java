
package editor;

// @author Jan

import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

 
public class EventsWindow {

	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	private static FXMLLoader loader;
	private static Controller_EventList controller;

	static {
		loader = new FXMLLoader(Controller_EventList.class.getResource("editor_eventlist.fxml"));
		controller = loader.getController();
		try {
			root = loader.load();
		} catch (IOException ex) {
			Logger.getLogger(SaveWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		scene = new Scene(root);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Event List");
		stage.setScene(scene);
	}
	
	public static void show() {
		stage.show();
	}
	public static void close() {
		stage.close();
	}

}
