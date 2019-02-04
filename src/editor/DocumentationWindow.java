package editor;

import java.io.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

public class DocumentationWindow {

	private static DocumentationWindow instance;
	private Stage stage;
	private Scene scene;
	private Parent root;

	private DocumentationWindow() {
		try {
			root = FXMLLoader.load(getClass().getResource("/editor/fxml/editor_docView.fxml"));
		} catch (IOException ex) {
			Logger.getLogger(DocumentationWindow.class.getName()).log(Level.SEVERE, null, ex);
		}
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Editor Documentation");
	}

	public static DocumentationWindow get() {
		if (instance == null) {
			instance = new DocumentationWindow();
		}
		return instance;
	}

	public void show() {
		stage.show();
	}

}
