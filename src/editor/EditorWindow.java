package editor;

import commons.*;
import commons.eventScript.*;
import editor.fxml.*;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class EditorWindow extends Application {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;
	private static Controller_Main controller;
	private static Board board = new Board();
	private static ObservableList<GameEvent> eventsToSave = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Coords c = new Coords(0, 0);
		Set<String> flags = new HashSet<>();
		flags.add("something");
		List<EventScript> scripts = new ArrayList<>();
		scripts.add(new EventScript() {
			@Override
			public void runScript() {
				System.out.println("run");
			}

			@Override
			public boolean isUnique() {
				return true;
			}

			@Override
			public int getOrder() {
				return 6;
			}
		});
		
		eventsToSave.add(new GameEvent(c, true, flags, flags, scripts));
		
		loader = new FXMLLoader(Controller_Main.class.getResource("editor_main.fxml"));
		root = loader.load();
		controller = loader.getController();
		
		scene = new Scene(root);
		stage = primaryStage;
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Game Board Editor");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static Controller_Main getController() {
		return controller;
	}

	public static Board getBoard() {
		return board;
	}

	public static ObservableList<GameEvent> getEventsToSave() {
		return eventsToSave;
	}

	
}
