package sandbox;

import java.io.*;
import java.net.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

public class FXMLWindow {

	private Stage stage;
	private Scene scene;
	private Parent root;
	private FXMLLoader loader;

	public FXMLWindow(URL fxml, boolean transparent) {
		loader = new FXMLLoader(fxml);

		try {
			root = loader.load();
		} catch (IOException e) {
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

}
