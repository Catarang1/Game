package editor;

import commons.*;
import editor.fxml.*;
import java.io.*;
import java.util.logging.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class EditorWindow {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	private static Controller_Main controller;
	private static Board board = new Board();
	private static ObservableList<GameEvent> eventsToSave = FXCollections.observableArrayList();
	
	public EditorWindow() {
		try {
			loader = new FXMLLoader(Controller_Main.class.getResource("editor_main.fxml"));
			root = loader.load();
			controller = loader.getController();
			
			scene = new Scene(root);
			stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Game Board Editor");
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(EditorWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static Controller_Main getController() {
		return controller;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		EditorWindow.board = board;
	}

	public static ObservableList<GameEvent> getEventsToSave() {
		return eventsToSave;
	}
	
	public static void addEvent(GameEvent event) {
		eventsToSave.add(event);
	}

	
}
