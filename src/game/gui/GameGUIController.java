package game.gui;

import game.logic.Alert;
import game.logic.Engine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.util.*;

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
	@FXML private Label timeText;
	
	private FadeTransition flashAnimation;
	private FadeTransition dayNightAnimation;
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		dayNightAnimation = new FadeTransition(Duration.seconds(30), lightLayer);
		
		flashAnimation = new FadeTransition(Duration.seconds(1));
		flashAnimation.setFromValue(1);
		flashAnimation.setToValue(0);
		flashAnimation.setDelay(Duration.seconds(2));
		
		consoleIn.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.TAB) toggleConsole();
		});
		
		consoleOut.getChildren().addListener((ListChangeListener.Change<? extends Node> c) -> {
			consoleScroll.layout();
			consoleScroll.setVvalue(1);
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

	public Label getTimeText() {
		return timeText;
	}
	
	public void sunrise() {
		dayNightAnimation.setToValue(0);
		dayNightAnimation.setFromValue(0.9);
		dayNightAnimation.play();
	}
	
	public void dawn() {
		dayNightAnimation.setToValue(0.9);
		dayNightAnimation.setFromValue(0);
		dayNightAnimation.play();
	}
}
