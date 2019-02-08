package commons.eventScript;

import commons.*;
import editor.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Controller_AddTeleport implements Initializable {

	@FXML private VBox root;
	@FXML private TextField x;
	@FXML private TextField y;
	@FXML private HBox buttonsRow;
	@FXML private Button cancel;
	@FXML private Button add;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		add.setOnAction(e -> {
			Integer x = null;
			Integer y = null;
			try {
				x = Integer.parseInt(this.x.getText());
				y = Integer.parseInt(this.y.getText());
				if (x < 0 || x > 39 || y < 0 || y > 21) throw new NumberFormatException();
			} catch (NumberFormatException ex) {
				Stage stage = (Stage) root.getScene().getWindow();
				stage.close();
			}
			EventScript created = new Script_Teleport(new Coords(x, y));
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
