package commons.eventScript;

import commons.eventScript.*;
import commons.eventScript.Script_Alert;
import editor.*;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.util.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_AddAlert implements Initializable {

	@FXML private VBox root;
	@FXML private ComboBox<Script_Alert.Type> type;
	@FXML private TextField text;
	@FXML private HBox buttonsRow;
	@FXML private Button cancel;
	@FXML private Button add;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupComboBox();
		setupAddButton();
	}

	private void setupComboBox() {
		type.getItems().addAll(Arrays.asList(Script_Alert.Type.values()));
		type.getSelectionModel().selectFirst();
		type.setCellFactory((ListView<Script_Alert.Type> param) -> {
			ListCell<Script_Alert.Type> cell = new ListCell<Script_Alert.Type>() {
				@Override
				protected void updateItem(Script_Alert.Type item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						return;
					}
					setGraphic(new ImageView(item.getAlertImage()));
					setText(item.name());
				}

			};
			return cell;
		});
	}

	private void setupAddButton() {
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				EventScript created = new Script_Alert(type.getSelectionModel().getSelectedItem(), text.getText());
				AddEventWindow.getController().addEventScript(created);
				Stage stage = (Stage) root.getScene().getWindow();
				stage.close();
			}
		});
	}

	public void reset() {
		type.getSelectionModel().selectFirst();
		text.clear();
	}

}
