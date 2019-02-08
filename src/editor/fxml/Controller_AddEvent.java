package editor.fxml;

import commons.*;
import commons.eventScript.*;
import java.net.URL;
import java.util.*;
import javafx.beans.property.*;
import javafx.beans.value.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.util.*;

/**
 * FXML Controller class
 *
 * @author Jan
 */
public class Controller_AddEvent implements Initializable {

	@FXML private TextField triggerX;
	@FXML private TextField triggerY;
	@FXML private CheckBox triggeredByPlayer;
	@FXML private MenuButton addScriptMenu;
	@FXML private MenuItem addDialog;
	@FXML private MenuItem addSwitchBoard;
	@FXML private MenuItem addFlagGain;
	@FXML private MenuItem addFlagLost;
	@FXML private MenuItem addTeleport;
	@FXML private MenuItem addAlert;
	@FXML private Button addB;
	@FXML private Button cancelB;
	@FXML private ListView<EventScript> eventScriptList;
	@FXML private VBox missingFlagsWrapper;
	@FXML private VBox presentFlagsWrapper;

	private Set<Flag> selectedPresent = new HashSet<>();
	private Set<Flag> selectedAbsent = new HashSet<>();
	
	// TODO add windows for individual script types

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupFlagLists();
		setupAddButton();
	}

	private void setupAddButton() {
		addB.setOnAction(e -> {
			int x = Integer.parseInt(triggerX.getText());
			int y = Integer.parseInt(triggerY.getText());
			boolean playerTriggered = triggeredByPlayer.isSelected();
		});
	}

	private void setupFlagLists() {
		
		EventHandler<ActionEvent> missingBoxAction = e -> {
			CheckBox changed = (CheckBox)e.getSource();
			if (changed.isSelected()) selectedAbsent.add(Flag.valueOf(changed.getText()));
			else selectedAbsent.remove(Flag.valueOf(changed.getText()));
		};
		
		EventHandler<ActionEvent> presentBoxAction = e -> {
			CheckBox changed = (CheckBox)e.getSource();
			if (changed.isSelected()) selectedPresent.add(Flag.valueOf(changed.getText()));
			else selectedPresent.remove(Flag.valueOf(changed.getText()));
		};
		
		for(Flag flag:Flag.values()) {
			CheckBox missingBox = new CheckBox(flag.name());
			CheckBox presentBox = new CheckBox(flag.name());
			
			missingBox.setOnAction(missingBoxAction);
			presentBox.setOnAction(presentBoxAction);
			
			missingFlagsWrapper.getChildren().add(missingBox);
			presentFlagsWrapper.getChildren().add(presentBox);
		}
	}

}
