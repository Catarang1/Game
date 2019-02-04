package editor;

import editor.fxml.*;
import java.io.*;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 *
 * @author Jan
 */
public class EditorWindow extends Application {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	private Controller_Main controller;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		loader = new FXMLLoader(Controller_Main.class.getResource("editor_main.fxml"));
		root = loader.load();
		controller = loader.getController();
		
		scene = new Scene(root);
		stage = primaryStage;
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
