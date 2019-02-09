package editor.fxml;

import commons.*;
import commons.eventScript.*;
import editor.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.util.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_EventList implements Initializable {

	@FXML private ListView<GameEvent> list;
	@FXML private Button closeB;
	@FXML private Button removeB;
	@FXML private Button createB;

	/**
	 * Initializes the controller class.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		list.setItems(EditorWindow.getEventsToSave());
		list.setCellFactory((ListView<GameEvent> param) -> new EventCell());
		
		createB.setOnAction(e ->  AddEventWindow.show());
		removeB.setOnAction(e -> list.getItems().remove(list.getSelectionModel().getSelectedItem()));
		closeB.setOnAction(e -> EventsWindow.close());
	}	
	
}
