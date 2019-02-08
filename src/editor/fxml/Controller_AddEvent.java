package editor.fxml;

import commons.*;
import commons.eventScript.*;
import commons.eventScript.Alert;
import editor.*;
import java.net.URL;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

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
	@FXML private Button deleteScriptB;

	private Set<Flag> selectedPresent = new HashSet<>();
	private Set<Flag> selectedAbsent = new HashSet<>();
	
	
	// TODO add windows for individual script types

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setupFlagLists();
		setupAddButton();
		setupDeleteButton();
		setupScriptAddition();
		setupCancelButton();
	}

	private void setupAddButton() {
		addB.setOnAction(ev -> {
			int x = Integer.parseInt(triggerX.getText());
			int y = Integer.parseInt(triggerY.getText());
			boolean playerTriggered = triggeredByPlayer.isSelected();
			
			System.out.println("absent flags:");
			selectedAbsent.forEach(System.out::println);
			System.out.println("\n");
			System.out.println("present flags");
			selectedPresent.forEach(System.out::println);
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

	private void setupScriptAddition() {
		addAlert.setOnAction(e -> Alert.showEditorWindow());
	}
	
	public void addEventScript(EventScript e) {
		eventScriptList.getItems().add(e);
	}

	private void setupDeleteButton() {
		deleteScriptB.setOnAction(e -> {
			EventScript selected = eventScriptList.getSelectionModel().getSelectedItem();
			eventScriptList.getItems().remove(selected);
		});
	}

	private void setupCancelButton() {
		cancelB.setOnAction(e -> AddEventWindow.close());
	}

}
