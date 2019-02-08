
package commons.eventScript;

// @author Jan

import java.io.*;
import java.net.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

 
public class CreateEventScriptWindow {
	
	private FXMLLoader loader = new FXMLLoader();
	private Parent root;
	private Scene scene;
	private Stage stage = new Stage();

	public CreateEventScriptWindow(URL fxmlLocation) {
		loader.setLocation(fxmlLocation);
		try {root = loader.load();} catch (IOException ex) {System.err.println("failed to load FXML");}
		scene = new Scene(root);
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Create Event Script");
		stage.show();
	}
	
	public void close() {
		stage.close();
	}
	
	

}
