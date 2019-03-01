package game;

// @author Jan
import commons.*;
import javafx.scene.input.*;

public class GameWindow extends FXMLWindow {

	public GameGUIController controller = super.getLoader().getController();

	public GameWindow() {
		super(GameWindow.class.getResource("gameGUI.fxml"), false);
		super.getScene().setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.TAB) controller.toggleConsole();
		});
	}

}
