package commons.eventScript;

import editor.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Controller_AddSwitchBoard implements Initializable {

	@FXML private VBox root;
	@FXML private TextField boardCode;
	@FXML private HBox buttonsRow;
	@FXML private Button cancel;
	@FXML private Button add;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		add.setOnAction(e -> {
			if (boardCode.getText().matches("[0-9]{4}")) {
				EventScript created = new Script_SwitchBoard(boardCode.getText());
				AddEventWindow.getController().addEventScript(created);
			}
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});

		cancel.setOnAction(e -> {
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});
	}

}
