package game;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class GameGUIController implements Initializable {

	@FXML private Canvas backLayer;
	@FXML private Canvas actorLayer;
	@FXML private Canvas frontLayer;
	@FXML private Pane lightLayer;
	@FXML private ImageView vignette;
	@FXML private Pane GUI;
	@FXML private Label alertText;
	@FXML private Label dialogText;
	@FXML private Label mapName;
	@FXML private Label mapSubName;
	@FXML private VBox consoleWrapper;
	@FXML private TextFlow consoleOut;
	@FXML private TextField consoleIn;
	@FXML private VBox root;
	@FXML private VBox mapNameWrapper;
	@FXML private ScrollPane consoleScroll;
	
	private FadeTransition flashAnimation;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		flashAnimation = new FadeTransition(Duration.seconds(1));
		flashAnimation.setFromValue(1);
		flashAnimation.setToValue(0);
		flashAnimation.setDelay(Duration.seconds(2));
		
		consoleIn.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.TAB) toggleConsole();
		});
	}
	
		
	@FXML
	public void acceptCommand(){
		String command = consoleIn.getText();
		Engine.console.acceptInput(command);
		consoleIn.clear();
	}
	
	public void toggleConsole() {
		if (consoleWrapper.isVisible()) {
			consoleWrapper.setVisible(false);
			root.requestFocus();
		} else {
			consoleWrapper.setVisible(true);
		}
		
	}
	
	public void flashMapName(){
		flashAnimation.setNode(mapNameWrapper);
		flashAnimation.play();
	}
	
	public void flashDialog(){
		flashAnimation.setNode(dialogText);
		flashAnimation.play();		
	}
	
	public TextFlow getConsoleOutput(){
		return consoleOut;
	}

	public VBox getMapNameWrapper() {
		return mapNameWrapper;
	}

	public ScrollPane getConsoleScroll() {
		return consoleScroll;
	}
	
	public void flashAlert(Alert a) {
		alertText.setText(a.getText());
		alertText.setGraphic(new ImageView(a.getType().getAlertGraphics()));
		flashAnimation.setNode(alertText);
		flashAnimation.play();
	}

	public void playFlashAnimation() {
		flashAnimation.play();
	}

	public Label getAlertText() {
		return alertText;
	}

	public Label getDialogText() {
		return dialogText;
	}

	public Label getMapName() {
		return mapName;
	}

	public Label getMapSubName() {
		return mapSubName;
	}
	
	
	
}
