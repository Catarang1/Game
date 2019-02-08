package commons.eventScript;

import commons.*;
import editor.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Controller_RemoveFlag implements Initializable {

	@FXML private VBox root;
	@FXML private ComboBox<Flag> flagSelection;
	@FXML private HBox buttonsRow;
	@FXML private Button cancel;
	@FXML private Button add;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		flagSelection.getItems().addAll(Flag.values());
		
		add.setOnAction(e -> {
			EventScript created = new Script_FlagLost(flagSelection.getSelectionModel().getSelectedItem());
			AddEventWindow.getController().addEventScript(created);
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});
		
		cancel.setOnAction(e -> {
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});
	}	
	
}
