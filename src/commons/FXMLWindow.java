package commons;

import java.io.*;
import java.net.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

public class FXMLWindow{

	private static Stage stage;
	private static Scene scene;
	private static Parent root;
	private static FXMLLoader loader;

	public FXMLWindow(URL fxml, boolean transparent) {
		loader = new FXMLLoader(fxml);
		System.out.println(loader.getLocation().toExternalForm());
		try {
			root = loader.load();
		} catch (IOException e) {
			System.err.println("FXML load fucked up");
			e.printStackTrace();
		}

		scene = new Scene(root);
		stage = new Stage();
		if (transparent) {
			stage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);
		} else {
			stage = new Stage();
		}
		stage.setScene(scene);
		stage.centerOnScreen();
	}

	public void kill() {
		stage.close();
	}

	public void show() {
		stage.show();
	}

	public Stage getStage() {
		return stage;
	}

	public Scene getScene() {
		return scene;
	}

	public Parent getRoot() {
		return root;
	}

	public FXMLLoader getLoader() {
		return loader;
	}

}
