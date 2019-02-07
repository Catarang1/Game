
package editor;

// @author Jan

import commons.*;
import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

 
public class SaveWindow {
	
	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	private static FXMLLoader loader;
	private static Controller_Save controller;

	static {
		loader = new FXMLLoader(Controller_Save.class.getResource("editor_save.fxml"));
		try {
			root = loader.load();
		} catch (IOException ex) {
			Logger.getLogger(SaveWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		controller = loader.getController();
		
		scene = new Scene(root);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Save...");
		stage.setScene(scene);
	}
	
	
	
	public static void show() {
		controller.reset();
		stage.show();
	}
	
	public static void close() {
		stage.close();
	}

}
