
package editor;

// @author Jan

import java.io.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

 
public class DocumentationWindow {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

	public DocumentationWindow() throws IOException {
		root = FXMLLoader.load(getClass().getResource("/editor/fxml/editor_docView.fxml"));
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.show();
	}
	
	

}
