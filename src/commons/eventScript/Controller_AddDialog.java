package commons.eventScript;

import editor.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_AddDialog implements Initializable {

	@FXML private VBox root;
	@FXML private HBox buttonsRow;
	@FXML private Button add;
	@FXML private Button remove;
	@FXML private VBox textFieldsWrapper;
	@FXML private Button cancel;
	@FXML private Button addScript;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		add.setOnAction(e -> {
			TextField tf = new TextField();
			tf.setMaxWidth(Double.MAX_VALUE);
			textFieldsWrapper.getChildren().add(tf);
			Stage stage = (Stage) root.getScene().getWindow();
			stage.sizeToScene();
		});

		remove.setOnAction(e -> {
			int number_of_textfields = textFieldsWrapper.getChildren().size();
			if (number_of_textfields > 1) {
				textFieldsWrapper.getChildren().remove(number_of_textfields-1);
			}
			Stage stage = (Stage) root.getScene().getWindow();
			stage.sizeToScene();
		});

		cancel.setOnAction(e -> {
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});

		addScript.setOnAction(e -> {
			ArrayList<String> dialogLines = new ArrayList<>();
			for (Node textfield : textFieldsWrapper.getChildren()) {
				if (textfield instanceof TextField) {
					TextField toParse = (TextField) textfield;
					if (!toParse.getText().isEmpty()) {
						dialogLines.add(toParse.getText());
					}
				}
			}
			EventScript created = new Script_Dialog(dialogLines.toArray(new String[dialogLines.size()]));
			AddEventWindow.getController().addEventScript(created);
			Stage stage = (Stage) root.getScene().getWindow();
			stage.close();
		});
	}

}
