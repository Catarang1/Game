package commons.eventScript;

import commons.eventScript.*;
import commons.eventScript.Alert;
import editor.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.util.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_AddAlert implements Initializable {

	@FXML private VBox root;
	@FXML private ComboBox<Alert.Type> type;
	@FXML private TextField text;
	@FXML private HBox buttonsRow;
	@FXML private Button cancel;
	@FXML private Button add;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupComboBox();
		setupAddButton();
	}

	private void setupComboBox() {
		type.getItems().addAll(Arrays.asList(Alert.Type.values()));
		type.getSelectionModel().selectFirst();
		type.setCellFactory((ListView<Alert.Type> param) -> {
			ListCell<Alert.Type> cell = new ListCell<Alert.Type>(){
				@Override
				protected void updateItem(Alert.Type item, boolean empty) {
					super.updateItem(item, empty);
					if (empty|| item==null) return;
					
					setGraphic(new ImageView(item.getAlertImage()));
					setText(item.name());
				}
				
			};
			return cell;
		});
	}

	private void setupAddButton() {
		add.setOnAction(e -> {
			EventScript created = new Alert(type.getSelectionModel().getSelectedItem(), text.getText());
			AddEventWindow.getController().addEventScript(created);
			Alert.closeEditorWindow();
		});
	}
	
	public void reset() {
		type.getSelectionModel().selectFirst();
		text.clear();
	}
	
}
